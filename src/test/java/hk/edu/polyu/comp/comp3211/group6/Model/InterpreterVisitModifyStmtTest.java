package hk.edu.polyu.comp.comp3211.group6.Model;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import hk.edu.polyu.comp.comp3211.group6.Controller.ModifyStmt;
import hk.edu.polyu.comp.comp3211.group6.Controller.PIMError;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.HashMap;

public class InterpreterVisitModifyStmtTest {
    private Interpreter interpreter;
    private HashMap<String, PIR> PIRs;

    @BeforeEach
    void setUp() {
        interpreter = new Interpreter();
        PIRs = new HashMap<>();
        interpreter.setInterpreterPIRs(PIRs);
    }

    @Test
    void testVisitModifyStmtWithExistingPIR() {
        String identifier = "existingPIR";
        PIR existingPIR = mock(PIR.class);
        PIRs.put(identifier, existingPIR);
        ModifyStmt stmt = new ModifyStmt(identifier);

        Void result = interpreter.visitModifyStmt(stmt);
        assertNull(result);
    }

    @Test
    void testVisitModifyStmtWithNonExistingPIR() {
        String identifier = "nonExistingPIR";
        ModifyStmt stmt = new ModifyStmt(identifier);

        assertThrows(PIMError.class, () -> interpreter.visitModifyStmt(stmt));
    }
}
