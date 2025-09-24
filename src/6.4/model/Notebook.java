package model;
import model.Note;

import java.util.ArrayList;

public class Notebook {

    Note note;
    ArrayList<Note> booklist = new ArrayList<>();

    public void add (Note note){
        booklist.add(note);
    }

    public ArrayList get (){
        return booklist;
    }
}
