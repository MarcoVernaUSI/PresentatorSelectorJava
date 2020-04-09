package com.dbMapper;

public interface DbMapper<T> {
    Iterable<T> findAll();
    T findById(String id);
    int insert(T entry);
    void delete(String id);
}
