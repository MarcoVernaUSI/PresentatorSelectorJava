package com.controller;

import java.util.List;
import java.util.Map;

import com.Context;
import com.dbMapper.DbMapper;
import com.dbMapper.StudentMapper;
import com.model.Student;
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
        return StudentsListController.buildPage(context);     
    }

    @Override
    public Student build(Map<String, String> contextMap) {
        return new Student(contextMap);
    }

    @Override
    public Map<String, List<String>>  validate(Map<String, String> contextMap) {
        return null;
    }

}
