package hk.edu.polyu.comp.comp3211.group6.Model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class InterpreterVisitAndTest {

    private Interpreter interpreter;
    @BeforeEach
    void setUp() {

        interpreter = new Interpreter();

    }
    @Test
    void testVisitAndCriteria() {
        Criteria leftCriteria = mock(Criteria.class);
        Criteria rightCriteria = mock(Criteria.class);
        AndCriteria andCriteria = new AndCriteria(leftCriteria, rightCriteria);

        PIR pir1 = mock(PIR.class);
        PIR pir2 =mock(PIR.class);
        PIR pir3 = mock(PIR.class);

        when(leftCriteria.accept(interpreter)).thenReturn(new ArrayList<>(Arrays.asList(pir1, pir2)));
        when(rightCriteria.accept(interpreter)).thenReturn(new ArrayList<>(Arrays.asList(pir2, pir3)));

        List<PIR> result = interpreter.visitAndCriteria(andCriteria);
        assertTrue(result.contains(pir2) && result.size() == 1);
    }
}
