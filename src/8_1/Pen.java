import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

//---------Task 1----------

public class Pen {

    public enum Color {
        RED("red"), GREEN("green"), BLUE("blue");
        private final String color;
        Color(String color) { this.color = color; };
        @Override public String toString() { return color; }
    }

    Color penColor;
    Boolean cap = true;

    // your code here
    public Pen(){
        this.penColor = Color.RED;
    }
    public Pen(Color color){
        this.penColor = color;
    }

    public String draw(){

        if(this.cap){
            return "";
        }
        else {return "Drawing "+ this.penColor.color;}
    }

    public void capOff(){
        this.cap = false;
    }
    public void capOn(){
        this.cap = true;
    }

    public void changeColor(Color color){

        if (this.cap){
        this.penColor = color;}
    }
}

class PenTest {
    @Test
    void canDrawDefault() {
        Pen p = new Pen();
        assertEquals("", p.draw());
        p.capOff();
        assertEquals("Drawing red", p.draw());
        p.capOn();
        assertEquals("", p.draw());
    }

    @Test
    void canDrawSelectedColor() {
        Pen p = new Pen(Pen.Color.BLUE);
        assertEquals("", p.draw());
        p.capOff();
        assertEquals("Drawing blue", p.draw());
        p.capOn();
        assertEquals("", p.draw());
    }

    @Test
    void canChangeColor() {
        Pen p = new Pen(Pen.Color.RED);
        p.capOff();
        assertEquals("Drawing red", p.draw());
        p.changeColor(Pen.Color.GREEN);
        assertEquals("Drawing red", p.draw());

        p.capOn();
        p.changeColor(Pen.Color.GREEN);
        assertEquals("", p.draw());
        p.capOff();
        assertEquals("Drawing green", p.draw());
    }
}

