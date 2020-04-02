package com.app.seminar.dbMapper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import com.app.seminar.model.Student;

public class StudentReader implements DbReader<Student>{
    
    private final Connection _connection;
    
    public StudentReader(Connection connection) {
        _connection = connection;
    }

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
    public int write(Student student) {
        PreparedStatement ps;
        try {
            ps = _connection.prepareStatement("insert into student (firstName, lastName ) values (?,?)", Statement.RETURN_GENERATED_KEYS);
            ps.setObject(1, student.getName());
            ps.setObject(2, student.getSurname());
            ps.executeUpdate();
            
            ResultSet rs = ps.getGeneratedKeys();
            int studentId = rs.getInt(1);
            
            ps.close();
            return studentId;
            
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    
    
    public int checkIfExist(Student student) {
        PreparedStatement ps;
        try {
            int result = -1;
            ps = _connection.prepareStatement("select * from Student where firstName = ? and lastName = ?");
            ps.setObject(1, student.getName());
            ps.setObject(2, student.getSurname());
            ResultSet rs = ps.executeQuery();
            
            if (rs.next()) {
                result =  rs.getInt(1);
            }
            
            ps.close();
            return result;
            
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        
    }

    @Override
    public void addAll(List<Student> entries) {
    }
}
