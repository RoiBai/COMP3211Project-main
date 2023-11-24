package hk.edu.polyu.comp.comp3211.group6.Model;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import hk.edu.polyu.comp.comp3211.group6.Controller.PIMError;
import hk.edu.polyu.comp.comp3211.group6.Controller.SaveStmt;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;

public class InterpreterVisitSaveStmtTest {
    private Interpreter interpreter;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @BeforeEach
    void setUp() throws IOException {
        interpreter = mock(Interpreter.class);
        System.setOut(new PrintStream(outContent));
    }

    @Test
    void testVisitSaveStmtSuccessfulSave() throws IOException {
        doNothing().when(interpreter).Interpretersave();
        doCallRealMethod().when(interpreter).visitSaveStmt(any(SaveStmt.class));
        interpreter.visitSaveStmt(new SaveStmt());

        assertTrue(outContent.toString().contains("Saved"));
        verify(interpreter, times(1)).Interpretersave();
    }

    @Test
    void testVisitSaveStmtWithFileNotFoundException() throws IOException {
        doThrow(FileNotFoundException.class).when(interpreter).Interpretersave();
        doCallRealMethod().when(interpreter).visitSaveStmt(any(SaveStmt.class));

        assertThrows(PIMError.class, () -> interpreter.visitSaveStmt(new SaveStmt()));
    }

    @Test
    void testVisitSaveStmtThrowsRuntimeException() throws IOException {
        doThrow(IOException.class).when(interpreter).Interpretersave();
        doCallRealMethod().when(interpreter).visitSaveStmt(any(SaveStmt.class));

        assertThrows(RuntimeException.class, () -> interpreter.visitSaveStmt(new SaveStmt()));
    }


    @AfterEach
    void restoreStreams() {
        System.setOut(originalOut);
    }
}
