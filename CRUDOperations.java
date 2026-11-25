package com.healthcare.dao;
public interface CRUDOperations<T> {
    void add(T obj) throws Exception;
    void update(T obj) throws Exception;
    void delete(int id) throws Exception;
}
