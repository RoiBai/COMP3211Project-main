package hk.edu.polyu.comp.comp3211.group6.Model;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;
import java.io.*;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;

class InterpreterCreateTaskTest {

    private Interpreter interpreter;
    private final InputStream originalSystemIn = System.in;
    private final PrintStream originalSystemOut = System.out;
    private ByteArrayInputStream inContent = null;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private ByteArrayOutputStream testOut;
    private String originalEnvironmentValue;

    @BeforeEach
    void setUp() {
        interpreter = new Interpreter();
        // 如果有必要，初始化 PIRs 集合
        interpreter.setInterpreterPIRs(new HashMap<>());
        String simulatedUserInput = "3211\n2023-11-25,23:59\n";
        inContent = new ByteArrayInputStream(simulatedUserInput.getBytes());
        System.setIn(inContent);

        originalEnvironmentValue = System.getProperty("environment");
        System.setProperty("environment", "test");
        System.setOut(new PrintStream(outContent));
    }

    @Test
    void testCreateTask() throws Exception {
        Method method = Interpreter.class.getDeclaredMethod("createTaskInterpreter", String.class);
        method.setAccessible(true);
        method.invoke(interpreter, "RB");

        Field field = Interpreter.class.getDeclaredField("PIRs");
        field.setAccessible(true);
        HashMap<String, PIR> PIRs = (HashMap<String, PIR>) field.get(interpreter);

        assertTrue(PIRs.containsKey("RB"));
        PIR pir = PIRs.get("RB");
        assertTrue(pir instanceof Task);
        Task task = (Task) pir;
        assertEquals("RB", task.getIdentifier());
    }

    @AfterEach
    void restoreSystemIn() {
        System.setIn(originalSystemIn);
        System.setOut(originalSystemOut);
        outContent.reset();
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
