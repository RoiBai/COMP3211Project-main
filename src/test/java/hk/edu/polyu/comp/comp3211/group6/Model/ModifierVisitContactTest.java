package hk.edu.polyu.comp.comp3211.group6.Model;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import org.junit.jupiter.api.*;

import java.io.*;
import java.util.HashMap;
import java.io.ByteArrayOutputStream;

import java.time.LocalDateTime;

class ModifierVisitContactTest {

    private Modifier modifier;
    private Contact contact;
    private final InputStream originalSystemIn = System.in;
    private final PrintStream originalSystemOut = System.out;
    private ByteArrayOutputStream testOut;
    private HashMap<String, PIR> PIRs;
    private String originalEnvironmentValue;

    @BeforeEach
    void setUp() throws IOException {
        modifier = spy(new Modifier(PIRs));
        contact = mock(Contact.class);

        doNothing().when(modifier).updateIdentifier(any(PIR.class));
        doNothing().when(contact).setName(anyString());
        doNothing().when(contact).setAddress(anyString());
        doNothing().when(contact).setMobile(anyString());

        originalEnvironmentValue = System.getProperty("environment");
        System.setProperty("environment", "test");
        testOut = new ByteArrayOutputStream();
        System.setOut(new PrintStream(testOut));
        //String simulatedInput = "1\n0\n";
        //System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));
    }

    void setMockInput(String input) {
        System.setIn(new ByteArrayInputStream(input.getBytes()));
    }

    @Test
    void testVisitContactUpdateIdentifier() {
        String simulatedInput = "1\n";
        setMockInput(simulatedInput);
        modifier.visitContact(contact);

        assertTrue(testOut.toString().contains("Identifier"));
        assertFalse(testOut.toString().contains("Invalid input."));
    }

    @Test
    void testVisitContactUpdateName() {
        String simulatedInput = "2\n";
        setMockInput(simulatedInput);
        modifier.visitContact(contact);

        assertTrue(testOut.toString().contains("Name"));
        assertFalse(testOut.toString().contains("Invalid input."));
    }

    @Test
    void testVisitContactUpdateAddress() {
        String simulatedInput = "3\n";
        setMockInput(simulatedInput);
        modifier.visitContact(contact);

        assertTrue(testOut.toString().contains("Address"));
        assertFalse(testOut.toString().contains("Invalid input."));
    }

    @Test
    void testVisitContactUpdateMobileNumber() {
        String simulatedInput = "4\n";
        setMockInput(simulatedInput);
        modifier.visitContact(contact);

        assertTrue(testOut.toString().contains("Mobile Number"));
        assertFalse(testOut.toString().contains("Invalid input."));
    }

    @Test
    void testVisitContactExit() throws IOException {
        setMockInput("0\n");
        modifier.visitContact(contact);

        verify(contact, never()).setName(anyString());
        verify(contact, never()).setAddress(anyString());
        verify(contact, never()).setMobile(anyString());
        verify(modifier, never()).updateIdentifier(any(PIR.class));
    }

    @Test
    void testVisitContactInvalidInput(){
        setMockInput("5\n");
        modifier.visitContact(contact);
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
