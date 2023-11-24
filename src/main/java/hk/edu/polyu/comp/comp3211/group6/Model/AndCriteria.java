package hk.edu.polyu.comp.comp3211.group6.Model;

public class AndCriteria implements Criteria{
    private final Criteria left;
    private final Criteria right;
    public AndCriteria(Criteria left, Criteria right){
        this.left = left;
        this.right = right;
    }

    public Criteria getRight() {
        return right;
    }

    public Criteria getLeft() {
        return left;
    }

    @Override
    public <T> T accept(CriteriaVisitor<T> visitor) {
        return visitor.visitAndCriteria(this);
    }
}
