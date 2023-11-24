package hk.edu.polyu.comp.comp3211.group6.Model;

public class IdCriteria implements Criteria{
    private final String identifier;
    public IdCriteria(String identifier){
        this.identifier = identifier;
    }

    public String getIdentifier() {
        return identifier;
    }

    @Override
    public <T> T accept(CriteriaVisitor<T> visitor) {
        return visitor.visitIdCriteria(this);
    }

}
