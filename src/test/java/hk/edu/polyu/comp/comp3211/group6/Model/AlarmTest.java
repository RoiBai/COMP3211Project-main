package hk.edu.polyu.comp.comp3211.group6.Model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.*;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class AlarmTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @BeforeEach
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    public void restoreStreams() {
        System.setOut(originalOut);
    }

    //This function tested setAlarmMessage() and getAlarmMessage() in Model/Alarm
    @Test
    public void testSetAndGetAlarmMessage() {
        String testMessage = "Test Alarm Message";
        Alarm alarm = new Alarm(testMessage);
        assertEquals(testMessage, alarm.getAlarmMessage());
    }

    //This function tested setAlarmMessage() in Model/Alarm, and check whether can change alarm message
    @Test
    public void testSetAlarmMessage() {
        String initialMessage = "Initial Message";
        String newMessage = "New Alarm Message";
        Alarm alarm = new Alarm(initialMessage);
        alarm.setAlarmMessage(newMessage);
        assertEquals(newMessage, alarm.getAlarmMessage());
    }

    //This function tested run() in Model/Alarm
    @Test
    public void testRunOutputsMessage() {
        String testMessage = "Test Alarm";
        Alarm alarm = new Alarm(testMessage);
        alarm.run();
        String expectedOutput = testMessage;
        assertEquals(expectedOutput, outContent.toString());
    }
}

