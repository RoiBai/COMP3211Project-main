package hk.edu.polyu.comp.comp3211.group6.Model;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import hk.edu.polyu.comp.comp3211.group6.Controller.PIMError;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.time.DateTimeException;
import java.time.format.DateTimeParseException;
import java.util.HashMap;
import hk.edu.polyu.comp.comp3211.group6.Controller.CreateStmt;
import hk.edu.polyu.comp.comp3211.group6.Controller.Token;
import hk.edu.polyu.comp.comp3211.group6.Controller.TokenType;


class InterpreterVisitCreateStmtTest{
    private Interpreter interpreter;
    private HashMap<String, PIR> PIRs;

    @BeforeEach
    void setUp() throws IOException {
        Interpreter realInterpreter = new Interpreter();
        HashMap<String, PIR> PIRs = new HashMap<>();
        realInterpreter.setInterpreterPIRs(PIRs);

        interpreter = spy(realInterpreter);
        doNothing().when(interpreter).createNoteInterpreter(anyString());
        doNothing().when(interpreter).createContactInterpreter(anyString());
        doNothing().when(interpreter).createTaskInterpreter(anyString());
        doNothing().when(interpreter).createScheduleInterpreter(anyString());
    }


    @Test
    void testVisitCreateStmtForNote() {
        String identifier = "newNote";
        Token dataTypeToken = new Token(TokenType.NOTE, "NOTE", null);
        Token identifierToken = new Token(TokenType.IDENTIFIER, identifier, null);
        CreateStmt stmt = new CreateStmt(dataTypeToken, identifierToken);

        doCallRealMethod().when(interpreter).visitCreateStmt(any(CreateStmt.class));
        interpreter.visitCreateStmt(stmt);
    }
    @Test
    void testVisitCreateStmtForContact() {
        String identifier = "newContact";
        Token dataTypeToken = new Token(TokenType.CONTACT, "CONTACT", null);
        Token identifierToken = new Token(TokenType.IDENTIFIER, identifier, null);
        CreateStmt stmt = new CreateStmt(dataTypeToken, identifierToken);

        doCallRealMethod().when(interpreter).visitCreateStmt(any(CreateStmt.class));
        interpreter.visitCreateStmt(stmt);
    }
    @Test
    void testVisitCreateStmtForSchedule()  {
        String identifier = "newSchedule";
        Token dataTypeToken = new Token(TokenType.SCHEDULE, "SCHEDULE", null);
        Token identifierToken = new Token(TokenType.IDENTIFIER, identifier, null);
        CreateStmt stmt = new CreateStmt(dataTypeToken, identifierToken);

        doCallRealMethod().when(interpreter).visitCreateStmt(any(CreateStmt.class));
        interpreter.visitCreateStmt(stmt);
    }

    @Test
    void testVisitCreateStmtForTask() {
        String identifier = "newTask";
        Token dataTypeToken = new Token(TokenType.TASK, "TASK", null);
        Token identifierToken = new Token(TokenType.IDENTIFIER, identifier, null);
        CreateStmt stmt = new CreateStmt(dataTypeToken, identifierToken);

        doCallRealMethod().when(interpreter).visitCreateStmt(any(CreateStmt.class));
        interpreter.visitCreateStmt(stmt);
    }

    @Test
    void testVisitCreateStmtThrowsRuntimeExceptionForNote() throws IOException {
        String identifier = "newNote";
        Token dataTypeToken = new Token(TokenType.NOTE, "NOTE", null);
        Token identifierToken = new Token(TokenType.IDENTIFIER, identifier, null);
        CreateStmt stmt = new CreateStmt(dataTypeToken, identifierToken);

        doThrow(IOException.class).when(interpreter).createNoteInterpreter(anyString());

        assertThrows(RuntimeException.class, () -> interpreter.visitCreateStmt(stmt));
    }

    @Test
    void testVisitCreateStmtThrowsRuntimeExceptionForTask() throws IOException {
        String identifier = "newTask";
        Token dataTypeToken = new Token(TokenType.TASK, "TASK", null);
        Token identifierToken = new Token(TokenType.IDENTIFIER, identifier, null);
        CreateStmt stmt = new CreateStmt(dataTypeToken, identifierToken);

        doThrow(IOException.class).when(interpreter).createTaskInterpreter(anyString());

        assertThrows(RuntimeException.class, () -> interpreter.visitCreateStmt(stmt));
    }

    @Test
    void testVisitCreateStmtThrowsRuntimeExceptionForSchedule() throws IOException {
        String identifier = "newSchedule";
        Token dataTypeToken = new Token(TokenType.SCHEDULE, "SCHEDULE", null);
        Token identifierToken = new Token(TokenType.IDENTIFIER, identifier, null);
        CreateStmt stmt = new CreateStmt(dataTypeToken, identifierToken);

        doThrow(IOException.class).when(interpreter).createScheduleInterpreter(anyString());
        assertThrows(RuntimeException.class, () -> interpreter.visitCreateStmt(stmt));
    }
    @Test
    void testVisitCreateStmtThrowsRuntimeExceptionForContact() throws IOException {
        String identifier = "newContact";
        Token dataTypeToken = new Token(TokenType.CONTACT, "CONTACT", null);
        Token identifierToken = new Token(TokenType.IDENTIFIER, identifier, null);
        CreateStmt stmt = new CreateStmt(dataTypeToken, identifierToken);

        doThrow(IOException.class).when(interpreter).createContactInterpreter(anyString());
        assertThrows(RuntimeException.class, () -> interpreter.visitCreateStmt(stmt));
    }

    @Test
    void testVisitCreateStmtThrowsDateTimeForTask() throws IOException {
        String identifier = "newTask";
        Token dataTypeToken = new Token(TokenType.TASK, "TASK", null);
        Token identifierToken = new Token(TokenType.IDENTIFIER, identifier, null);
        CreateStmt stmt = new CreateStmt(dataTypeToken, identifierToken);

        doThrow(DateTimeParseException.class).when(interpreter).createTaskInterpreter(anyString());
        assertThrows(PIMError.class, () -> interpreter.visitCreateStmt(stmt));
    }

    @Test
    void testVisitCreateStmtThrowsDateTimeForSchedule() throws IOException {
        String identifier = "newSchedule";
        Token dataTypeToken = new Token(TokenType.SCHEDULE, "SCHEDULE", null);
        Token identifierToken = new Token(TokenType.IDENTIFIER, identifier, null);
        CreateStmt stmt = new CreateStmt(dataTypeToken, identifierToken);

        doThrow(DateTimeParseException.class).when(interpreter).createScheduleInterpreter(anyString());
        assertThrows(PIMError.class, () -> interpreter.visitCreateStmt(stmt));
    }

    @AfterEach
    void tearDown() {
        interpreter = null;
    }

}