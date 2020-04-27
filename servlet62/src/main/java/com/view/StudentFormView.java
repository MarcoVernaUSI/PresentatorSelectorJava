package com.view;

import java.util.List;
import java.util.Map;

import com.Context;
import com.model.Student;
import com.model.validation.StudentValidation;

public class StudentFormView extends FormView{
    
    Context _context;

    public StudentFormView(String action, Context context, Map<String, String> defaultFields) {
        super(action, context, defaultFields);
        _context = context;
    }

    @Override
    protected Map<String, String> getFields() {
        return Student.getFieldsTypes();
    }

    @Override
    protected Map<String, List<String>> getErrors() {
        return new StudentValidation().validate(_context.requestMap());
    }

}
