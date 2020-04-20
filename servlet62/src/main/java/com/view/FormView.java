package com.view;

import static com.github.manliogit.javatags.lang.HtmlHelper.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.github.manliogit.javatags.element.Element;

public class FormView implements View  {
    private final String _action;
    private final Map<String, String> _fields;
    private final Map<String, String> _errors;
    private final Feedback _result;
    private final Map<String, String> _contextMap;
    
    public FormView(String action, Map<String, String> fields, Map<String, String> errors,Feedback result, Map<String, String> contextMap) {
        _action = action;
       _fields = fields;
       _errors = errors;
       _result = result;
       _contextMap = contextMap;
    }
  
   
    @Override
    public Element[] getBody() {
        return new Element[]{
                div(attr("class -> row"),
                    div(attr("class -> col-md-6 col-md-offset-3"),
                        h1(attr("class -> page-header text-center"), "Create new Seminar"),
                        form(attr("class -> form-horizontal", "name -> createForm","role -> form", "method -> post", "action -> "+_action),
                            buildForm(_result)       
                                )
                            ),
                    getButtons()
                    
                    ),
                
        
        };
    }
    
    private Element getButtons() {
        return form(attr("id -> back", "name -> back", "action -> /course"),
            div(attr( "id -> backBtn", "class-> col-sm-10"),
                input(attr("id -> btn", "name -> submit", "type -> submit",  "value -> Back", "class -> btn btn-primary"))                                     )   
            );
    }
    
    
    
    
    private Element getField(Map.Entry<String, String> field) {
            Element input = input(attr("type -> "+field.getValue(), "class -> form-control", "id -> "+
                field.getKey(), "name -> "+field.getKey(), "placeholder -> "+field.getKey(), "value -> "
                    +(_contextMap.get(field.getKey())==null ? "" : _contextMap.get(field.getKey()))));
            
            if (field.getValue()=="number") {
                input =input(attr("type -> "+field.getValue(), "min -> 0", "class -> form-control", "id -> "+
                    field.getKey(), "name -> "+field.getKey(), "value -> "+(_contextMap.get(field.getKey())==null ? 0 : _contextMap.get(field.getKey())))); 
            }
            return input;
            
        }
    
    
    private List<Element> getFields(Feedback result, Map<String, String> errors) {
        List<Element> fields = new ArrayList<Element>();
        
      
        for(Map.Entry<String, String> field : _fields.entrySet()) {
            switch(result) {
            case DEFAULT:
                fields.add(div(attr("class -> form-group"),
                label(attr("for -> "+field.getKey(), "class -> col-sm-2 control-label"), field.getKey()),
                div(attr("class-> col-sm-10"),
                  getField(field)
                    )
                ));
                break;
            default:  
                String validationStyle;
                Element[] Divs;
                if (errors.get(field.getKey())!= null) {
                    validationStyle = "has-error";
                    Divs = new Element[2];
                    Divs[0] = getField(field);
                    Divs[1] = span(attr("class -> help-block"), errors.get(field.getKey()));    
                    
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
    
    
    
    
    
    private Element[] buildForm(Feedback result) {
        List<Element> fields = getFields(result, _errors);
        
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

}
