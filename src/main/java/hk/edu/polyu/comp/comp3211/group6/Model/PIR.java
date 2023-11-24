package hk.edu.polyu.comp.comp3211.group6.Model;

import java.io.Serializable;

public abstract class PIR implements Serializable {
    private String identifier;
    public PIR(String identifier){
        this.identifier = identifier;
    }
    public abstract <T> T accept(PrintVisitor<T> visitor);
    public abstract <T> T accept(ModifyVisitor<T> visitor);
    public String getIdentifier(){
        return identifier;
    }
    public void setIdentifier(String identifier){
        this.identifier = identifier;
    }

    public abstract String getString();
}
