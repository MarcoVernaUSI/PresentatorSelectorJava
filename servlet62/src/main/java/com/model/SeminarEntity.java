package com.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.Context;
import com.controller.ListController;
import com.dbMapper.DbMapper;
import com.dbMapper.SeminarMapper;
import com.view.SeminarListView;
import com.view.View;

public class SeminarEntity implements Entity<Seminar>{

    @Override
    public Map<String, List<String>> validate(Map<String, String> contextMap) {
        return new SeminarValidation().validate(contextMap);
    }

    @Override
    public DbMapper<Seminar> getMapper(Context context) {
        return new SeminarMapper(context.connection());
    }

    @Override
    public View getListView(Context context) {
        return new ListController<Seminar>(new SeminarEntity()).buildPage(context);     
    }
    
    @Override
    public SeminarListView getListView(Iterable<Seminar> seminars, Iterable<String> header) {
        return new SeminarListView(seminars, header);     
    }

    @Override
    public Seminar build(Map<String, String> contextMap) {
        return new Seminar(contextMap);
    }

    @Override
    public Map<String, String> getFieldsTypes() {
        return Seminar.getFieldsTypes();
    }

    @Override
    public boolean isValid(Map<String, String> contextMap) {
        return new SeminarValidation().isValid(contextMap);
    }
    
    @Override
    public Iterable<String> getFieldList(){
        Iterable<String> fields =Seminar.getFieldList();
        
        ArrayList<String> header = new ArrayList<String>();

        //iterate through current objects and add them to new list
        Iterator<String> iterator = fields.iterator();
        while(iterator.hasNext()){
            header.add(iterator.next());
        }
        
        header.add("csv");
        header.add("html");
        header.add("Delete");
        return header;
    }

    @Override
    public Map<String, String> getFieldsValues(Seminar seminar) {
        return seminar.getFieldsValues();
    }

    @Override
    public String getRouteRoot() {
        return "course";
    }
    }
