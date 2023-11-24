package hk.edu.polyu.comp.comp3211.group6.Controller;

import hk.edu.polyu.comp.comp3211.group6.Controller.PIMError;
import hk.edu.polyu.comp.comp3211.group6.Controller.Scanner;
import hk.edu.polyu.comp.comp3211.group6.Controller.Token;
import hk.edu.polyu.comp.comp3211.group6.Controller.TokenType;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ScannerTest {
    @Test
    void create(){
        var scanner = new Scanner("create");
        Token create = new Token(TokenType.CREATE, "create", null);
        List<Token> tokens = scanner.scanTokens();
        assertEquals(create, tokens.get(0));
    }

    @Test
    void unexpectedChar(){
        var scanner = new Scanner("[]");
        assertThrows(PIMError.class, scanner::scanTokens);
    }

    @Test
    void eof(){
        var scanner = new Scanner("");
        Token eof = new Token(TokenType.EOF, "", null);
        List<Token> tokens = scanner.scanTokens();
        assertEquals(eof, tokens.get(0));
    }

    @Test
    void string(){
        var scanner = new Scanner("\"abc\"");
        Token string = new Token(TokenType.STRING, "\"abc\"", "abc");
        List<Token> tokens = scanner.scanTokens();
        assertEquals(string, tokens.get(0));
    }

    @Test
    void stringUnterminated(){
        var scanner = new Scanner("\"abc");
        assertThrows(PIMError.class, scanner::scanTokens);
    }

    @Test
    void symbols(){
        var scanner = new Scanner("*    < > = ! && ||");
        Token all = new Token(TokenType.ALL, "*", null);
        Token before = new Token(TokenType.BEFORE, "<", null);
        Token after = new Token(TokenType.AFTER, ">", null);
        Token equal = new Token(TokenType.EQUAL, "=", null);
        Token not = new Token(TokenType.NEG, "!", null);
        Token and = new Token(TokenType.AND, "&&", null);
        Token or = new Token(TokenType.OR, "||", null);
        Token eof = new Token(TokenType.EOF, "", null);
        List<Token> expectedTokens = new ArrayList<>();
        expectedTokens.add(all);
        expectedTokens.add(before);
        expectedTokens.add(after);
        expectedTokens.add(equal);
        expectedTokens.add(not);
        expectedTokens.add(and);
        expectedTokens.add(or);
        expectedTokens.add(eof);
        List<Token> tokens = scanner.scanTokens();
        assertEquals(expectedTokens, tokens);
    }

    @Test
    void date(){
        var scanner = new Scanner("2023-11-11,19:00");
        Token date = new Token(TokenType.DATE, "2023-11-11,19:00", null);
        List<Token> tokens = scanner.scanTokens();
        assertEquals(date, tokens.get(0));
    }

}