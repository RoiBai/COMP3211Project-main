package hk.edu.polyu.comp.comp3211.group6.Controller;

public class SaveStmt implements Stmt{
    public SaveStmt(){}

    @Override
    public <T> T accept(StmtVisitor<T> stmtVisitor) {
        return stmtVisitor.visitSaveStmt(this);
    }
}
