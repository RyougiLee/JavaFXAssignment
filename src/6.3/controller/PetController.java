package controller;

import model.Pet;
import view.PetGUI;

public class PetController {

    private PetGUI gui;
    private Pet pet = new Pet();
    private volatile boolean running = false;

    public PetController(PetGUI gui){

        this.gui = gui;
    }

    public void start(){
        System.out.println("start");
        running = true;
    }

    public void stop(){
        System.out.println("stop");
        running = false;
    }

    public void move(){
        new Thread(()-> {
            while(true){
                if(running){

                    double mouseX = gui.getMouseX();
                    double mouseY = gui.getMouseY();
                    double petX = pet.getX();
                    double petY = pet.getY();

                    if(petX < mouseX -1){
                        petX += 1;
                    } else if (petX > mouseX +1) {
                        petX -= 1;
                    }

                    if(petY < mouseY -1){
                        petY += 1;
                    } else if (petY > mouseY +1){
                        petY -= 1;
                    }

                    gui.setImgX(petX);
                    gui.setImgY(petY);

                    pet.setX(petX);
                    pet.setY(petY);

                    System.out.println("X:" + petX + " Y: " + petY);

                    try {
                        Thread.sleep(5);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }).start();
    }

    }
