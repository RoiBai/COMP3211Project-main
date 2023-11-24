package hk.edu.polyu.comp.comp3211.group6.Model;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;
import java.io.*;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;

public class InterpreterCreateScheduleTest {
    private Interpreter interpreter;
    private final InputStream originalSystemIn = System.in;
    private final PrintStream originalSystemOut = System.out;
    private ByteArrayInputStream inContent = null;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private ByteArrayOutputStream testOut;
    private String originalEnvironmentValue;
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd,HH:mm");


    @BeforeEach
    void setUp() {
        interpreter = new Interpreter();
        interpreter.setInterpreterPIRs(new HashMap<>());
        String simulatedUserInput = "3211\n2023-11-25,23:59\n2023-11-24,23:59\n";
        inContent = new ByteArrayInputStream(simulatedUserInput.getBytes());
        System.setIn(inContent);

        originalEnvironmentValue = System.getProperty("environment");
        System.setProperty("environment", "test");
        System.setOut(new PrintStream(outContent));
    }

    @Test
    void testCreateScheduleInterpreter() throws Exception {
        String identifier = "schedule1";
        interpreter.createScheduleInterpreter(identifier);

        HashMap<String, PIR> PIRs = interpreter.getInterpreterPIRs();
        assertTrue(PIRs.containsKey(identifier));
        PIR pir = PIRs.get(identifier);
        assertNotNull(pir);
        assertTrue(pir instanceof Schedule);
        Schedule schedule = (Schedule) pir;
        assertEquals("schedule1", schedule.getIdentifier());
        assertEquals("3211", schedule.getDescription());
        assertEquals(LocalDateTime.parse("2023-11-25,23:59", formatter), schedule.getStartTime());
        assertEquals(LocalDateTime.parse("2023-11-24,23:59", formatter), schedule.getAlarmTime());
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
