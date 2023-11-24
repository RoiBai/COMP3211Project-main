package hk.edu.polyu.comp.comp3211.group6.Controller;

public interface StmtVisitor<T>{
    T visitCreateStmt(CreateStmt stmt);
    T visitPrintStmt(PrintStmt stmt);
    T visitExitStmt(ExitStmt stmt);
    T visitSaveStmt(SaveStmt stmt);
    T visitLoadStmt(LoadStmt stmt);
    T visitModifyStmt(ModifyStmt stmt);
    T visitSearchStmt(SearchStmt stmt);
    T visitDeleteStmt(DeleteStmt stmt);
}
