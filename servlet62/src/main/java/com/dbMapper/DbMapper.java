package com.dbMapper;

import java.sql.ResultSet;
import java.util.List;
import java.util.Map;

public interface DbMapper<T> {
    Iterable<T> findAll();
    T findById(String id);
    int insert(Map<String, String> entry);
    void delete(String id);
    List<T> read(ResultSet rs);
    void update(Map<String, String> entry, String id); 
}
