package model;

public class Pet {

    double x = 50;
    double y = 50;

    public synchronized void setX (double value){
        this.x = value;
    }

    public synchronized void setY (double value){
        this.y = value;
    }

    public synchronized double getX (){
        return this.x;
    }

    public synchronized double getY (){
        return this.y;
    }
}
