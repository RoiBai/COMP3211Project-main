package hk.edu.polyu.comp.comp3211.group6.Model;

import hk.edu.polyu.comp.comp3211.group6.Controller.Stmt;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class InterpreterTestA {
    class PIRImpl extends PIR {
        public PIRImpl(String identifier) {
            super(identifier);
        }

        @Override
        public <T> T accept(PrintVisitor<T> visitor) {
            return null;
        }

        @Override
        public <T> T accept(ModifyVisitor<T> visitor) {
            return null;
        }

        @Override
        public String getString() {
            return null;
        }
    }
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
        String simulatedUserInput = "\"Roi Bai\\nSZ\\n555-1234\\n\"";
        inContent = new ByteArrayInputStream(simulatedUserInput.getBytes());
        System.setIn(inContent);

        System.setOut(new PrintStream(outContent));
        interpreter = new Interpreter();
    }
    @AfterEach
    public void restoreStreams() {
        System.setIn(originalIn);
        System.setOut(originalOut);
    }

    @Test
    void testVisitOrCriteria() {
        Criteria leftCriteria = mock(Criteria.class);
        Criteria rightCriteria = mock(Criteria.class);
        OrCriteria orCriteria = new OrCriteria(leftCriteria, rightCriteria);

        when(leftCriteria.accept(interpreter)).thenReturn(new ArrayList<>());
        when(rightCriteria.accept(interpreter)).thenReturn(new ArrayList<>());

        List<PIR> result = interpreter.visitOrCriteria(orCriteria);
        assertTrue(result.isEmpty());
    }

    @Test
    void testInterpret() {
        Stmt mockStmt = mock(Stmt.class);
        interpreter.interpret(mockStmt);
        verify(mockStmt).accept(interpreter);
    }



    @Test
    void testReadLine() throws IOException {
        Interpreter interpreter = new Interpreter();

        String result = interpreter.readLine("Enter something:", false);

        assertEquals("\"Roi Bai\\nSZ\\n555-1234\\n\"", result.trim());

        assertEquals("Enter something:", outContent.toString().trim());
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
}


