package com.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.Context;
import com.controller.ListController;
import com.dbMapper.DbMapper;
import com.dbMapper.StudentMapper;
import com.view.StudentListView;
import com.view.View;

public class StudentEntity implements Entity<Student>{

    @Override
    public Map<String, String> getFieldsTypes() {
        return Student.getFieldsTypes();
    }

    @Override
    public DbMapper<Student> getMapper(Context context) {
        return new StudentMapper(context.connection());
    }

    @Override
    public View getListView(Context context) {
        return new ListController<Student>(new StudentEntity()).buildPage(context);     
    }

    @Override
    public Student build(Map<String, String> contextMap) {
        return new Student(contextMap);
    }

    @Override
    public Map<String, List<String>>  validate(Map<String, String> contextMap) {
        return new StudentValidation().validate(contextMap);
          }

    @Override
    public boolean isValid(Map<String, String> contextMap) {
        return new StudentValidation().isValid(contextMap);
        
    }
    
    @Override
    public Iterable<String> getFieldList(){
        
        Iterable<String> fields =Student.getFieldList();
        
        ArrayList<String> header = new ArrayList<String>();

        //iterate through current objects and add them to new list
        Iterator<String> iterator = fields.iterator();
        while(iterator.hasNext()){
            header.add(iterator.next());
        }
        
        header.add("Delete");     
        return header;
    }

    @Override
    public View getListView(Iterable<Student> students, Iterable<String> header) {
        return new StudentListView(students, header);
    }
    

    @Override
    public Map<String, String> getFieldsValues(Student student) {
        return student.getFieldsValues();
    }

    @Override
    public String getRouteRoot() {
        return "student";
    }

}
