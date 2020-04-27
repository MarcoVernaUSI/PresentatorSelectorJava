package com.controller.entities;

import java.util.Map;

import com.Context;
import com.view.View;


// tolgo passaggio di context ovunque
public interface Entity {
    
    String getRoute();
    
    boolean isValid(Context context);
    void create(Context context);
    void update(Context context, String id);
    void delete(Context context, String id);
    
    View getListView(Context context);
    View getFormView(Context context);
    View getFormView(Context context, Map<String,String> defaultFields);
    
    Map<String,String> getFieldsValues(Context context, String id);
}
