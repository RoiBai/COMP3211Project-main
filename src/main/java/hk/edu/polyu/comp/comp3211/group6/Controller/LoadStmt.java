package hk.edu.polyu.comp.comp3211.group6.Controller;

public class LoadStmt implements Stmt{
    public LoadStmt(){}

    @Override
    public <T> T accept(StmtVisitor<T> stmtVisitor) {
        return stmtVisitor.visitLoadStmt(this);
    }
}
