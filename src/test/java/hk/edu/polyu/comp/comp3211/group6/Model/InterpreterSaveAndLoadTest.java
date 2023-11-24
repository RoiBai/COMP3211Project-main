package hk.edu.polyu.comp.comp3211.group6.Model;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;
import java.io.*;
import java.lang.reflect.Method;
import java.util.HashMap;

public class InterpreterSaveAndLoadTest {
    private Interpreter interpreter;
    private final InputStream originalSystemIn = System.in;

    @BeforeEach
    void setUp() {
        interpreter = new Interpreter();
    }
    @Test
    void testLoad() throws Exception {
        // 创建临时文件
        File tempFile = File.createTempFile("test", ".ser");
        String testFilePath = tempFile.getAbsolutePath();

        // 创建模拟的 PIR 对象并序列化到文件
        HashMap<String, PIR> expectedPIRs = new HashMap<>();
        PIR mockPir = mock(PIR.class);
        expectedPIRs.put("testId", mockPir);
        //String testFilePath = "/Users/roi/Desktop";
        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(testFilePath));
        out.writeObject(expectedPIRs);
        out.close();

        // 模拟用户输入文件路径
        String simulatedInput = testFilePath + "\n";
        ByteArrayInputStream in = new ByteArrayInputStream(simulatedInput.getBytes());
        System.setIn(in);

        // 使用反射调用私有的 load 方法
        Method loadMethod = Interpreter.class.getDeclaredMethod("Interpreterload");
        loadMethod.setAccessible(true);
        loadMethod.invoke(interpreter);

        // 验证结果
        // 您需要有一个方法来获取 interpreter 中的 PIRs，可能需要更改 PIRs 的可见性或添加 getter 方法
        HashMap<String, PIR> loadedPIRs = interpreter.getInterpreterPIRs();
        assertTrue(loadedPIRs.containsKey("testId"));
        // 其他必要的验证...

        // 清理测试文件
        new File(testFilePath).delete();
    }

    @Test
    void testSave() throws Exception {
        // 模拟用户输入文件路径
        String testFilePath = "/Users/liuchengju/Desktop";
        ByteArrayInputStream in = new ByteArrayInputStream(testFilePath.getBytes());
        System.setIn(in);

        // 填充 PIRs 并调用 save
        // 假设您有一个方法来设置 PIRs
        HashMap<String, PIR> PIRs = new HashMap<>();
        PIRs.put("testId", mock(PIR.class));
        interpreter.setInterpreterPIRs(PIRs);

        // 使用反射调用私有的 save 方法
        Method saveMethod = Interpreter.class.getDeclaredMethod("Interpretersave");
        saveMethod.setAccessible(true);
        saveMethod.invoke(interpreter);

        // 验证文件是否被创建
        File savedFile = new File(testFilePath + ".pim");
        assertTrue(savedFile.exists());


        // 清理测试文件
        savedFile.delete();
    }


    @AfterEach
    void restoreSystemIn() {
        System.setIn(originalSystemIn);
    }
}
