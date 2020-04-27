package com.controller.entities;

import java.util.List;
import java.util.Map;

import com.Context;
import com.dbMapper.SeminarMapper;
import com.model.Seminar;
import com.model.validation.SeminarValidation;
import com.view.FormView;
import com.view.SeminarListView;
import com.view.View;

public class SeminarEntity implements Entity{
    
    @Override
    public String getRoute() {
        return "course";
    }
    
    
    @Override
    public void create(Context context) {
        new SeminarMapper(context.connection()).insert(
            new Seminar(context.requestMap()));   
    }
    
    
    @Override
    public boolean isValid(Context context) {
        return new SeminarValidation().isValid(context.requestMap());
    }
    
    
    @Override
    public View getFormView(Context context, Map<String,String> defaultFields) {
        Map<String,String> fields = Seminar.getFieldsTypes();
        Map<String,List<String>> errors = new SeminarValidation().validate(context.requestMap());
        return new FormView(context.requestUri(), fields, errors, context, defaultFields);
    }
    
    
    @Override
    public View getFormView(Context context) {
        return getFormView(context,context.requestMap());
    }
    
    
    
    @Override
    public void delete(Context context, String id) {
        new SeminarMapper(context.connection()).delete(id); 
    }
    
    
    @Override
    public Map<String, String> getFieldsValues(Context context, String id) {
        return new SeminarMapper(context.connection()).findById(id).getFieldsValues();
    }
    
    
    @Override
    public void update(Context context, String id) {
        new SeminarMapper(context.connection()).update(new Seminar(context.requestMap()), id);
    }
    
    
    @Override
    public SeminarListView getListView(Context context) {
        Iterable<Seminar> seminars = new SeminarMapper(context.connection()).findAll();   
        return new SeminarListView(seminars);     
    }
    

   
    }
