package com.model;

import static com.model.Student.*;

import java.util.ArrayList;
import java.util.List;

public class StudentValidation extends Validation{
    
    @Override
    protected List<String> validateField(String key, String value) {
        List<String> errors = new ArrayList<String>();
        if (key.equals(FIRSTNAME)) {
            mandatoryField(value, errors);
        }
        if (key.equals(LASTNAME)) {
            mandatoryField(value, errors);
        }
        return errors;
    }
    
    
   
}
