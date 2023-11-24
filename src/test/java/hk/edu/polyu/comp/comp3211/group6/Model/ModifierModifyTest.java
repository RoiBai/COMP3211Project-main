package hk.edu.polyu.comp.comp3211.group6.Model;

import static org.mockito.Mockito.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import java.util.HashMap;

class ModifierModifyTest {

    private Modifier modifier;
    private PIR pir;
    private HashMap<String, PIR> PIRs;

    @BeforeEach
    void setUp() {
        modifier = spy(new Modifier(PIRs));
        pir = mock(PIR.class); // 模拟 PIR 对象
    }

    @Test
    void testModifyCallsAccept() {
        modifier.modify(pir); // 调用 modify 方法

        // 验证 pir 对象的 accept 方法是否被调用，且参数为 modifier
        verify(pir, times(1)).accept(modifier);
    }
}

