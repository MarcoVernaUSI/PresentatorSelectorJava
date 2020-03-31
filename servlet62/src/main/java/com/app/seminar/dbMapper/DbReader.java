package com.app.seminar.dbMapper;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

public interface DbReader<T> {
    T read(ResultSet rs);
    PreparedStatement write(PreparedStatement ps, T entry);
}
