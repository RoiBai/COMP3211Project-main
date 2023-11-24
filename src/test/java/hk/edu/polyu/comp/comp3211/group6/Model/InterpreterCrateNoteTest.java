package hk.edu.polyu.comp.comp3211.group6.Model;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;
import java.io.*;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;

public class InterpreterCrateNoteTest {
    private final InputStream originalIn = System.in;
    private final PrintStream originalOut = System.out;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private ByteArrayInputStream inContent = null;


    private Interpreter interpreter;
    private HashMap<String, PIR> pirMap;

    @BeforeEach
    void setUp() {
        pirMap = new HashMap<>();
        interpreter = new Interpreter();
        String simulatedUserInput = "\"software engi\\n\"";
        inContent = new ByteArrayInputStream(simulatedUserInput.getBytes());
        System.setIn(inContent);

        System.setOut(new PrintStream(outContent));
        interpreter = new Interpreter();
    }

    @Test
    void testCreateNote() throws Exception {
        Method method = Interpreter.class.getDeclaredMethod("createNoteInterpreter", String.class);
        method.setAccessible(true);
        method.invoke(interpreter, "project");

        Field field = Interpreter.class.getDeclaredField("PIRs");
        field.setAccessible(true);
        HashMap<String, PIR> PIRs = (HashMap<String, PIR>) field.get(interpreter);

        assertTrue(PIRs.containsKey("project"));
        PIR pir = PIRs.get("project");
        assertTrue(pir instanceof Note);
        Note note = (Note) pir;
        assertEquals("project", note.getIdentifier());
    }

    @AfterEach
    public void restoreStreams() {
        System.setIn(originalIn);
        System.setOut(originalOut);
    }
}
