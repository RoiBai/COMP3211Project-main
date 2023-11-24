package hk.edu.polyu.comp.comp3211.group6.Model;


public class NegCriteria implements Criteria{
    private final Criteria right;

    public NegCriteria(Criteria right){
        this.right = right;
    }

    public Criteria getRight() {
        return right;
    }

    @Override
    public <T> T accept(CriteriaVisitor<T> visitor) {
        return visitor.visitNegCriteria(this);
    }

}
