package hk.edu.polyu.comp.comp3211.group6.Model;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class InterpreterVisitStringCriteriaTest {
    @Test
    void testVisitStringCriteria() {
        StringCriteria stringCriteria = mock(StringCriteria.class);
        when(stringCriteria.getQuery()).thenReturn("TestQuery");

        PIR pir1 = mock(PIR.class);
        PIR pir2 = mock(PIR.class);
        when(pir1.getString()).thenReturn("TestQuery String");
        when(pir2.getString()).thenReturn("Other String");

        HashMap<String, PIR> PIRs = new HashMap<>();
        PIRs.put("pir1", pir1);
        PIRs.put("pir2", pir2);

        Interpreter interpreter = new Interpreter();
        interpreter.setInterpreterPIRs(PIRs);

        List<PIR> result = interpreter.visitStringCriteria(stringCriteria);

        assertTrue(result.contains(pir1));
        assertFalse(result.contains(pir2));
    }
}
