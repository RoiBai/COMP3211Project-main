package hk.edu.polyu.comp.comp3211.group6.Model;

import hk.edu.polyu.comp.comp3211.group6.Controller.Token;

public class TypeCriteria implements Criteria{
    private final Token type;

    public TypeCriteria(Token type){
        this.type = type;
    }

    public Token getType() {
        return type;
    }

    @Override
    public <T> T accept(CriteriaVisitor<T> visitor) {
        return visitor.visitTypeCriteria(this);
    }

}
