package hk.edu.polyu.comp.comp3211.group6.Model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Task extends PIR implements Serializable, HasTime {
    private String description;
    private LocalDateTime deadline;
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd,HH:mm");

    public Task(String identifier, String description, LocalDateTime deadline){
        super(identifier);
        this.description = description;
        this.deadline = deadline;
    }
    @Override
    public <T> T accept(PrintVisitor<T> visitor) {
        return visitor.visitTask(this);
    }

    @Override
    public <T> T accept(ModifyVisitor<T> visitor) {
        return visitor.visitTask(this);
    }


    @Override
    public String getString() {
        return description;
    }

    @Override
    public List<LocalDateTime> getTime() {
        List<LocalDateTime> result = new ArrayList<>();
        result.add(deadline);
        return result;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description){this.description = description;}

    public LocalDateTime getDeadline() {
        return deadline;
    }

    public void setDeadline(LocalDateTime deadline) {
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        return super.getIdentifier() + "\n" + description + "\n" + deadline.format(formatter);
    }
}
