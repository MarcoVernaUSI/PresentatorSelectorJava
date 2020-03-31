package com.app.seminar.dbMapper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.app.seminar.model.Course;
import com.app.seminar.model.Seminar;

public class SeminarReader implements DbReader<Seminar>{

    @Override
    public Seminar read(ResultSet rs) {
        try {
            return new Seminar(
                rs.getInt(1),
                rs.getString(4),
                rs.getInt(5),
                new Course(rs.getString(2), rs.getString(3)),
                rs.getString(6)
                );
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public PreparedStatement write(Connection connection, Seminar seminar) {
        PreparedStatement ps;
        try {
            ps = connection.prepareStatement("insert into Seminar (name, description, location, totalSeats, start) values (?,?,?,?,?)"); 
            ps.setObject(1, seminar.getName());
            ps.setObject(2, seminar.getDescription());
            ps.setObject(3, seminar.getLocation());
            ps.setObject(4, seminar.getTotalSeats());
            ps.setObject(5, seminar.getStartDate());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return ps;
    }
    


}
