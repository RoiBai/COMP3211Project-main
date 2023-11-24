package hk.edu.polyu.comp.comp3211.group6.Model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ContactTest {
    //This function tested Model/Contact: getIdentifier(), getName(), getAddress() and getMobile()
    @Test
    void testGets() {
        Contact contact = new Contact("RB", "Roi Bai", "SZ", "123456");
        assertEquals("RB", contact.getIdentifier());
        assertEquals("Roi Bai", contact.getName());
        assertEquals("SZ", contact.getAddress());
        assertEquals("123456", contact.getMobile());
    }

    //This function tested Model/Contact: setName(),setAddress(), and setMobile()
    @Test
    void testSets() {
        Contact contact = new Contact("RB", "Roi Bai", "SZ", "123456");

        contact.setName("Ruiyuan Bai");
        assertEquals("Ruiyuan Bai", contact.getName());

        contact.setAddress("Beijing");
        assertEquals("Beijing", contact.getAddress());

        contact.setMobile("654321");
        assertEquals("654321", contact.getMobile());
    }

    //This function tested Model/Contact: getString()
    @Test
    void testGetString() {
        Contact contact = new Contact("RB", "Roi Bai", "SZ", "123456");
        String expectedString = "Roi Bai SZ 123456";
        assertEquals(expectedString, contact.getString());
    }

    //This function tested Model/Contact: toString()
    @Test
    void testToString() {
        Contact contact = new Contact("RB", "Roi Bai", "SZ", "123456");
        String expectedString = "RB\nRoi Bai\nSZ\n123456";
        assertEquals(expectedString, contact.toString());
    }

    //This function tested Model/Contact: accept()
    @Test
    void testAcceptPrintVisitorMethod() {
        Contact contact = new Contact("RB", "Roi Bai", "SZ", "123456");
        PrintVisitor<Object> mockPrintVisitor = mock(PrintVisitor.class);
        contact.accept(mockPrintVisitor);
        verify(mockPrintVisitor).visitContact(contact);
    }

    //This function tested Model/Contact: accept()
    @Test
    void testAcceptModifyVisitorMethod() {
        Contact contact = new Contact("RB", "Roi Bai", "SZ", "123456");
        ModifyVisitor<Object> mockModifyVisitor = mock(ModifyVisitor.class);
        contact.accept(mockModifyVisitor);
        verify(mockModifyVisitor).visitContact(contact);
    }
}
