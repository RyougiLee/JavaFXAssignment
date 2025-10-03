import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.*;

public class RealisticHealthcareSimulation extends Application {

    // Triage（分诊）
    private Queue<Patient> triageQueue = new LinkedList<>();
    private Label triageLabel = new Label("Triage Queue: 0");
    private Pane triagePane = new Pane();

    // Outpatient（普通门诊） - 单一队列，多窗口
    private Queue<Patient> outpatientQueue = new LinkedList<>();
    private Label outpatientQueueLabel = new Label("Outpatient Queue: 0");
    private Pane outpatientQueuePane = new Pane();
    private int numOutpatientWindows = 3;
    private List<ServiceWindow> outpatientWindows = new ArrayList<>();

    // Pharmacy（药房） - 单一队列，多窗口
    private Queue<Patient> pharmacyQueue = new LinkedList<>();
    private Label pharmacyQueueLabel = new Label("Pharmacy Queue: 0");
    private Pane pharmacyQueuePane = new Pane();
    private int numPharmacyWindows = 2;
    private List<ServiceWindow> pharmacyWindows = new ArrayList<>();

    // Inpatient（住院部） - 有床位限制
    private Queue<Patient> inpatientQueue = new LinkedList<>();
    private Label inpatientQueueLabel = new Label("Inpatient Waiting Queue: 0");
    private Pane inpatientQueuePane = new Pane();
    private int maxBeds = 50;
    private int occupiedBeds = 0;
    private Label inpatientBedLabel = new Label("Beds Occupied: 0 / 50");

    private Random random = new Random();
    private int patientId = 1;

    @Override
    public void start(Stage stage) {
        // 创建各个模块
        VBox triageBox = createQueueBox("Triage", triageLabel, triagePane);

        VBox outpatientQueueBox = createQueueBox("Outpatient Queue", outpatientQueueLabel, outpatientQueuePane);
        HBox outpatientWindowsBox = createWindowsBox("Outpatient", outpatientWindows, numOutpatientWindows);

        VBox pharmacyQueueBox = createQueueBox("Pharmacy Queue", pharmacyQueueLabel, pharmacyQueuePane);
        HBox pharmacyWindowsBox = createWindowsBox("Pharmacy", pharmacyWindows, numPharmacyWindows);

        VBox inpatientBox = createQueueBox("Inpatient Waiting", inpatientQueueLabel, inpatientQueuePane);
        inpatientBox.getChildren().add(inpatientBedLabel);

        VBox root = new VBox(30,
                triageBox,
                outpatientQueueBox, outpatientWindowsBox,
                pharmacyQueueBox, pharmacyWindowsBox,
                inpatientBox
        );
        root.setAlignment(Pos.CENTER);
        root.setStyle("-fx-background-color: #f4f4f4; -fx-padding: 20;");

        Scene scene = new Scene(root, 1000, 800);
        stage.setTitle("Healthcare Simulation - Shared Queue & Bed Limit");
        stage.setScene(scene);
        stage.show();

        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1), e -> simulateStep()));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

    private VBox createQueueBox(String title, Label queueLabel, Pane queuePane) {
        Label titleLabel = new Label(title);
        titleLabel.setFont(new Font(18));
        queuePane.setPrefSize(400, 80);
        queuePane.setStyle("-fx-background-color: white; -fx-border-color: black; -fx-border-width: 1;");

        VBox box = new VBox(10, titleLabel, queueLabel, queuePane);
        box.setAlignment(Pos.CENTER);
        box.setStyle("-fx-border-color: #333; -fx-border-width: 2; -fx-background-color: #fff;");
        return box;
    }

    private HBox createWindowsBox(String name, List<ServiceWindow> list, int count) {
        HBox row = new HBox(20);
        row.setAlignment(Pos.CENTER);
        for (int i = 1; i <= count; i++) {
            ServiceWindow window = new ServiceWindow(name + " Window " + i);
            list.add(window);
            row.getChildren().add(window.box);
        }
        return row;
    }

    private void simulateStep() {
        // 1. 新病人进入分诊队列
        if (random.nextDouble() < 0.7) {
            triageQueue.add(new Patient(patientId++));
        }

        // 2. 分诊 → Outpatient 队列
        if (!triageQueue.isEmpty() && random.nextDouble() < 0.8) {
            outpatientQueue.add(triageQueue.poll());
        }

        // 3. Outpatient 窗口处理
        for (ServiceWindow w : outpatientWindows) {
            w.process(outpatientQueue, pharmacyQueue, inpatientQueue);
        }

        // 4. Pharmacy 窗口处理
        for (ServiceWindow w : pharmacyWindows) {
            w.process(pharmacyQueue, null, null);
        }

        // 5. Inpatient 床位管理
        //   - 有床 → 直接入院
        //   - 床位满 → 排队等待
        if (!inpatientQueue.isEmpty() && occupiedBeds < maxBeds) {
            inpatientQueue.poll();
            occupiedBeds++;
        }

        // 模拟部分病人出院，释放床位
        if (occupiedBeds > 0 && random.nextDouble() < 0.2) {
            occupiedBeds--;
        }

        updateQueueVisual(triageQueue, triagePane, triageLabel, "Triage Queue");
        updateQueueVisual(outpatientQueue, outpatientQueuePane, outpatientQueueLabel, "Outpatient Queue");
        updateQueueVisual(pharmacyQueue, pharmacyQueuePane, pharmacyQueueLabel, "Pharmacy Queue");
        updateQueueVisual(inpatientQueue, inpatientQueuePane, inpatientQueueLabel, "Inpatient Waiting Queue");
        inpatientBedLabel.setText("Beds Occupied: " + occupiedBeds + " / " + maxBeds);
    }

    private void updateQueueVisual(Queue<Patient> queue, Pane pane, Label label, String prefix) {
        pane.getChildren().clear();
        int index = 0;
        for (Patient p : queue) {
            double x = 20;
            double y = 20 + index * 20; // 单列纵向排队
            p.circle.setCenterX(x);
            p.circle.setCenterY(y);
            pane.getChildren().add(p.circle);
            index++;
        }
        label.setText(prefix + ": " + queue.size());
    }

    public static void main(String[] args) {
        launch();
    }

    // 👤 病人类
    static class Patient {
        Circle circle;
        int id;

        Patient(int id) {
            this.id = id;
            this.circle = new Circle(8, Color.color(Math.random(), Math.random(), Math.random()));
        }
    }

    // 🪟 服务窗口类（共享队列）
    static class ServiceWindow {
        String name;
        Label label;
        Pane pane;
        VBox box;
        Patient currentPatient;
        int serviceTime = 0;
        Random rand = new Random();

        ServiceWindow(String name) {
            this.name = name;
            label = new Label(name + ": idle");
            pane = new Pane();
            pane.setPrefSize(60, 60);
            pane.setStyle("-fx-border-color: black; -fx-background-color: white;");
            box = new VBox(5, label, pane);
            box.setAlignment(Pos.CENTER);
            box.setMinSize(100, 100);
            box.setStyle("-fx-border-color: #666; -fx-border-width: 1;");
        }

        void process(Queue<Patient> fromQueue, Queue<Patient> toPharmacy, Queue<Patient> toInpatient) {
            if (currentPatient == null) {
                if (!fromQueue.isEmpty()) {
                    currentPatient = fromQueue.poll();
                    serviceTime = 2 + rand.nextInt(4); // 服务时间 2-5秒
                    pane.getChildren().setAll(currentPatient.circle);
                    currentPatient.circle.setCenterX(30);
                    currentPatient.circle.setCenterY(30);
                    label.setText(name + ": serving");
                }
            } else {
                serviceTime--;
                if (serviceTime <= 0) {
                    // 完成服务 → 转到下一个环节
                    if (toPharmacy != null && rand.nextBoolean()) {
                        toPharmacy.add(currentPatient);
                    } else if (toInpatient != null && rand.nextBoolean()) {
                        toInpatient.add(currentPatient);
                    }
                    currentPatient = null;
                    pane.getChildren().clear();
                    label.setText(name + ": idle");
                }
            }
        }
    }
}

class simutest{
    public static void main(String[] args) {
        Application.launch(RealisticHealthcareSimulation.class);
    }
}
