package hk.edu.polyu.comp.comp3211.group6.Model;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import hk.edu.polyu.comp.comp3211.group6.Controller.PIMError;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.time.format.DateTimeParseException;
import java.util.HashMap;
import hk.edu.polyu.comp.comp3211.group6.Controller.CreateStmt;
import hk.edu.polyu.comp.comp3211.group6.Controller.Token;
import hk.edu.polyu.comp.comp3211.group6.Controller.TokenType;

public class InterpreterVisitCrateTestB {
    private Interpreter interpreter;
    private HashMap<String, PIR> PIRs;

    @BeforeEach
    void setUp() {
        interpreter = new Interpreter();
        PIRs = new HashMap<>();
        interpreter.setInterpreterPIRs(PIRs);
    }

    @Test
    void testVisitCreateStmtWithExistingIdentifier() {
        String existingIdentifier = "existingPIR";
        PIR existingPIR = mock(PIR.class);
        PIRs.put(existingIdentifier, existingPIR);

        Token dataTypeToken = new Token(TokenType.NOTE, "NOTE",null);
        Token identifierToken = new Token(TokenType.IDENTIFIER, existingIdentifier, null);
        CreateStmt stmt = new CreateStmt(dataTypeToken, identifierToken);

        assertThrows(PIMError.class, () -> interpreter.visitCreateStmt(stmt));
    }
}
