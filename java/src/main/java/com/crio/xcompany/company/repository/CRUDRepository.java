package com.crio.xcompany.company.repository;

public interface CRUDRepository<T, ID> {
    public T save(T entity);
    public T delete(String entity);
    public T update(T entity);
}
