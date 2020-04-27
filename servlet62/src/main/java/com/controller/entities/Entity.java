package com.controller.entities;

import java.sql.Connection;
import java.util.Map;

import com.Context;
import com.dbMapper.DbMapper;
import com.model.validation.Validation;
import com.view.View;

public abstract class Entity {
    
    String _route;
    Class<DbMapper> _dbMapperClass;
    Validation _validation;
    
    public Entity(String route, Class dbMapper, Validation validation) {
        _route = route;
        _dbMapperClass = dbMapper;
        _validation = validation;
   }
    
    
    public String getRoute() {
        return _route;
    };
    
    public boolean isValid(Map<String,String> contextMap) {
        return _validation.isValid(contextMap);
    }
    
    public DbMapper getDbMapper(Context context) {
        try {
            return _dbMapperClass.getConstructor(Connection.class).newInstance(context.connection());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }        
    }
    
    public abstract Map<String,String> getValues(Context context, String id);
    
    
    public abstract View getListView(Context context);
    
    
    public abstract View getFormView(Context context);
    
    
    public abstract View getFormView(Context context, Map<String,String> defaultFields);
    
}
