package com.model;

import static com.model.Seminar.*;

import java.util.HashMap;
import java.util.Map;


public class SeminarValidation {
    
    
    
    public static Map<String,String> validate(Map<String,String> map){
        Map<String,String> errors = new HashMap<String, String>();
        
        for (String field : map.keySet()) {
            errors.put(field, validateField(field, map.get(field)));
        }
        
        return errors;
    }
    
    private static String validateField(String key, String value) {
        if (key.equals(NAME)) {
            return mandatoryField(value);}
        if (key.equals(LOCATION)) {
            return mandatoryField(value);}
        if (key.equals(TOTAL_SEATS)) {
            return positiveInteger(value);}
        if (key.equals(START)) {
            return mandatoryField(value);}
        else {return null;}
        
        
    }
    
    private static String mandatoryField(String value){
        if (value.equals("")) {
            return "Campo obbligatorio";}
        else {
            return null;}    
    }

    private static String positiveInteger(String value){ 
        if (Integer.parseInt(value) <= 0) {
            return "Il numero di posti deve essere maggiore di 0";}
        else {
            return null;}    
    }
}
