package hk.edu.polyu.comp.comp3211.group6.Controller;

import hk.edu.polyu.comp.comp3211.group6.Model.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

public class Parser {
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd,HH:mm");
    private final List<Token> tokens;
    private int current = 0;

    public Parser(List<Token> tokens){
        this.tokens = tokens;
    }

    private boolean isAtEnd() {
        return peek().type == TokenType.EOF;
    }

    private Token peek() {
        return tokens.get(current);
    }

    private Token previous() {
        return tokens.get(current - 1);
    }

    private Token advance() {
        if (!isAtEnd()) current++;
        return previous();
    }

    private boolean check(TokenType type) {
        if (isAtEnd()) return false;
        return peek().type == type;
    }

    private boolean match(TokenType... types) {
        for (TokenType type : types) {
            if (check(type)) {
                advance();
                return true;
            }
        }
        return false;
    }

    private Token consume(TokenType type, String message){
        if(check(type)) return advance();
        throw new ParserError(message);
    }

    private Token consume(TokenType type1, TokenType type2, String message){
        if (check(type1)) return advance();
        else if (check(type2)) return advance();
        throw new ParserError(message);
    }

    public Stmt parse(){
        try{
            if (match(TokenType.CREATE)) return crateStmt();
            if (match(TokenType.PRINT)) return printStmt();
            if (match(TokenType.EXIT)) return exitStmt();
            if (match(TokenType.SAVE)) return saveStmt();
            if (match(TokenType.LOAD)) return loadStmt();
            if (match(TokenType.MODIFY)) return modifyStmt();
            if (match(TokenType.SEARCH)) return searchStmt();
            if (match(TokenType.DELETE)) return deleteStmt();
            throw new ParserError("""
                Commands: create
                          print
                          exit
                          save
                          load
                          modify""");
        }catch (ParserError e){
            throw new PIMError(e);
        }

    }

    private Criteria search(){
        Criteria criteria = null;
        while (!isAtEnd())
            criteria = or();
        return criteria;
    }
    private Criteria or(){
        Criteria criteria = and();
        while (match(TokenType.OR)){
            Criteria right = and();
            criteria = new OrCriteria(criteria, right);
        }
        return criteria;
    }
    private Criteria and(){
        Criteria criteria = neg();
        while (match(TokenType.AND)){
            Criteria right = neg();
            criteria = new AndCriteria(criteria, right);
        }
        return criteria;
    }

    private Criteria neg(){
        if (match(TokenType.NEG)){
            Criteria right = neg();
            return new NegCriteria(right);
        }
        return primary();
    }

    private Criteria primary(){
        if (match(TokenType.NOTE, TokenType.SCHEDULE, TokenType.TASK, TokenType.CONTACT)){
            Token type = previous();
            return new TypeCriteria(type);
        }else if (match(TokenType.IDENTIFIER)){
            Token id = previous();
            return new IdCriteria(id.lexeme);
        }else if (match(TokenType.STRING)){
            Token str = previous();
            return new StringCriteria((String) str.literal);
        }else if (match(TokenType.BEFORE, TokenType.AFTER, TokenType.EQUAL)){
            Token operator = previous();
            Token dateTimeStr = consume(TokenType.DATE, "Expect a datetime.");
            try {
                LocalDateTime dateTime = LocalDateTime.parse(dateTimeStr.lexeme, formatter);
                return new TimeCriteria(operator, dateTime);
            }catch (DateTimeParseException e){
                throw new ParserError("Invalid datetime format. (yyyy-MM-dd,HH:mm)");
            }
        }
        throw new ParserError("Expect a condition.");
    }

    private Stmt searchStmt(){
       Criteria criteria = search();
       if (criteria == null)
           throw new ParserError("Expect a condition after search.");

        return new SearchStmt(criteria);
    }

    private Stmt deleteStmt(){
        Token identifier = consume(TokenType.IDENTIFIER, "Expect an identifier after delete.");
        return new DeleteStmt(identifier);
    }

    private Stmt modifyStmt(){
        Token identifier = consume(TokenType.IDENTIFIER, "Expect an identifier after modify.");
        return new ModifyStmt(identifier.lexeme);
    }

    private Stmt loadStmt(){return new LoadStmt();}

    private Stmt saveStmt(){return new SaveStmt();}
    private Stmt exitStmt(){
        return new ExitStmt();
    }

    private Stmt crateStmt(){
        if (match(TokenType.NOTE, TokenType.TASK, TokenType.SCHEDULE, TokenType.CONTACT)){
            Token dataType = previous();
            Token identifier = consume(TokenType.IDENTIFIER, "Expect an identifier for a PIR.");
            return new CreateStmt(dataType, identifier);
        }else throw new ParserError("create {Note, Task, Schedule, Contact}");
    }

    private Stmt printStmt(){
        Token identifier = consume(TokenType.IDENTIFIER, TokenType.ALL, "Expect an identifier after print.");
        return new PrintStmt(identifier);
    }

}
