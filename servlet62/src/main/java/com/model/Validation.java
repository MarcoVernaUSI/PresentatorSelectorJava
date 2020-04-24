package com.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


public abstract class Validation {
    
    public boolean isValid(Map<String,String> map) {
        boolean valid = true;
        for (List<String> value : validate(map).values()) {
            if (!value.isEmpty()) {
                valid = false;
            }
        }
        return valid;
    }
    
    public Map<String,List<String>> validate(Map<String,String> map){
        Map<String,List<String>> errors = new HashMap<String, List<String>>();
        
        for (String field : map.keySet()) {
            errors.put(field, validateField(field, map.get(field)));
        }
        return errors;
    }
    
    abstract protected List<String> validateField(String key, String value);
    
    protected static void mandatoryField(String value, List<String> errors){
        if (value.equals("")) {
            errors.add("Campo obbligatorio");}
    }
    
    protected static void maximumNumberOfChar(String value, List<String> errors, int numberOfChar){
        if (value.length()>numberOfChar) {
            errors.add("Il campo non può superare i "+numberOfChar+" caratteri");}
    }

    protected static void positiveInteger(String value, List<String> errors, int maxSeats){ 
        if (Integer.parseInt(value) <= 0 || Integer.parseInt(value) > maxSeats) {
            errors.add("Il numero di posti deve essere compreso tra 0 e "+ maxSeats);}    
    }
}
