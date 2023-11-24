package hk.edu.polyu.comp.comp3211.group6.Model;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import hk.edu.polyu.comp.comp3211.group6.Controller.Token;
import hk.edu.polyu.comp.comp3211.group6.Controller.TokenType;
import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;

class TimeCriteriaTest {

    @Test
    void testTimeCriteriaConstructionAndAccessors() {
        Token testOperator = new Token(TokenType.BEFORE, "BEFORE",null);
        LocalDateTime testDateTime = LocalDateTime.now();

        TimeCriteria timeCriteria = new TimeCriteria(testOperator, testDateTime);

        assertEquals(testOperator, timeCriteria.getOperator());
        assertEquals(testDateTime, timeCriteria.getDateTime());
    }

    @SuppressWarnings("unchecked")
    @Test
    void testAccept() {
        Token testOperator = new Token(TokenType.BEFORE, "BEFORE",null);
        LocalDateTime testDateTime = LocalDateTime.now();
        TimeCriteria timeCriteria = new TimeCriteria(testOperator, testDateTime);

        CriteriaVisitor<String> mockVisitor = (CriteriaVisitor<String>) mock(CriteriaVisitor.class);
        when(mockVisitor.visitTimeCriteria(timeCriteria)).thenReturn("Test Result");

        String result = timeCriteria.accept(mockVisitor);

        verify(mockVisitor, times(1)).visitTimeCriteria(timeCriteria);
        assertEquals("Test Result", result);
    }
}

