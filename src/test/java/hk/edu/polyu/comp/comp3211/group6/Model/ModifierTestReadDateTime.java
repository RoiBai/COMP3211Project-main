package hk.edu.polyu.comp.comp3211.group6.Model;
import org.junit.jupiter.api.*;
import java.io.*;
import java.lang.reflect.Method;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.*;

class ModifierTestReadDateTime {
    private final InputStream originalIn = System.in;
    private final PrintStream originalOut = System.out;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private ByteArrayInputStream inContent;
    private Modifier modifier;
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd,HH:mm");

    @BeforeEach
    void setUp() {
        System.setOut(new PrintStream(outContent));
        modifier = new Modifier(null);
    }

    @AfterEach
    void restoreStreams() {
        System.setIn(originalIn);
        System.setOut(originalOut);
    }

    @Test
    void testReadDateTime() throws Exception {
        String simulatedDateTime = "2023-11-22,10:00\n";
        inContent = new ByteArrayInputStream(simulatedDateTime.getBytes());
        System.setIn(inContent);

        Method method = Modifier.class.getDeclaredMethod("readDateTime", String.class);
        method.setAccessible(true);
        LocalDateTime result = (LocalDateTime) method.invoke(modifier, "Enter datetime: ");

        LocalDateTime expectedDateTime = LocalDateTime.parse("2023-11-22T10:00", DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        assertEquals(expectedDateTime, result);
    }
}
