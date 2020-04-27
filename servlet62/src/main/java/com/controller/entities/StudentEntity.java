package com.controller.entities;

import java.util.List;
import java.util.Map;

import com.Context;
import com.dbMapper.StudentMapper;
import com.model.Student;
import com.model.validation.StudentValidation;
import com.view.FormView;
import com.view.StudentListView;
import com.view.View;

public class StudentEntity implements Entity{

    @Override
    public String getRoute() {
        return "student";
    }
    
    
    @Override
    public void create(Context context) {
        new StudentMapper(context.connection()).insert(
            new Student(context.requestMap()));   
    }
    
    @Override
    public boolean isValid(Context context) {
        return new StudentValidation().isValid(context.requestMap());
    }
    

    @Override
    public View getFormView(Context context, Map<String,String> defaultFields) {
        Map<String,String> fields = Student.getFieldsTypes();
        Map<String,List<String>> errors = new StudentValidation().validate(context.requestMap());
        return new FormView(context.requestUri(), fields, errors, context, defaultFields);
    }
    
    
    @Override
    public View getFormView(Context context) {
        return getFormView(context,context.requestMap());
    }
    
    
    @Override
    public void delete(Context context, String id) {
        new StudentMapper(context.connection()).delete(id); 
    }
    
    
    @Override
    public Map<String, String> getFieldsValues(Context context, String id) {
        return new StudentMapper(context.connection()).findById(id).getFieldsValues();
    }
    
    
    @Override
    public void update(Context context, String id) {
        new StudentMapper(context.connection()).update(new Student(context.requestMap()), id);
    }

    
    @Override
    public StudentListView getListView(Context context) {
        Iterable<Student> students = new StudentMapper(context.connection()).findAll();
        return new StudentListView(students);     
    }
  
}
