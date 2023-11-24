package hk.edu.polyu.comp.comp3211.group6.Model;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import hk.edu.polyu.comp.comp3211.group6.Controller.PIMError;
import hk.edu.polyu.comp.comp3211.group6.Controller.SearchStmt;
import hk.edu.polyu.comp.comp3211.group6.View.Printer;
import org.junit.jupiter.api.Test;
import java.util.*;

public class InterpreterVisitSearchStmtTest {
    @Test
    void testVisitSearchStmtWithEmptyResult() {
        SearchStmt stmt = mock(SearchStmt.class);
        Criteria criteria = mock(Criteria.class);
        when(stmt.getCriteria()).thenReturn(criteria);

        Interpreter interpreter = new Interpreter();
        when(interpreter.evaluate(criteria)).thenReturn(Collections.emptyList());

        assertThrows(PIMError.class, () -> interpreter.visitSearchStmt(stmt));
    }

    @Test
    void testVisitSearchStmtWithNonEmptyResult() {
        SearchStmt stmt = mock(SearchStmt.class);
        Criteria criteria = mock(Criteria.class);
        when(stmt.getCriteria()).thenReturn(criteria);

        PIR pir1 = mock(PIR.class);
        when(pir1.getIdentifier()).thenReturn("pir1");
        List<PIR> searchRes = Arrays.asList(pir1);

        Interpreter interpreter = new Interpreter();
        when(interpreter.evaluate(criteria)).thenReturn(searchRes);

        Printer mockPrinter = mock(Printer.class);
        // 假设您可以注入或以其他方式提供 Printer 的实例
        //interpreter.setTestPrinter(mockPrinter);

        interpreter.visitSearchStmt(stmt);


    }
}
