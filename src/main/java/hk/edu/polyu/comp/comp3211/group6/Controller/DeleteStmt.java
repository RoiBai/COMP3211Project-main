package hk.edu.polyu.comp.comp3211.group6.Controller;

public class DeleteStmt implements Stmt{
    private final Token identifier;
    public DeleteStmt(Token identifier){
        this.identifier = identifier;
    }

    public String getIdentifier(){
        return identifier.lexeme;
    }
    @Override
    public <T> T accept(StmtVisitor<T> stmtVisitor) {
        return stmtVisitor.visitDeleteStmt(this);
    }
}
