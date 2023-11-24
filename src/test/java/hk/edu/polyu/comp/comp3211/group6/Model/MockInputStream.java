package hk.edu.polyu.comp.comp3211.group6.Model;

import java.io.IOException;
import java.io.InputStream;

public class MockInputStream extends InputStream{
    private String input;
    private int position = 0;

    public MockInputStream(String input) {
        this.input = input.replace("\n", System.lineSeparator());
    }

    @Override
    public int read() throws IOException {
        if (position < input.length()) {
            int ret = input.charAt(position++);
            System.out.println("MockInputStream read: " + (char) ret); // 调试信息
            return ret;
        } else {
            System.out.println("MockInputStream reached end of input");
            return -1; // 表示没有更多的数据
        }
    }


}
