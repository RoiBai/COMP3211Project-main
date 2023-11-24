package hk.edu.polyu.comp.comp3211.group6.Model;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class InterpreterVisitOrCriteriaTest {
    @Test
    void testVisitOrCriteria() {
        // 创建并模拟 OrCriteria 对象
        OrCriteria orCriteria = mock(OrCriteria.class);
        Criteria leftCriteria = mock(Criteria.class);
        Criteria rightCriteria = mock(Criteria.class);
        when(orCriteria.getLeft()).thenReturn(leftCriteria);
        when(orCriteria.getRight()).thenReturn(rightCriteria);

        // 创建 Interpreter 实例
        Interpreter interpreter = new Interpreter();

        // 模拟 evaluate 方法
        PIR pir1 = mock(PIR.class);
        PIR pir2 = mock(PIR.class);
        PIR pir3 = mock(PIR.class);
        when(interpreter.evaluate(leftCriteria)).thenReturn(new ArrayList<>(Arrays.asList(pir1, pir2)));
        when(interpreter.evaluate(rightCriteria)).thenReturn(new ArrayList<>(Arrays.asList(pir2, pir3)));


        // 调用 visitOrCriteria 方法
        List<PIR> result = interpreter.visitOrCriteria(orCriteria);

        // 验证结果
        assertEquals(3, result.size()); // 确保列表包含3个唯一对象
        assertTrue(result.containsAll(Arrays.asList(pir1, pir2, pir3)));
    }
}
