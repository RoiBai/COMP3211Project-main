package hk.edu.polyu.comp.comp3211.group6.Model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class NoteTest {
    //This function tested Model/Note: getIdentifier() and note.getText()
    @Test
    void testGets() {
        String testIdentifier = "TestId";
        String testText = "Test note";
        Note note = new Note(testIdentifier, testText);

        assertEquals(testIdentifier, note.getIdentifier());
        assertEquals(testText, note.getText());
    }

    //This function tested Model/Note: getText()
    @Test
    void testSet() {
        Note note = new Note("TestId", "text A");
        String newText = "Updated text B";
        note.setText(newText);
        assertEquals(newText, note.getText());
    }

    //This function tested Model/Note: getString()
    @Test
    void testGetString() {
        Note note = new Note("TestId", "Test note");
        assertEquals("Test note", note.getString());
    }

    //This function tested Model/Note: toString()
    @Test
    void testToString() {
        Note note = new Note("TestId", "Test note");
        assertEquals("TestId\nTest note", note.toString());
    }

    //This function tested Model/Note: accept()
    @Test
    void testAcceptPrint() {
        Note note = new Note("Note1", "Test note");
        PrintVisitor<Object> mockPrintVisitor = mock(PrintVisitor.class);
        note.accept(mockPrintVisitor);
        verify(mockPrintVisitor).visitNote(note);
    }

    //This function tested Model/Note: accept()
    @Test
    void testAcceptModify() {
        Note note = new Note("Note1", "Test Note");
        ModifyVisitor<Object> mockModifyVisitor = mock(ModifyVisitor.class);
        note.accept(mockModifyVisitor);
        verify(mockModifyVisitor).visitNote(note);
    }
}
