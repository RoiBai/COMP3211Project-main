package hk.edu.polyu.comp.comp3211.group6.Model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class InterpreterCreateContactTest {
    private Interpreter interpreter;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final InputStream originalIn = System.in;
    private final PrintStream originalOut = System.out;
    private ByteArrayInputStream inContent = null;

    @BeforeEach
    void setUp() {
        //pirMap = new HashMap<>();
        interpreter = new Interpreter();
        String simulatedUserInput = "\"Roi Bai\\nSZ\\n555-1234\\n\"";
        inContent = new ByteArrayInputStream(simulatedUserInput.getBytes());
        System.setIn(inContent);

        System.setOut(new PrintStream(outContent));
        interpreter = new Interpreter();
    }


    @Test
    void testCreateContact() throws Exception {
        Method method = Interpreter.class.getDeclaredMethod("createContactInterpreter", String.class);
        method.setAccessible(true);
        method.invoke(interpreter, "RB");

        Field field = Interpreter.class.getDeclaredField("PIRs");
        field.setAccessible(true);
        HashMap<String, PIR> PIRs = (HashMap<String, PIR>) field.get(interpreter);

        assertTrue(PIRs.containsKey("RB"));
        PIR pir = PIRs.get("RB");
        assertTrue(pir instanceof Contact);
        Contact contact = (Contact) pir;
        assertEquals("RB", contact.getIdentifier());
    }
    @AfterEach
    public void restoreStreams() {
        System.setIn(originalIn);
        System.setOut(originalOut);
    }
}
