package hk.edu.polyu.comp.comp3211.group6.Model;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import org.junit.jupiter.api.*;

import java.io.*;
import java.util.HashMap;
import java.io.ByteArrayOutputStream;

class ModifierVisitNoteTest {

    private final InputStream originalSystemIn = System.in;
    private ByteArrayOutputStream testOut;
    private HashMap<String, PIR> PIRs;
    private String originalEnvironmentValue;
    private Modifier modifier;
    private Note testNote;

    @BeforeEach
    void setUp() throws IOException {
        modifier = spy(new Modifier(PIRs));
        testNote = mock(Note.class);

        doNothing().when(modifier).updateIdentifier(any(PIR.class));
        doNothing().when(testNote).setText(anyString());

        originalEnvironmentValue = System.getProperty("environment");
        System.setProperty("environment", "test");
        testOut = new ByteArrayOutputStream();
        System.setOut(new PrintStream(testOut));
    }

    void setMockInput(String input) {
        System.setIn(new ByteArrayInputStream(input.getBytes()));
    }

    @Test
    void testVisitNoteIdentifier(){
        String simulatedInput = "1\n";
        setMockInput(simulatedInput);
        modifier.visitNote(testNote);
        assertTrue(testOut.toString().contains("Identifier"));
        assertFalse(testOut.toString().contains("Invalid input."));
    }

    @Test
    void testVisitNoteText(){
        String simulatedInput = "2\n";
        setMockInput(simulatedInput);
        modifier.visitNote(testNote);
        assertTrue(testOut.toString().contains("Text"));
        assertFalse(testOut.toString().contains("Invalid input."));
    }

    @Test
    void testVisitNoteExit() throws IOException {
        setMockInput("0\n");
        modifier.visitNote(testNote);

        verify(testNote, never()).setText(anyString());
        verify(modifier, never()).updateIdentifier(any(PIR.class));
    }

    @Test
    void testVisitNoteInvalidInput() {
        setMockInput("3\n");
        modifier.visitNote(testNote);
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
