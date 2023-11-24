package hk.edu.polyu.comp.comp3211.group6.Model;

import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class StringCriteriaTest {

    //This function tested Model/StringCriteria, getQuery()
    @Test
    void testGet() {
        String tester = "Test query";
        StringCriteria testString = new StringCriteria(tester);
        assertEquals(tester, testString.getQuery());
    }

    //This function tested Model/StringCriteria, accept()
    @Test
    void testAccept() {
        String tester = "Test query";
        StringCriteria testString = new StringCriteria(tester);
        CriteriaVisitor<Object> mockVisitor = mock(CriteriaVisitor.class);
        testString.accept(mockVisitor);

        verify(mockVisitor).visitStringCriteria(testString);
    }
}
