package com.app.seminar.dbMapper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.app.seminar.model.Course;
import com.app.seminar.model.Seminar;
import com.app.seminar.model.Student;

public class SeminarReader implements DbReader<Seminar>{
    
    private final Connection _connection;
    
    public SeminarReader(Connection connection) {
        _connection = connection;
    }
    

    @Override
    public Seminar read(ResultSet rs) {
        try {
            Seminar seminar = new Seminar(
                rs.getInt(1),
                rs.getString(4),
                rs.getInt(5),
                new Course(rs.getString(2), rs.getString(3)),
                rs.getString(6)
                );
            return seminar;
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int write(Seminar seminar) {
        PreparedStatement ps;
        try {
            ps = _connection.prepareStatement("insert into Seminar (name, description, location, totalSeats, start) values (?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS); 
            ps.setObject(1, seminar.getName());
            ps.setObject(2, seminar.getDescription());
            ps.setObject(3, seminar.getLocation());
            ps.setObject(4, seminar.getTotalSeats());
            ps.setObject(5, seminar.getStartDate());
            ps.executeUpdate();
            
            ResultSet rs = ps.getGeneratedKeys();

            int seminarId = rs.getInt(1);

            ps.close();
            
            for (Student student : seminar.getStudentsList()) {
                int studentId = new StudentReader(_connection).checkIfExist(student);
                
                if (studentId == -1){
                    studentId =new StudentReader(_connection).write(student);        
                }
                
                PreparedStatement  ps2 = _connection.prepareStatement("insert into Enrollement (studentId, seminarId ) values (?,?)");
                ps2.setObject(1, studentId);
                ps2.setObject(2, seminarId);
                ps2.executeUpdate();
                ps2.close();
                
            }
            return seminarId;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    
    
    public Seminar readStudents(Seminar seminar) {
        try {
            PreparedStatement ps = _connection.prepareStatement("select * from Enrollement where seminarId = ?");
            ps.setObject(1, seminar.getId());
            ResultSet rs = ps.executeQuery();
            
            
            List<Integer> ids = new ArrayList<Integer>();
            while(rs.next()) {
                ids.add(rs.getInt(1));
            }
            
            ps.close();
            rs.close();
            
            for (int id : ids) {
                PreparedStatement Studentps =  _connection.prepareStatement("select * from Student where id = ?");
                Studentps.setObject(1, id);
                ResultSet rs2 = Studentps.executeQuery();
                while(rs2.next()){
                    seminar.addEnrollment(new StudentReader(_connection).read(rs2));
                }
            }
            
            
            
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return seminar;
    }


    @Override
    public void addAll(List<Seminar> entries) {
        for (Seminar entry : entries) {
            readStudents(entry);
        }
    }
    


}
