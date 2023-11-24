package hk.edu.polyu.comp.comp3211.group6.Controller;

public class CreateStmt implements Stmt{
    private final Token identifier;
    private final Token dataType;
    public CreateStmt(Token dataType, Token identifier){
        this.identifier = identifier;
        this.dataType = dataType;
    }

    @Override
    public <T> T accept(StmtVisitor<T> stmtVisitor) {
        return stmtVisitor.visitCreateStmt(this);
    }

    public String getIdentifier() {
        return identifier.lexeme;
    }

    public Token getDataType() {
        return dataType;
    }
}
