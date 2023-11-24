package hk.edu.polyu.comp.comp3211.group6.Controller;

public class PIMError extends RuntimeException{
    public PIMError(String message){
        System.out.println(message);
    }
    public PIMError(RuntimeException e){
        System.out.println(e.getMessage());
    }
}
