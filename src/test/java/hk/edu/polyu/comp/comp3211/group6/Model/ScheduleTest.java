package hk.edu.polyu.comp.comp3211.group6.Model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.lang.reflect.Field;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Timer;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;


class ScheduleTest {

    //This function tested Model/Schedule, getDescription(), getStartTime() and getAlarmTime()
    @Test
    void testGet() {
        LocalDateTime testStartTime = LocalDateTime.now().plusDays(1);
        LocalDateTime testAlarmTime = testStartTime.minusHours(1);
        Schedule schedule = new Schedule("id", "Test description", testStartTime, testAlarmTime);
        //doNothing().when(schedule).setTimer();
        assertEquals("Test description", schedule.getDescription());
        assertEquals(testStartTime, schedule.getStartTime());
        assertEquals(testAlarmTime, schedule.getAlarmTime());
    }

    @Test
    void testGetTime() {
        LocalDateTime startTime = LocalDateTime.now().plusDays(2);
        LocalDateTime alarmTime = startTime.minusHours(5);
        Schedule schedule = new Schedule("id", "description", startTime, alarmTime);
        //doNothing().when(schedule).setTimer();
        List<LocalDateTime> times = schedule.getTime();
        assertEquals(2, times.size());
        assertTrue(times.contains(startTime));
        assertTrue(times.contains(alarmTime));
    }

    //This function tested Model/Schedule, setStartTime() and setDescription()
    @Test
    void testSet() {
        LocalDateTime initialStartTime = LocalDateTime.now().plusDays(1);
        LocalDateTime newStartTime = initialStartTime.plusHours(2);
        Schedule schedule = new Schedule("id", "Test Schedule", initialStartTime, initialStartTime.minusHours(1));
        //doNothing().when(schedule).setTimer();
        schedule.setStartTime(newStartTime);
        schedule.setDescription("new description");
        assertEquals(newStartTime, schedule.getStartTime());
        assertEquals("new description",schedule.getDescription());
    }

    @Test
    void testSetAlarmTime() {
        LocalDateTime initialAlarmTime = LocalDateTime.now().plusDays(5);
        LocalDateTime newAlarmTime = initialAlarmTime.plusHours(1);
        Schedule schedule = spy(new Schedule("id", "description", LocalDateTime.now().plusDays(8), initialAlarmTime));

        doNothing().when(schedule).setTimer();

        schedule.setAlarmTime(newAlarmTime);
        assertEquals(newAlarmTime, schedule.getAlarmTime());
    }

    @Test
    void testGetString() {
        LocalDateTime startTime = LocalDateTime.now().plusDays(1);
        LocalDateTime alarmTime = startTime.minusHours(1);
        Schedule schedule = new Schedule("id", "Test Schedule", startTime, alarmTime);
        //doNothing().when(schedule).setTimer();
        assertEquals("Test Schedule", schedule.getString());
    }

    @Test
    void testToString() {
        String identifier = "id";
        String description = "des";
        LocalDateTime startTime = LocalDateTime.of(2028, 11, 28, 12, 12);
        LocalDateTime alarmTime = startTime.minusHours(1);
        Schedule schedule = new Schedule(identifier, description, startTime, alarmTime);
        //doNothing().when(schedule).setTimer();
        String expected = "id" + "\n" + "des" + "\n" + startTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd,HH:mm")) + "\n" + alarmTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd,HH:mm"));

        assertEquals(expected, schedule.toString());
    }

    @Test
    void testAcceptPrintVisitor() {
        Schedule schedule = spy(new Schedule("id", "description", LocalDateTime.now().plusDays(2), LocalDateTime.now().plusDays(1)));
        doNothing().when(schedule).setTimer();
        PrintVisitor<Void> mockPrintVisitor = mock(PrintVisitor.class);

        schedule.accept(mockPrintVisitor);

        verify(mockPrintVisitor, times(1)).visitSchedule(schedule);
    }

}
