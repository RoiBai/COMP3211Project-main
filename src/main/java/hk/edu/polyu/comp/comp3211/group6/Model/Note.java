package hk.edu.polyu.comp.comp3211.group6.Model;

import java.io.Serializable;

public class Note extends PIR implements Serializable {
    private String text;

    public Note(String identifier, String text){
        super(identifier);
        this.text = text;
    }


    public String getText() {
        return text;
    }
    public void setText(String text) {
        this.text = text;
    }

    @Override
    public <T> T accept(PrintVisitor<T> visitor) {
        return visitor.visitNote(this);
    }

    @Override
    public <T> T accept(ModifyVisitor<T> visitor) {
        return visitor.visitNote(this);
    }

    @Override
    public String getString() {
        return text;
    }

    public String toString(){
        return super.getIdentifier() + "\n" + text;
    }
}
