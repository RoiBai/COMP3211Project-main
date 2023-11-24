package hk.edu.polyu.comp.comp3211.group6.Model;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import hk.edu.polyu.comp.comp3211.group6.Controller.LoadStmt;
import hk.edu.polyu.comp.comp3211.group6.Controller.PIMError;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.io.IOException;

public class InterpreterVisitLoadStmtTest {
    private Interpreter interpreter;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @BeforeEach
    void setUp() {
        interpreter = mock(Interpreter.class);
        System.setOut(new PrintStream(outContent));
    }

    @Test
    void testVisitLoadStmtSuccessfulLoad() throws IOException, ClassNotFoundException {
        doNothing().when(interpreter).Interpreterload();
        doCallRealMethod().when(interpreter).visitLoadStmt(any(LoadStmt.class));
        interpreter.visitLoadStmt(new LoadStmt());

        assertTrue(outContent.toString().contains("Loaded"));
        verify(interpreter, times(1)).Interpreterload();
    }

    @Test
    void testVisitLoadStmtWithFileNotFoundException() throws IOException, ClassNotFoundException {
        doThrow(FileNotFoundException.class).when(interpreter).Interpreterload();
        doCallRealMethod().when(interpreter).visitLoadStmt(any(LoadStmt.class));

        assertThrows(PIMError.class, () -> interpreter.visitLoadStmt(new LoadStmt()));
    }

    @Test
    void testVisitLoadStmtWithIOException() throws IOException, ClassNotFoundException {
        doThrow(IOException.class).when(interpreter).Interpreterload();
        doCallRealMethod().when(interpreter).visitLoadStmt(any(LoadStmt.class));

        assertThrows(RuntimeException.class, () -> interpreter.visitLoadStmt(new LoadStmt()));
    }

    @Test
    void testVisitLoadStmtWithClassNotFoundException() throws IOException, ClassNotFoundException {
        doThrow(ClassNotFoundException.class).when(interpreter).Interpreterload();
        doCallRealMethod().when(interpreter).visitLoadStmt(any(LoadStmt.class));

        assertThrows(RuntimeException.class, () -> interpreter.visitLoadStmt(new LoadStmt()));
    }


    @AfterEach
    void restoreStreams() {
        System.setOut(originalOut);
    }
}
