package hk.edu.polyu.comp.comp3211.group6.Model;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import org.junit.jupiter.api.*;

import java.io.*;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.io.ByteArrayOutputStream;

public class ModifierVisitScheduleTest {
    private Modifier modifier;
    private Schedule schedule;
    private final InputStream originalSystemIn = System.in;
    private final PrintStream originalSystemOut = System.out;
    private ByteArrayOutputStream testOut;
    private HashMap<String, PIR> PIRs;
    private String originalEnvironmentValue;

    @BeforeEach
    void setUp() throws IOException {
        modifier = spy(new Modifier(PIRs));
        schedule = mock(Schedule.class);

        doNothing().when(modifier).updateIdentifier(any(PIR.class));
        doNothing().when(schedule).setDescription(anyString());
        doNothing().when(schedule).setStartTime(any(LocalDateTime.class));
        doNothing().when(schedule).setAlarmTime(any(LocalDateTime.class));

        originalEnvironmentValue = System.getProperty("environment");
        System.setProperty("environment", "test");
        testOut = new ByteArrayOutputStream();
        System.setOut(new PrintStream(testOut));
    }

    void setMockInput(String input) {
        System.setIn(new ByteArrayInputStream(input.getBytes()));
    }

    @Test
    void testVisitScheduleUpdateIdentifier() {
        String simulatedInput = "1\n";
        setMockInput(simulatedInput);
        modifier.visitSchedule(schedule);

        assertTrue(testOut.toString().contains("Identifier"));
        assertFalse(testOut.toString().contains("Invalid input."));
    }

    @Test
    void testVisitScheduleUpdateDescription() {
        String simulatedInput = "2\n";
        setMockInput(simulatedInput);
        modifier.visitSchedule(schedule);

        assertTrue(testOut.toString().contains("Description"));
        assertFalse(testOut.toString().contains("Invalid input."));
    }

    @Test
    void testVisitScheduleUpdateStartTime() {
        String simulatedInput = "3\n";
        setMockInput(simulatedInput);
        modifier.visitSchedule(schedule);

        assertTrue(testOut.toString().contains("Start time"));
        assertFalse(testOut.toString().contains("Invalid input."));
    }

    @Test
    void testVisitScheduleUpdateAlarmTime() {
        String simulatedInput = "4\n";
        setMockInput(simulatedInput);
        modifier.visitSchedule(schedule);

        assertTrue(testOut.toString().contains("Alarm time"));
        assertFalse(testOut.toString().contains("Invalid input."));
    }

    @Test
    void testVisitContactExit() throws IOException {
        setMockInput("0\n");
        modifier.visitSchedule(schedule);

        verify(schedule, never()).setDescription(anyString());
        verify(schedule, never()).setStartTime(any(LocalDateTime.class));
        verify(schedule, never()).setAlarmTime(any(LocalDateTime.class));
        verify(modifier, never()).updateIdentifier(any(PIR.class));
    }
    @Test
    void testVisitContactInvalidInput(){
        setMockInput("5\n");
        modifier.visitSchedule(schedule);
        assertTrue(testOut.toString().contains("Invalid input."));
    }
    @AfterEach
    void restoreSystemIn() {
        System.setIn(originalSystemIn);
    }

    @AfterEach
    void tearDown() {
        if (originalEnvironmentValue != null) {
            System.setProperty("environment", originalEnvironmentValue);
        } else {
            System.clearProperty("environment");
        }
    }
}
