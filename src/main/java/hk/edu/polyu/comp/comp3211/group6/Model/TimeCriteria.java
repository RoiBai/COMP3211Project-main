package hk.edu.polyu.comp.comp3211.group6.Model;

import hk.edu.polyu.comp.comp3211.group6.Controller.Token;

import java.time.LocalDateTime;

public class TimeCriteria implements Criteria{
    private final Token operator;
    private final LocalDateTime dateTime;

    public TimeCriteria(Token operator, LocalDateTime dateTime){
        this.operator = operator;
        this.dateTime = dateTime;
    }

    public Token getOperator() {
        return operator;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    @Override
    public <T> T accept(CriteriaVisitor<T> visitor) {
        return visitor.visitTimeCriteria(this);
    }
}
