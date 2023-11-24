package hk.edu.polyu.comp.comp3211.group6.Controller;

public class ExitStmt implements Stmt{
    @Override
    public <T> T accept(StmtVisitor<T> stmtVisitor) {
        return stmtVisitor.visitExitStmt(this);
    }
}
