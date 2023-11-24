package hk.edu.polyu.comp.comp3211.group6.Model;

import org.junit.jupiter.api.*;
import java.lang.reflect.Method;
import java.util.HashMap;
import static org.junit.jupiter.api.Assertions.*;

class ModifierUpdatePIRTest {

    private Modifier modifier;
    private HashMap<String, PIR> PIRs;
    private PIR testPIR;

    @BeforeEach
    void setUp() throws Exception {
        PIRs = new HashMap<>();
        modifier = new Modifier(PIRs);

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
        PIRs.put("initialId", testPIR);
    }

    @Test
    void testUpdatePIR() throws Exception {
        Method method = Modifier.class.getDeclaredMethod("updatePIR", String.class, PIR.class);
        method.setAccessible(true);

        testPIR.setIdentifier("newId");
        method.invoke(modifier, "initialId", testPIR);

        assertFalse(PIRs.containsKey("initialId"), "PIRs should no longer contain the old identifier");
        assertTrue(PIRs.containsKey("newId"), "PIRs should contain the new identifier");
        assertSame(testPIR, PIRs.get("newId"), "The PIR object should be associated with the new identifier");
    }
}
