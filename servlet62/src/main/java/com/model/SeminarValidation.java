package com.model;

import static com.model.Seminar.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public abstract class SeminarValidation {
    
    public static boolean isValid(Map<String,String> map) {
        boolean valid = true;
        for (List<String> value : validate(map).values()) {
            if (!value.isEmpty()) {
                valid = false;
            }
        }
        return valid;
    }
    
    public static Map<String,List<String>> validate(Map<String,String> map){
        Map<String,List<String>> errors = new HashMap<String, List<String>>();
        
        for (String field : map.keySet()) {
            errors.put(field, validateField(field, map.get(field)));
        }
        return errors;
    }
    
    private static List<String> validateField(String key, String value) {
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
    
    private static void mandatoryField(String value, List<String> errors){
        if (value.equals("")) {
            errors.add("Campo obbligatorio");}
    }
    
    private static void maximumNumberOfChar(String value, List<String> errors, int numberOfChar){
        if (value.length()>numberOfChar) {
            errors.add("Il campo non può superare i "+numberOfChar+" caratteri");}
    }

    private static void positiveInteger(String value, List<String> errors, int maxSeats){ 
        if (Integer.parseInt(value) <= 0 || Integer.parseInt(value) > maxSeats) {
            errors.add("Il numero di posti deve essere compreso tra 0 e "+ maxSeats);}    
    }
}
