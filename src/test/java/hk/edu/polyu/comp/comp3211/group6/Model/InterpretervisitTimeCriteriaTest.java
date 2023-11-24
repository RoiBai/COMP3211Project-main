package hk.edu.polyu.comp.comp3211.group6.Model;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import hk.edu.polyu.comp.comp3211.group6.Controller.PIMError;
import hk.edu.polyu.comp.comp3211.group6.Controller.Token;
import hk.edu.polyu.comp.comp3211.group6.Controller.TokenType;
import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

class InterpreterTest {

    @Test
    void testVisitTimeCriteriaBefore() {
        TimeCriteria timeCriteria = mock(TimeCriteria.class);
        Token operator = new Token(TokenType.BEFORE, "BEFORE",null);
        LocalDateTime queryTime = LocalDateTime.now();
        when(timeCriteria.getOperator()).thenReturn(operator);
        when(timeCriteria.getDateTime()).thenReturn(queryTime);

        PIR pir1 = mock(PIR.class, withSettings().extraInterfaces(HasTime.class));
        PIR pir2 = mock(PIR.class);
        when(((HasTime) pir1).getTime()).thenReturn(Arrays.asList(queryTime.minusDays(1)));

        HashMap<String, PIR> PIRs = new HashMap<>();
        PIRs.put("pir1", pir1);
        PIRs.put("pir2", pir2);

        Interpreter interpreter = new Interpreter();
        interpreter.setInterpreterPIRs(PIRs);

        List<PIR> result = interpreter.visitTimeCriteria(timeCriteria);

        assertTrue(result.contains(pir1));
        assertFalse(result.contains(pir2));
    }
    @Test
    void testVisitTimeCriteriaAfter() {
        TimeCriteria timeCriteria = mock(TimeCriteria.class);
        Token operator = new Token(TokenType.AFTER, "AFTER",null);
        LocalDateTime queryTime = LocalDateTime.now();
        when(timeCriteria.getOperator()).thenReturn(operator);
        when(timeCriteria.getDateTime()).thenReturn(queryTime);

        PIR pir1 = mock(PIR.class, withSettings().extraInterfaces(HasTime.class));
        when(((HasTime) pir1).getTime()).thenReturn(Arrays.asList(queryTime.plusDays(1)));

        HashMap<String, PIR> PIRs = new HashMap<>();
        PIRs.put("pir1", pir1);

        Interpreter interpreter = new Interpreter();
        interpreter.setInterpreterPIRs(PIRs);

        List<PIR> result = interpreter.visitTimeCriteria(timeCriteria);

        assertTrue(result.contains(pir1));
    }
    @Test
    void testVisitTimeCriteriaEqual() {
        TimeCriteria timeCriteria = mock(TimeCriteria.class);
        Token operator = new Token(TokenType.EQUAL, "EQUAL",null);
        LocalDateTime queryTime = LocalDateTime.now();
        when(timeCriteria.getOperator()).thenReturn(operator);
        when(timeCriteria.getDateTime()).thenReturn(queryTime);

        PIR pir1 = mock(PIR.class, withSettings().extraInterfaces(HasTime.class));
        when(((HasTime) pir1).getTime()).thenReturn(Arrays.asList(queryTime));

        HashMap<String, PIR> PIRs = new HashMap<>();
        PIRs.put("pir1", pir1);

        Interpreter interpreter = new Interpreter();
        interpreter.setInterpreterPIRs(PIRs);

        List<PIR> result = interpreter.visitTimeCriteria(timeCriteria);

        assertTrue(result.contains(pir1));
    }

}
