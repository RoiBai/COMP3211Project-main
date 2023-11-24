package hk.edu.polyu.comp.comp3211.group6.View;

import hk.edu.polyu.comp.comp3211.group6.Model.*;
import hk.edu.polyu.comp.comp3211.group6.Controller.PIMError;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Printer implements PrintVisitor<Void> {

    private static final String FORMAT = "yyyy-MM-dd,HH:mm";
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern(FORMAT);
    private final HashMap<String, PIR>  PIRs;
    public Printer(HashMap<String, PIR> PIRs){
        this.PIRs = PIRs;
    }

    public void print(String identifier){
        if (identifier.equals("*")){
            printAll();
            return;
        }
        PIR pir = PIRs.get(identifier);
        if (pir == null) throw new PIMError("PIR \""+ identifier +"\" does not exist.");
        pir.accept(this);
    }

    public void printAll(){
        if (PIRs.isEmpty()) throw new PIMError("No PIR created.");
        List<PIR> pirList = new ArrayList<>(PIRs.values());
        for (PIR pir :pirList){
            pir.accept(this);
            System.out.println("---------------------------------------------------------");
        }
    }

    @Override
    public Void visitSchedule(Schedule schedule) {
        System.out.println("Schedule: " + schedule.getIdentifier());
        System.out.println("Description: " + schedule.getDescription());
        LocalDateTime startTime = schedule.getStartTime();
        LocalDateTime alarmTime = schedule.getAlarmTime();
        System.out.println("Start time: " + startTime.format(FORMATTER));
        System.out.println("Alarm time: " + alarmTime.format(FORMATTER));
        return null;
    }

    @Override
    public Void visitTask(Task task) {
        System.out.println("Task: " + task.getIdentifier());
        System.out.println("Description: " + task.getDescription());
        LocalDateTime deadline = task.getDeadline();
        String deadlineStr = deadline.format(FORMATTER);
        System.out.println("Deadline: " + deadlineStr);
        return null;
    }

    @Override
    public Void visitNote(Note note) {
        System.out.println("Note:" + note.getIdentifier());
        System.out.println(note.getText());
        return null;
    }

    @Override
    public Void visitContact(Contact contact) {
        System.out.println("Contact: " + contact.getIdentifier());
        System.out.println("Name: " + contact.getName());
        System.out.println("Address: " + contact.getAddress());
        System.out.println("Mobile number: " + contact.getMobile());
        return null;
    }
}
