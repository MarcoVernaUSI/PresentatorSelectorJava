package com.model;

import static com.model.Seminar.*;

import java.util.ArrayList;
import java.util.List;

public class SeminarValidation extends Validation{
    
    @Override
    protected List<String> validateField(String key, String value) {
        List<String> errors = new ArrayList<String>();
        if (key.equals(NAME)) {
            mandatoryField(value, errors);
            maximumNumberOfChar(value, errors, 15);
        }
        if (key.equals(LOCATION)) {
            mandatoryField(value, errors);
            maximumNumberOfChar(value, errors, 20);    
        }
        if (key.equals(TOTAL_SEATS)) {
            positiveInteger(value, errors, 100);}
        if (key.equals(START)) {
            mandatoryField(value, errors);}
        return errors;
    }
    
    
   
}
