package com.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.regex.Pattern;

import com.Context;
import com.dbMapper.SeminarMapper;
import com.dbMapper.StudentMapper;
import com.model.Course;
import com.model.Seminar;
import com.model.Student;

public class CreateDatabaseController implements Controller{

    @Override
    public boolean handles(String route) {
        return Pattern.matches("^(/create|/)$", route);
        
    }

    @Override
    public void execute(Context context) {
        
    Student s1 = new Student(1, "Marco", "Rossi");
    Student s2 = new Student(2, "Aldo", "Baglio");
    Student s3 = new Student(3, "Giovanni", "Storti");
    Student s4 = new Student(4, "Giacomo", "Poretti");
    Student s5 = new Student(5, "Francesca", "Bianchi");
        
    
    Course c1 = new Course("Programmazione Java", "Corso di basi di java");
    Course c2 = new Course("Software Engineering", "Corso principi di SE");
            
    Seminar sem1 = new Seminar(1, "Lugano", 20, c1, "10/02/2020");
    Seminar sem2 = new Seminar(2, "Mendrisio", 20, c2, "05/02/2020");
    Seminar sem3 = new Seminar(3, "Manno", 10, c2, "05/02/2020");
    
    sem1.addEnrollment(s1);
    sem1.addEnrollment(s2);
    sem1.addEnrollment(s3);
    sem1.addEnrollment(s4);
    sem1.addEnrollment(s5);
    

    sem2.addEnrollment(s2);
    sem2.addEnrollment(s3);
    sem2.addEnrollment(s4);
    

    sem3.addEnrollment(s1);
    sem3.addEnrollment(s5);
    
    
    //create(context.connection());
    
    //saveStudent(context, s1);
    //saveStudent(context, s2);
    //saveStudent(context, s3);
    //saveStudent(context, s4);
    //saveStudent(context, s5);
    

    saveSeminar(context, sem1);
    saveSeminar(context, sem2);
    saveSeminar(context, sem3);
    
    }
    
    
    public void create(Connection connection) {
        try {
            PreparedStatement ps = connection.prepareStatement("CREATE TABLE Seminar(" + 
                "id INTEGER PRIMARY KEY," + 
                "name VARCHAR(255) NOT NULL," + 
                "description VARCHAR(255)," + 
                "location VARCHAR(255) NOT NULL," + 
                "totalSeats NUMERIC NOT NULL," + 
                "start DATETIME NOT NULL)");
            ps.executeUpdate();
            ps.close();
            
            ps = connection.prepareStatement("CREATE TABLE Student(" + 
                "id INTEGER PRIMARY KEY," + 
                "firstName VARCHAR(255) NOT NULL," + 
                "lastName   VARCHAR(255))");
            ps.executeUpdate();
            ps.close();
            
            ps = connection.prepareStatement("CREATE TABLE Enrollement(" + 
                "id INTEGER PRIMARY KEY," + 
                "studentId INTEGER NOT NULL," + 
                "seminarId INTEGER NOT NULL)");
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        
    }
    
    
    public void saveStudent(Context context, Student student) {
        new StudentMapper(context.connection()).insert(student);
    }

    public void saveSeminar(Context context, Seminar seminar) {
        new SeminarMapper(context.connection()).insert(seminar);
     }
}
