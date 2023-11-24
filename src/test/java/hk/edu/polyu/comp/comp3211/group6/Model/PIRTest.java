package hk.edu.polyu.comp.comp3211.group6.Model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PIRTest {
    //Because of PIR is a abstract class so we need to create the PIRImpl to extend the PIR class
    private class PIRImpl extends PIR {
        public PIRImpl(String identifier) {
            super(identifier);
        }

        @Override
        public <T> T accept(PrintVisitor<T> visitor) {
            return null;
        }

        @Override
        public <T> T accept(ModifyVisitor<T> visitor) {
            return null;
        }

        @Override
        public String getString() {
            return getIdentifier();
        }
    }
    @Test
    void testSetIdentifier() {
        PIR pir = new PIRImpl("id");
        String newId = "update";

        pir.setIdentifier(newId);
        assertEquals(newId, pir.getIdentifier());
    }
}
