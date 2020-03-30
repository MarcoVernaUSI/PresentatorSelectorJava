package com.app.seminar.dbMapper;

import java.sql.ResultSet;

public interface DbReader<T> {
    T read(ResultSet rs);
}
