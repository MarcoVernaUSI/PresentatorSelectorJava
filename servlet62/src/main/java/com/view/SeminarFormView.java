package com.view;

import java.util.List;
import java.util.Map;

import com.Context;
import com.model.Seminar;
import com.model.validation.SeminarValidation;

public class SeminarFormView extends FormView{
    
    Context _context;

    public SeminarFormView(String action, Context context, Map<String, String> defaultFields) {
        super(action, context, defaultFields);
        _context = context;
    }

    @Override
    protected Map<String, String> getFields() {
        return Seminar.getFieldsTypes();
    }

    @Override
    protected Map<String, List<String>> getErrors() {
        return new SeminarValidation().validate(_context.requestMap());
    }

}
