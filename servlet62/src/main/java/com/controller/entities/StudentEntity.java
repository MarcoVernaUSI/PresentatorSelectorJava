package com.controller.entities;

import java.util.Map;

import com.Context;
import com.dbMapper.StudentMapper;
import com.model.Student;
import com.model.validation.StudentValidation;
import com.view.StudentFormView;
import com.view.StudentListView;
import com.view.View;

public class StudentEntity extends Entity{

    
    public StudentEntity() {
        super("student", StudentMapper.class, new StudentValidation());
    }
    
    
    @Override
    public Map<String, String> getValues(Context context, String id) {
        return new StudentMapper(context.connection()).findById(id).getFieldsValues();
    }
    
    
    @Override
    public View getFormView(Context context, Map<String,String> defaultFields) {
        return new StudentFormView(context.requestUri(), context, defaultFields);
    }
    
    
    @Override
    public View getFormView(Context context) {
        return getFormView(context,context.requestMap());
    }

    @Override
    public StudentListView getListView(Context context) {
        Iterable<Student> students = new StudentMapper(context.connection()).findAll();
        return new StudentListView(students);     
    }
    


  
}
