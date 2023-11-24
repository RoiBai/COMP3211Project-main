package hk.edu.polyu.comp.comp3211.group6.Controller;

import hk.edu.polyu.comp.comp3211.group6.Model.Criteria;

public class SearchStmt implements Stmt{
    private final Criteria criteria;
    public SearchStmt(Criteria criteria){
        this.criteria = criteria;
    }
    @Override
    public <T> T accept(StmtVisitor<T> stmtVisitor) {
        return stmtVisitor.visitSearchStmt(this);
    }

    public Criteria getCriteria() {
        return criteria;
    }
}
