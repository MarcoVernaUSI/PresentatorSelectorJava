package com.app.seminar.dbMapper;

import java.sql.Connection;
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
    public PreparedStatement write(Connection connection, Student student) {
        PreparedStatement ps;
        try {
            ps = connection.prepareStatement("insert into student (firstName, lastName ) values (?,?)");
            ps.setObject(1, student.getName());
            ps.setObject(2, student.getSurname());
            } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return ps;
    }
    


}
