package com.view;

import static com.github.manliogit.javatags.lang.HtmlHelper.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.Context;
import com.github.manliogit.javatags.element.Element;

public abstract class FormView implements View  {
    private final String _action;
    private final Context _context;
    private final Map<String, String> _defaultFields; 
    
   
    
    public FormView(String action, Context context, Map<String, String> defaultFields) {
        _action = action;
       _context = context;
       _defaultFields =defaultFields;
    }
    
   
    @Override
    public Element[] getBody() {
        return new Element[]{
                div(attr("clfields;ass -> row"),
                    div(attr("class -> col-md-6 col-md-offset-3"),
                        form(attr("class -> form-horizontal", "name -> createForm","role -> form", "method -> post", "action -> "+_action),
                            buildForm()       
                                )
                            )
                    ),
        };
    }
    
    
    private Element getField(Map.Entry<String, String> field) {
        return input(attr("type -> "+field.getValue(), "min -> 0","class -> form-control", "id -> "+
            field.getKey(), "name -> "+field.getKey(), "placeholder -> "+field.getKey(), 
            "value -> "+(_defaultFields.get(field.getKey())==null ? (field.getValue() == "number" ? 0 : "") 
                : _defaultFields.get(field.getKey()))));
        }
    
    
    private List<Element> getFields(Map<String, List<String>> errors) {
        List<Element> fields = new ArrayList<Element>();
      
        for(Map.Entry<String, String> field : getFields().entrySet()) {
            if(!_context.post()) {
                fields.add(div(attr("class -> form-group"),
                label(attr("for -> "+field.getKey(), "class -> col-sm-2 control-label"), field.getKey()),
                div(attr("class-> col-sm-10"),
                  getField(field)
                    )
                ));
            } else {  
                String validationStyle;
                Element[] Divs;
                if (!errors.get(field.getKey()).isEmpty()) {
                    validationStyle = "has-error";
                    
                    Divs = new Element[errors.get(field.getKey()).size()+1];
                    Divs[0] = getField(field);
                    
                    for (int i = 1; i<errors.get(field.getKey()).size()+1; i++) {
                        Divs[i] = span(attr("class -> help-block"), errors.get(field.getKey()).get(i-1));    
                    }
                    
                } else {
                    validationStyle = "has-success";
                    Divs = new Element[1];
                    Divs[0] = getField(field);
                }
                
                
                fields.add(div(attr("class -> form-group "+ validationStyle),
                    label(attr("for -> "+field.getKey(), "class -> col-sm-2 control-label"),  field.getKey()),
                    div(attr("class-> col-sm-10"),
                        Divs
                        ))
                    );
            }
        }
        return fields;
    }
    

    private Element[] buildForm() {
        List<Element> fields = getFields(getErrors());
        
        fields.add(
        div(attr("class -> form-group"),
            div(attr("class-> col-sm-10"),
                input(attr("id -> submit", "name -> submit", "type -> submit", "value -> Send", "class -> btn btn-primary"))    
                )
            ));
        
        Element[] elements = new Element[fields.size()];
        for (int i = 0; i<fields.size(); i++) {
            elements[i]=fields.get(i);
        }
        return elements;
        
    }

    protected abstract Map<String, String> getFields();
    
    protected abstract Map<String, List<String>> getErrors();
}
