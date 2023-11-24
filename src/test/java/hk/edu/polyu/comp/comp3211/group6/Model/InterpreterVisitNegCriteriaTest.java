package hk.edu.polyu.comp.comp3211.group6.Model;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class InterpreterVisitNegCriteriaTest {
    @Test
    void testVisitNegCriteria() {
        NegCriteria negCriteria = mock(NegCriteria.class);
        Criteria rightCriteria = mock(Criteria.class);
        when(negCriteria.getRight()).thenReturn(rightCriteria);

        PIR pir1 = mock(PIR.class);
        PIR pir2 = mock(PIR.class);
        when(pir1.getIdentifier()).thenReturn("pir1");
        when(pir2.getIdentifier()).thenReturn("pir2");

        Interpreter interpreter = new Interpreter();
        when(interpreter.evaluate(rightCriteria)).thenReturn(List.of(pir1));

        HashMap<String, PIR> PIRs = new HashMap<>();
        PIRs.put("pir1", pir1);
        PIRs.put("pir2", pir2);
        interpreter.setInterpreterPIRs(PIRs);

        List<PIR> result = interpreter.visitNegCriteria(negCriteria);

        assertFalse(result.contains(pir1));
        assertTrue(result.contains(pir2));
    }
}
