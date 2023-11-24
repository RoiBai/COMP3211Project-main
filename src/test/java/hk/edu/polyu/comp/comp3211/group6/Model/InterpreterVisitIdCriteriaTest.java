package hk.edu.polyu.comp.comp3211.group6.Model;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class InterpreterVisitIdCriteriaTest {
    @Test
    void testVisitIdCriteria() {
        // 创建模拟的 IdCriteria 对象
        IdCriteria idCriteria = mock(IdCriteria.class);
        when(idCriteria.getIdentifier()).thenReturn("testId");

        // 创建模拟的 PIR 对象
        PIR pir1 = mock(PIR.class);
        PIR pir2 = mock(PIR.class);
        when(pir1.getIdentifier()).thenReturn("testId");
        when(pir2.getIdentifier()).thenReturn("otherId");

        // 准备 PIRs 集合
        HashMap<String, PIR> PIRs = new HashMap<>();
        PIRs.put("pir1", pir1);
        PIRs.put("pir2", pir2);

        // 创建 Interpreter 实例并设置 PIRs
        Interpreter interpreter = new Interpreter();
        interpreter.setInterpreterPIRs(PIRs);

        // 调用 visitIdCriteria 方法
        List<PIR> result = interpreter.visitIdCriteria(idCriteria);

        // 验证结果
        assertTrue(result.contains(pir1));
        assertFalse(result.contains(pir2));
    }
}
