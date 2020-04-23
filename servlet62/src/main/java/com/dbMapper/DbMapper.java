package com.dbMapper;

import java.sql.ResultSet;
import java.util.List;

public interface DbMapper<T> {
    Iterable<T> findAll();
    T findById(String id);
    int insert(T entry);
    void delete(String id);
    List<T> read(ResultSet rs);
    void update(T entry); 
}
