package hk.edu.polyu.comp.comp3211.group6.Model;

import static org.junit.jupiter.api.Assertions.*;

import hk.edu.polyu.comp.comp3211.group6.Controller.Token;
import hk.edu.polyu.comp.comp3211.group6.Controller.TokenType;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;
import org.junit.jupiter.api.Test;

class TypeCriteriaTest {

    @Test
    void testTypeCriteriaConstructionAndGetter() {
        Token testType = new Token(TokenType.EQUAL, "EQUAL",null);
        TypeCriteria typeCriteria = new TypeCriteria(testType);

        assertEquals(testType, typeCriteria.getType());
    }

    @SuppressWarnings("unchecked")
    @Test
    void testAccept() {
        Token testType = new Token(TokenType.EQUAL, "EQUAL",null);
        TypeCriteria typeCriteria = new TypeCriteria(testType);

        CriteriaVisitor<String> mockVisitor = (CriteriaVisitor<String>) mock(CriteriaVisitor.class);
        when(mockVisitor.visitTypeCriteria(typeCriteria)).thenReturn("Test Result");

        String result = typeCriteria.accept(mockVisitor);

        verify(mockVisitor, times(1)).visitTypeCriteria(typeCriteria);
        assertEquals("Test Result", result);
    }
}

