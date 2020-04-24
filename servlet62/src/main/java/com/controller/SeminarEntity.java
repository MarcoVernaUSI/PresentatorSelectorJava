package com.controller;

import java.util.List;
import java.util.Map;

import com.Context;
import com.dbMapper.DbMapper;
import com.dbMapper.SeminarMapper;
import com.model.Seminar;
import com.model.Validation;
import com.view.View;

public class SeminarEntity implements Entity<Seminar>{

    @Override
    public Map<String, List<String>> validate(Map<String, String> contextMap) {
        return Validation.validate(contextMap);
    }

    @Override
    public DbMapper<Seminar> getMapper(Context context) {
        return new SeminarMapper(context.connection());
    }

    @Override
    public View getListView(Context context) {
        return SeminarListController.buildPage(context);     
    }

    @Override
    public Seminar build(Map<String, String> contextMap) {
        return new Seminar(contextMap);
    }

    @Override
    public Map<String, String> getFieldsTypes() {
        return Seminar.getFieldsTypes();
    }
}
