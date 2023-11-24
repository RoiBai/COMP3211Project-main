package hk.edu.polyu.comp.comp3211.group6.Model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AndCriteriaTest {

    //This function tested Model/AndCriteria, getLeft() and getRight()
    @Test
    void testGet() {
        Criteria left = mock(Criteria.class);
        Criteria right = mock(Criteria.class);
        AndCriteria andCriteria = new AndCriteria(left, right);
        assertSame(left, andCriteria.getLeft());
        assertSame(right, andCriteria.getRight());
    }

    @Test
    void testAccept() {
        Criteria left = mock(Criteria.class);
        Criteria right = mock(Criteria.class);
        CriteriaVisitor<Object> visitor = mock(CriteriaVisitor.class);
        AndCriteria andCriteria = new AndCriteria(left, right);
        andCriteria.accept(visitor);
        verify(visitor).visitAndCriteria(andCriteria);
    }
}
