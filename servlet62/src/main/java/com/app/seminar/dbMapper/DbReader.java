package com.app.seminar.dbMapper;

import java.sql.ResultSet;
import java.util.List;

public interface DbReader<T> {
    T read(ResultSet rs);
    int write(T entry);
    void addAll(List<T> entries);
}
