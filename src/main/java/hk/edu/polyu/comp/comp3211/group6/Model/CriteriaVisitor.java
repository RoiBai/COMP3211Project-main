package hk.edu.polyu.comp.comp3211.group6.Model;



public interface CriteriaVisitor<T> {
    T visitTypeCriteria(TypeCriteria typeCriteria);
    T visitIdCriteria(IdCriteria idCriteria);
    T visitNegCriteria(NegCriteria negCriteria);
    T visitStringCriteria(StringCriteria stringCriteria);
    T visitTimeCriteria(TimeCriteria timeCriteria);
    T visitAndCriteria(AndCriteria andCriteria);
    T visitOrCriteria(OrCriteria orCriteria);

}
