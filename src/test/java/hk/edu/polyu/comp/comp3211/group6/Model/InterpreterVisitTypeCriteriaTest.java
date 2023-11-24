package hk.edu.polyu.comp.comp3211.group6.Model;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import hk.edu.polyu.comp.comp3211.group6.Controller.Token;
import hk.edu.polyu.comp.comp3211.group6.Controller.TokenType;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class InterpreterVisitTypeCriteriaTest {
    @Test
    void testVisitTypeCriteriaWithNote() {
        Token noteTypeToken = new Token(TokenType.NOTE, "NOTE",null);
        TypeCriteria typeCriteria = new TypeCriteria(noteTypeToken);

        PIR notePIR = mock(Note.class);
        PIR otherPIR = mock(Task.class);

        HashMap<String, PIR> PIRs = new HashMap<>();
        PIRs.put("note", notePIR);
        PIRs.put("other", otherPIR);

        Interpreter interpreter = new Interpreter();
        interpreter.setInterpreterPIRs(PIRs);

        List<PIR> result = interpreter.visitTypeCriteria(typeCriteria);

        assertTrue(result.contains(notePIR));
        assertFalse(result.contains(otherPIR));
    }

    @Test
    void testVisitTypeCriteriaWithTask() {
        Token taskTypeToken = new Token(TokenType.TASK, "TASK",null);
        TypeCriteria typeCriteria = new TypeCriteria(taskTypeToken);

        PIR taskPIR = mock(Task.class);
        PIR otherPIR = mock(Schedule.class);

        HashMap<String, PIR> PIRs = new HashMap<>();
        PIRs.put("Task", taskPIR);
        PIRs.put("other", otherPIR);

        Interpreter interpreter = new Interpreter();
        interpreter.setInterpreterPIRs(PIRs);

        // 调用 visitTypeCriteria 方法
        List<PIR> result = interpreter.visitTypeCriteria(typeCriteria);

        // 验证结果
        assertTrue(result.contains(taskPIR));
        assertFalse(result.contains(otherPIR));
    }

    @Test
    void testVisitTypeCriteriaWithSchedule() {
        Token scheduleTypeToken = new Token(TokenType.SCHEDULE, "SCHEDULE",null);
        TypeCriteria typeCriteria = new TypeCriteria(scheduleTypeToken);

        PIR schedulePIR = mock(Schedule.class);
        PIR otherPIR = mock(Note.class);

        HashMap<String, PIR> PIRs = new HashMap<>();
        PIRs.put("schedule", schedulePIR);
        PIRs.put("other", otherPIR);

        Interpreter interpreter = new Interpreter();
        interpreter.setInterpreterPIRs(PIRs);

        List<PIR> result = interpreter.visitTypeCriteria(typeCriteria);

        assertTrue(result.contains(schedulePIR));
        assertFalse(result.contains(otherPIR));
    }

    @Test
    void testVisitTypeCriteriaWithContact() {
        Token contactTypeToken = new Token(TokenType.CONTACT, "CONTACT",null);
        TypeCriteria typeCriteria = new TypeCriteria(contactTypeToken);

        PIR contactPIR = mock(Contact.class);
        PIR otherPIR = mock(Task.class);

        HashMap<String, PIR> PIRs = new HashMap<>();
        PIRs.put("contact", contactPIR);
        PIRs.put("other", otherPIR);

        Interpreter interpreter = new Interpreter();
        interpreter.setInterpreterPIRs(PIRs);

        List<PIR> result = interpreter.visitTypeCriteria(typeCriteria);

        assertTrue(result.contains(contactPIR));
        assertFalse(result.contains(otherPIR));
    }


}
