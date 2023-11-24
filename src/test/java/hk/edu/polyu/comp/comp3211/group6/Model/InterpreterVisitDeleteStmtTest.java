package hk.edu.polyu.comp.comp3211.group6.Model;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import hk.edu.polyu.comp.comp3211.group6.Controller.DeleteStmt;
import hk.edu.polyu.comp.comp3211.group6.Controller.PIMError;
import hk.edu.polyu.comp.comp3211.group6.Controller.Token;
import hk.edu.polyu.comp.comp3211.group6.Controller.TokenType;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.HashMap;

public class InterpreterVisitDeleteStmtTest {
    private Interpreter interpreter;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @BeforeEach
    void setUp() {
        interpreter = new Interpreter();
        interpreter.setInterpreterPIRs(new HashMap<>()); // 假设有方法设置 PIRs
        System.setOut(new PrintStream(outContent));
    }

    @Test
    void testVisitDeleteStmtWithExistingPIR() {
        Token identifier = new Token(TokenType.IDENTIFIER,"IDENTIFIER",null);
        PIR existingPIR = mock(PIR.class);
        interpreter.getInterpreterPIRs().put(identifier.lexeme, existingPIR);
        DeleteStmt stmt = new DeleteStmt(identifier);

        interpreter.visitDeleteStmt(stmt);

        assertTrue(outContent.toString().contains("PIR \"" + identifier.lexeme + "\" is deleted."));
        assertFalse(interpreter.getInterpreterPIRs().containsKey(identifier));
    }

    @Test
    void testVisitDeleteStmtWithNonExistingPIR() {
        Token identifier = new Token(TokenType.IDENTIFIER,"IDENTIFIER",null);
        DeleteStmt stmt = new DeleteStmt(identifier);

        assertThrows(PIMError.class, () -> interpreter.visitDeleteStmt(stmt));
    }

    @AfterEach
    void restoreStreams() {
        System.setOut(originalOut);
    }
}
