package hk.edu.polyu.comp.comp3211.group6.Controller;

import hk.edu.polyu.comp.comp3211.group6.Controller.Token;
import hk.edu.polyu.comp.comp3211.group6.Controller.TokenType;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;

class TokenTest {
    @Test
    void testToString() {
        var create = new Token(TokenType.CREATE, "create", null);
        assertEquals("CREATE create", create.toString());
    }

    @Test
    void testToStringStr(){
        var str = new Token(TokenType.STRING, "\"abc\"", "abc");
        assertEquals("STRING \"abc\" abc", str.toString());
    }

    @Test
    void testEquals() {
        var t1 = new Token(TokenType.IDENTIFIER, "t1", null);
        var t2 = new Token(TokenType.IDENTIFIER, "t1", null);
        assertTrue(t1.equals(t2));
    }

    @Test
    void testEqualsType() {
        var t1 = new Token(TokenType.IDENTIFIER, "t1", null);
        var t2 = new Token(TokenType.NOTE, "Note", null);
        assertNotEquals(t1, t2);
    }

    @Test
    void testEqualsClass() {
        var t1 = new Token(TokenType.IDENTIFIER, "t1", null);
        var t2 = "2";
        assertNotEquals(t1, t2);
    }

    @Test
    void testEqualsOneNull() {
        var t1 = new Token(TokenType.IDENTIFIER, "t1", null);
        var t2 = new Token(TokenType.STRING, "\"abc\"", "abc");
        assertNotEquals(t1, t2);
    }

    @Test
    void testEqualsNotNull() {
        var t1 = new Token(TokenType.STRING, "\"abc\"", "abc");
        var t2 = new Token(TokenType.STRING, "\"abc\"", "abc");
        assertEquals(t1, t2);
    }

}