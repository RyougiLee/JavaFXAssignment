package model;

public class Note {
    String title;
    String content;

    public Note(String title, String content){
        this.title = title;
        this.content = content;
    }

    @Override
    public String toString(){
        return this.title + " - " + this.content;
    }
}
