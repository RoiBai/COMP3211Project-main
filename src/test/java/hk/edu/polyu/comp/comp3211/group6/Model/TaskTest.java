package hk.edu.polyu.comp.comp3211.group6.Model;

import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import java.time.format.DateTimeFormatter;
import java.util.List;


class TaskTest {
    //This function tested Model/Task,getIdentifier(), getDescription() and getDeadline()
    @Test
    void testGet() {
        LocalDateTime testDeadline = LocalDateTime.now();
        String testIdentifier = "TestTask";
        String testDescription = "Test description";
        Task task = new Task(testIdentifier, testDescription, testDeadline);

        assertEquals(testIdentifier, task.getIdentifier());
        assertEquals(testDescription, task.getDescription());
        assertEquals(testDeadline, task.getDeadline());
    }

    //This function tested Model/Task, setDescription(), setDeadline()
    @Test
    void testSet() {
        Task task = new Task("TestTask", "Test description", LocalDateTime.now());
        String newDescription = "Updated description";
        task.setDescription(newDescription);
        assertEquals(newDescription, task.getDescription());

        LocalDateTime newDeadline = LocalDateTime.now().plusDays(1);
        task.setDeadline(newDeadline);
        assertEquals(newDeadline, task.getDeadline());
    }

    //This function tested Model/Task, getString()
    @Test
    void testGetString() {
        Task task = new Task("id", "Test description", LocalDateTime.now());
        assertEquals("Test description", task.getString());
    }

    //This function tested Model/Task, toString()
    @Test
    void testToString() {
        LocalDateTime testDeadline = LocalDateTime.now();
        Task task = new Task("Task1", "Test Task", testDeadline);
        String expectedString = "Task1\nTest Task\n" + testDeadline.format(DateTimeFormatter.ofPattern("yyyy-MM-dd,HH:mm"));
        assertEquals(expectedString, task.toString());
    }

    //This function tested Model/Task, accept()
    @Test
    void testAcceptPrintVisitor() {
        Task task = new Task("id", "Test description", LocalDateTime.now());
        PrintVisitor<Object> mockPrintVisitor = mock(PrintVisitor.class);
        task.accept(mockPrintVisitor);
        verify(mockPrintVisitor).visitTask(task);
    }

    //This function tested Model/Task, accept()
    @Test
    void testAcceptModifyVisitor() {
        Task task = new Task("id", "Test description", LocalDateTime.now());
        ModifyVisitor<Object> mockModifyVisitor = mock(ModifyVisitor.class);
        task.accept(mockModifyVisitor);
        verify(mockModifyVisitor).visitTask(task);
    }

    //This function tested Model/Task, getTime()
    @Test
    void testGetTime() {
        LocalDateTime testDeadline = LocalDateTime.of(2022, 1, 1, 12, 0);
        Task task = new Task("id", "Test description", testDeadline);
        List<LocalDateTime> time = task.getTime();
        assertNotNull(time, "Time list should not be null");
        assertTrue(time.contains(testDeadline), "Time list should contain the deadline");
    }
}

