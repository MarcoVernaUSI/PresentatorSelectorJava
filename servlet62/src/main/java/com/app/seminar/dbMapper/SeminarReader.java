package com.app.seminar.dbMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.app.seminar.model.Course;
import com.app.seminar.model.Seminar;

public class SeminarReader implements DbReader<Seminar>{

    @Override
    public Seminar read(ResultSet rs) {
        try {
            return new Seminar(
                rs.getString(4),
                rs.getInt(5),
                new Course(rs.getString(2), rs.getInt(1), rs.getString(3)),
                rs.getString(6)
                );
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
