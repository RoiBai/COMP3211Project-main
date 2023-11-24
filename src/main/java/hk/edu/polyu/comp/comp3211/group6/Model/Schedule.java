package hk.edu.polyu.comp.comp3211.group6.Model;

import hk.edu.polyu.comp.comp3211.group6.Controller.PIMError;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serial;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Timer;

public class Schedule extends PIR implements Serializable, HasTime {
    private String description;
    private LocalDateTime startTime;
    private LocalDateTime alarmTime;
    private static final Timer timer = new Timer();

    private final static String FORMAT = "yyyy-MM-dd,HH:mm";
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern(FORMAT);
    private static final DateFormat dateFormatter = new SimpleDateFormat(FORMAT);

    public Schedule(String identifier, String description, LocalDateTime startTime, LocalDateTime alarmTime){
        super(identifier);
        this.description = description;
        this.startTime = startTime;
        this.alarmTime = alarmTime;
        checkTime();
        if(!"test".equals(System.getProperty("environment"))){
            setTimer();
        }
    }

    public void setTimer(){
        Alarm alarm = new Alarm("\nAlarm!\nSchedule: " + super.getIdentifier() +"\nDescription: " + description +
                "\nStart time: " + startTime.format(formatter) + "\nPIM> ");
        try {
            Date date = dateFormatter.parse(alarmTime.format(formatter));
            timer.schedule(alarm, date);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    public void checkTime(){
        if (alarmTime.isBefore(LocalDateTime.now()))
            throw new PIMError("Alarm time cannot be before current time.");
        if (alarmTime.isAfter(startTime))
            throw new PIMError("Alarm time cannot be after start time.");
    }

    @Override
    public <T> T accept(PrintVisitor<T> visitor) {
        return visitor.visitSchedule(this);
    }

    @Override
    public <T> T accept(ModifyVisitor<T> visitor) {
        return visitor.visitSchedule(this);
    }

    @Override
    public String getString() {
        return description;
    }

    @Override
    public List<LocalDateTime> getTime() {
        List<LocalDateTime> result = new ArrayList<>();
        result.add(startTime);
        result.add(alarmTime);
        return result;
    }

    @Serial
    private void readObject(ObjectInputStream ois) throws ClassNotFoundException, IOException {
        ois.defaultReadObject();
        this.setTimer();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
        checkTime();
    }

    public LocalDateTime getAlarmTime() {
        return alarmTime;
    }

    public void setAlarmTime(LocalDateTime alarmTime) {
        this.alarmTime = alarmTime;
        checkTime();
        timer.cancel();
        setTimer();
    }

    @Override
    public String toString() {
        return super.getIdentifier() + "\n" + description + "\n" + startTime.format(formatter) + "\n"
                + alarmTime.format(formatter);
    }
}
