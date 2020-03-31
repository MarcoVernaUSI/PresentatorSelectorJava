package com.app.seminar.controller;

import java.util.regex.Pattern;

import com.Context;
import com.app.controller.Controller;
import com.app.seminar.dbMapper.DbMapper;
import com.app.seminar.dbMapper.SeminarReader;
import com.app.seminar.dbMapper.StudentReader;
import com.app.seminar.model.Course;
import com.app.seminar.model.Seminar;
import com.app.seminar.model.Student;

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
    
    
    //new DbMapper<Seminar>(context.connection(), new SeminarReader(), "Seminar").create();
    
    saveStudent(context, s1);
    saveStudent(context, s2);
    saveStudent(context, s3);
    saveStudent(context, s4);
    saveStudent(context, s5);
    

    saveSeminar(context, sem1);
    saveSeminar(context, sem2);
    saveSeminar(context, sem3);
    
    }
    
    
    public void saveStudent(Context context, Student student) {
        new DbMapper<Student>(context.connection(), new StudentReader(), "Student").save(student);
    }

    public void saveSeminar(Context context, Seminar seminar) {
        new DbMapper<Seminar>(context.connection(), new SeminarReader(), "Seminar").save(seminar);
          }
}
