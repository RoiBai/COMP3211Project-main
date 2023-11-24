package hk.edu.polyu.comp.comp3211.group6.Model;

import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class OrCriteriaTest {

    //This function tested Model/OrCriteria, getLeft() and getRight()
    @Test
    void testGet() {
        Criteria left = mock(Criteria.class);
        Criteria right = mock(Criteria.class);
        OrCriteria orC = new OrCriteria(left, right);
        assertSame(left, orC.getLeft());
        assertSame(right, orC.getRight());
    }

    //This function tested Model/OrCriteria, accept()
    @Test
    void testAccept() {
        Criteria left = mock(Criteria.class);
        Criteria right = mock(Criteria.class);
        CriteriaVisitor<Object> mockVisitor = mock(CriteriaVisitor.class);
        OrCriteria orC = new OrCriteria(left, right);
        orC.accept(mockVisitor);
        verify(mockVisitor).visitOrCriteria(orC);
    }
}
