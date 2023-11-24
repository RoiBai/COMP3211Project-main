package hk.edu.polyu.comp.comp3211.group6.Model;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import hk.edu.polyu.comp.comp3211.group6.Controller.PrintStmt;
import hk.edu.polyu.comp.comp3211.group6.Controller.Token;
import hk.edu.polyu.comp.comp3211.group6.Controller.TokenType;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.HashMap;

public class InterpreterVisitPrintStmtTest {
    private Interpreter interpreter;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @BeforeEach
    void setUp() {
        interpreter = new Interpreter();
        System.setOut(new PrintStream(outContent));
    }

    @Test
    void testVisitPrintStmt() {
        String identifier = "testPIR";
        PIR pir = mock(PIR.class);
        when(pir.toString()).thenReturn("Printed PIR info");

        HashMap<String, PIR> PIRs = new HashMap<>();
        PIRs.put(identifier, pir);
        interpreter.setInterpreterPIRs(PIRs);

        Token token = new Token(TokenType.IDENTIFIER, identifier, null);
        PrintStmt stmt = new PrintStmt(token);

        Void result = interpreter.visitPrintStmt(stmt);
        assertNull(result);
    }

    @AfterEach
    void restoreStreams() {
        System.setOut(originalOut);
    }
}
