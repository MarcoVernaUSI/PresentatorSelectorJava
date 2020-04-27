package com.controller.entities;

import java.util.Map;

import com.Context;
import com.dbMapper.SeminarMapper;
import com.model.Seminar;
import com.model.validation.SeminarValidation;
import com.view.SeminarFormView;
import com.view.SeminarListView;
import com.view.View;

public class SeminarEntity extends Entity{
    
    public SeminarEntity() {
        super("course", SeminarMapper.class, new SeminarValidation());
    }

    
    @Override
    public Map<String, String> getValues(Context context, String id) {
        return new SeminarMapper(context.connection()).findById(id).getFieldsValues();
    }
    
   
    @Override
    public View getFormView(Context context, Map<String,String> defaultFields) {
        return new SeminarFormView(context.requestUri(), context, defaultFields);
    }
    
    
    @Override
    public View getFormView(Context context) {
        return getFormView(context,context.requestMap());
    }
    
   
    
    @Override
    public SeminarListView getListView(Context context) {
        Iterable<Seminar> seminars = new SeminarMapper(context.connection()).findAll();   
        return new SeminarListView(seminars);     
    }
   
    }
