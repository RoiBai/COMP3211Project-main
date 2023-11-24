package hk.edu.polyu.comp.comp3211.group6.Model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class IdCriteriaTest {

    //This function tested Model/IdCriteria getIdentifier()
    @Test
    void testGetIdentifier() {
        String testIdentifier = "id";
        IdCriteria idCriteria = new IdCriteria(testIdentifier);
        assertEquals(testIdentifier, idCriteria.getIdentifier());
    }

    @Test
    void testAcceptMethod() {
        IdCriteria idCriteria = new IdCriteria("id");
        CriteriaVisitor<Object> mockVisitor = mock(CriteriaVisitor.class);
        idCriteria.accept(mockVisitor);
        verify(mockVisitor).visitIdCriteria(idCriteria);
    }
}
