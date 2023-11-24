package hk.edu.polyu.comp.comp3211.group6.Model;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import org.junit.jupiter.api.*;

import java.io.*;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.io.ByteArrayOutputStream;

public class ModifierVisitTaskTest {
    private Modifier modifier;
    private Task task;
    private final InputStream originalSystemIn = System.in;
    private final PrintStream originalSystemOut = System.out;
    private ByteArrayOutputStream testOut;
    private HashMap<String, PIR> PIRs;
    private String originalEnvironmentValue;

    @BeforeEach
    void setUp() throws IOException {
        modifier = spy(new Modifier(PIRs));
        task = mock(Task.class);

        doNothing().when(modifier).updateIdentifier(any(PIR.class));
        doNothing().when(task).setDescription(anyString());
        doNothing().when(task).setDeadline(any(LocalDateTime.class));

        originalEnvironmentValue = System.getProperty("environment");
        System.setProperty("environment", "test");
        testOut = new ByteArrayOutputStream();
        System.setOut(new PrintStream(testOut));
    }

    void setMockInput(String input) {
        System.setIn(new ByteArrayInputStream(input.getBytes()));
    }

    @Test
    void testVisitTaskUpdateIdentifier() {
        String simulatedInput = "1\n";
        setMockInput(simulatedInput);
        modifier.visitTask(task);

        assertTrue(testOut.toString().contains("Identifier"));
        assertFalse(testOut.toString().contains("Invalid input."));
    }

    @Test
    void testVisitTaskDescription() {
        String simulatedInput = "2\n";
        setMockInput(simulatedInput);
        modifier.visitTask(task);
        assertTrue(testOut.toString().contains("Description"));
        assertFalse(testOut.toString().contains("Invalid input."));
    }

    @Test
    void testVisitTaskDeadline() {
        String simulatedInput = "3\n";
        setMockInput(simulatedInput);
        modifier.visitTask(task);
        assertTrue(testOut.toString().contains("Deadline"));
        assertFalse(testOut.toString().contains("Invalid input."));
    }

    @Test
    void testVisitTaskExit() throws IOException {
        setMockInput("0\n");
        modifier.visitTask(task);

        verify(task, never()).setDescription(anyString());
        verify(task, never()).setDeadline(any(LocalDateTime.class));
        verify(modifier, never()).updateIdentifier(any(PIR.class));
    }

    @Test
    void testVisitTaskInvalidInput(){
        setMockInput("4\n");
        modifier.visitTask(task);
        assertTrue(testOut.toString().contains("Invalid input."));
    }

    @AfterEach
    void restoreSystemIn() {
        System.setIn(originalSystemIn);
    }

    @AfterEach
    void tearDown() {
        // 恢复原始值
        if (originalEnvironmentValue != null) {
            System.setProperty("environment", originalEnvironmentValue);
        } else {
            System.clearProperty("environment");
        }
    }
}
