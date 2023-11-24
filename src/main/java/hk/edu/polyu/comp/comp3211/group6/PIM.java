package hk.edu.polyu.comp.comp3211.group6;

import hk.edu.polyu.comp.comp3211.group6.Controller.Parser;
import hk.edu.polyu.comp.comp3211.group6.Controller.Scanner;
import hk.edu.polyu.comp.comp3211.group6.Controller.PIMError;
import hk.edu.polyu.comp.comp3211.group6.Controller.Token;
import hk.edu.polyu.comp.comp3211.group6.Controller.Stmt;
import hk.edu.polyu.comp.comp3211.group6.Model.Interpreter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;


public class PIM {
    private static boolean haveError = false;
    private static final Interpreter interpreter = new Interpreter();
    public static void main(String[] args){
        try {
            runPrompt();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void runPrompt()throws IOException{
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(System.in));
        for (;;) {
            System.out.print("PIM> ");
            String line = reader.readLine();
            if (line == null) break;
            run(line);
            haveError = false;
        }
    }

    private static void run(String line){
        try {
            Scanner scanner = new Scanner(line);
            List<Token> tokens = scanner.scanTokens();
            Parser parser = new Parser(tokens);
            Stmt stmt = parser.parse();
            interpreter.interpret(stmt);

        }catch (PIMError error){
            haveError = false;
        }

    }
}
