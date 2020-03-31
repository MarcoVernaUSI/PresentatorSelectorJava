package com.app.seminar.dbMapper;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.app.seminar.model.Student;

public class StudentReader implements DbReader<Student>{

    @Override
    public Student read(ResultSet rs) {
        try {
            return new Student(
                rs.getInt(1),
                rs.getString(2),
                rs.getString(3)
                );
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public PreparedStatement write(PreparedStatement ps, Student student) {
        try {
            ps.setObject(1, student.getId());
            ps.setObject(2, student.getName());
            ps.setObject(3, student.getSurname());
            } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return ps;
    }
    


}
