package com.controller;

import java.util.List;
import java.util.Map;

import com.Context;
import com.dbMapper.DbMapper;
import com.view.View;

public interface Entity<T> {
    Map<String,String> getFieldsTypes();
    DbMapper<T> getMapper(Context context);
    View getListView(Context context);
    T build(Map<String,String> contextMap);
    Map<String, List<String>> validate(Map<String, String> contextMap);
}
