package hk.edu.polyu.comp.comp3211.group6.Model;

public interface Criteria {
    <T> T accept(CriteriaVisitor<T> visitor);
}
