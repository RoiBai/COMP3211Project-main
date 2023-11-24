package hk.edu.polyu.comp.comp3211.group6.Model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class NegCriteriaTest {

    //This function tested Model/NegCriteria, getRight()
    @Test
    void testNegCriteriaConstructorAndGetRight() {
        Criteria tester = mock(Criteria.class);
        NegCriteria negCriteria = new NegCriteria(tester);
        assertSame(tester, negCriteria.getRight());
    }

    //This function tested Model/NegCriteria, accept()
    @Test
    void testAccept() {
        Criteria tester = mock(Criteria.class);
        CriteriaVisitor<Object> mockVisitor = mock(CriteriaVisitor.class);
        NegCriteria negCriteria = new NegCriteria(tester);
        negCriteria.accept(mockVisitor);
        verify(mockVisitor).visitNegCriteria(negCriteria);
    }
}
