package com.model;

import java.util.List;
import java.util.Map;

import com.Context;
import com.dbMapper.DbMapper;
import com.view.View;

public interface Entity<T> {
    boolean isValid(Map<String,String> contextMap);
    Map<String,String> getFieldsTypes();
    DbMapper<T> getMapper(Context context);
    View getListView(Context context);
    T build(Map<String,String> contextMap);
    Map<String, List<String>> validate(Map<String, String> contextMap);
    Iterable<String> getFieldList();
    Map<String,String> getFieldsValues(T object);
    View getListView(Iterable<T> entries, Iterable<String> header);
    String getRouteRoot();
}
