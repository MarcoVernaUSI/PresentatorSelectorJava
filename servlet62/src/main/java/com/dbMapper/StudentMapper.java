package com.dbMapper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.model.Seminar;
import com.model.Student;

public class StudentMapper implements DbMapper<Student>{
    
    private final static String TableName = "Student";
    private final Connection _connection;
    
    public StudentMapper(Connection connection) {
        _connection = connection;
    }

    
    @Override
    public Iterable<Student> findAll() {
        try {
            PreparedStatement ps = _connection.prepareStatement("select * from "+TableName);
            ResultSet rs = ps.executeQuery();
            return read(rs);
            
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    
    
    @Override
    public Student findById(String id) {
        try {
            PreparedStatement ps = _connection.prepareStatement("select * from "+TableName+" where id = ?");
            ps.setObject(1, id);
            
            ResultSet rs = ps.executeQuery();
            return read(rs).get(0);
            
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    
    
    public List<Student> read(ResultSet rs) {
        List<Student> entries = new ArrayList<Student>();
        try {
            while(rs.next()){
                entries.add( new Student(
                    rs.getInt(1),
                    rs.getString(2),
                    rs.getString(3)
                    ));
            }
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return entries;
    }

    
    @Override
    public int insert(Student student) {
        try {
            PreparedStatement ps;
            ps = _connection.prepareStatement("insert into "+TableName+" (firstName, lastName ) values (?,?)", Statement.RETURN_GENERATED_KEYS);
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
    
    
    // return -1 if th estudent is not found
    public int getStudentId(Student student) {
        PreparedStatement ps;
        try {
            int result = -1;
            ps = _connection.prepareStatement("select * from "+TableName+" where firstName = ? and lastName = ?");
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
    
    
    public Seminar addStudentsTo(Seminar seminar) {
        try {
            PreparedStatement ps = _connection.prepareStatement("select * from Enrollement where seminarId = ?");
            ps.setObject(1, seminar.getId());
            ResultSet rs = ps.executeQuery();
            
            List<Integer> ids = new ArrayList<Integer>();
            while(rs.next()) {
                ids.add(rs.getInt(2));
            }
            ps.close();
            rs.close();
            
            for (int id : ids) {
                PreparedStatement Studentps =  _connection.prepareStatement("select * from "+TableName+" where id = ?");
                Studentps.setObject(1, id);
                ResultSet rs2 = Studentps.executeQuery();
                
                for (Student student : read(rs2)){
                    seminar.addEnrollment(student);
                }
            }
            
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return seminar;
    }
}
