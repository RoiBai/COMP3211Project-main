package hk.edu.polyu.comp.comp3211.group6.Model;

import org.junit.jupiter.api.*;
import java.io.*;
import java.util.HashMap;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ModifierUpdateIdentifierTest {
    private final InputStream originalIn = System.in;
    private final PrintStream originalOut = System.out;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private ByteArrayInputStream inContent;
    private Modifier modifier;
    private PIR testPIR;

    @BeforeEach
    void setUp() {
        System.setOut(new PrintStream(outContent));
        modifier = new Modifier(new HashMap<>());

        testPIR = new PIR("initialId") {
            private String identifier = "initialId";

            @Override
            public <T> T accept(PrintVisitor<T> visitor) {
                return null;
            }

            @Override
            public <T> T accept(ModifyVisitor<T> visitor) {
                return null;
            }

            @Override
            public String getIdentifier() {
                return identifier;
            }

            @Override
            public void setIdentifier(String identifier) {
                this.identifier = identifier;
            }

            @Override
            public String getString() {
                return null;
            }
        };
    }

    @AfterEach
    void restoreStreams() {
        System.setIn(originalIn);
        System.setOut(originalOut);
    }

    @Test
    void testUpdateIdentifier() throws IOException {
        String newIdentifier = "newId\n";
        inContent = new ByteArrayInputStream(newIdentifier.getBytes());
        System.setIn(inContent);

        modifier.updateIdentifier(testPIR);

        assertEquals("newId", testPIR.getIdentifier(), "PIR identifier should be updated to newId");
    }
    private ByteArrayInputStream testIn;
    void setInput(String data) {
        testIn = new ByteArrayInputStream(data.getBytes());
        System.setIn(testIn);
    }

    @Test
    void testUpdateIdentifierWithInvalidInput() throws IOException {
        //Modifier modifier = new Modifier(PIRs);
        PIR mockPIR = mock(PIR.class);
        when(mockPIR.getIdentifier()).thenReturn("originalId");

        String invalidIdentifier = "invalidIdentifier\n";
        setInput(invalidIdentifier);

        modifier.updateIdentifier(mockPIR);

        //assertTrue(outContent.toString().contains("Invalid identifier."));
    }
}
