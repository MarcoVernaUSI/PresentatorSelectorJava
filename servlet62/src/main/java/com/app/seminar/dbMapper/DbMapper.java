package com.app.seminar.dbMapper;

public interface DbMapper<T> {
    Iterable<T> findAll();
    T findById(String id);
    int insert(T entry);
}
