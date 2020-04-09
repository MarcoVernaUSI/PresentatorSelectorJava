package com.view;

import static com.github.manliogit.javatags.lang.HtmlHelper.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.github.manliogit.javatags.element.Element;

public class FormView implements View  {
    private final String _action;
    //private final Iterable<String> _fields;
    private final Map<String, String> _fields;
    private final Feedback _result;
    
    public FormView(String action, Map<String, String> fields, Feedback result) {
        _action = action;
       _fields = fields;
       _result = result;
    }
    
    @Override
    public Element getHeader() {
        return div(attr("class -> row"),
            div(attr("class -> col-lg-8 col-md-7 col-sm-6"),
                h1("djxjx")
              )
         );
    }
   
    @Override
    public Element[] getBody() {
        return new Element[]{
                div(attr("class -> row"),
                    div(attr("class -> col-md-6 col-md-offset-3"),
                        h1(attr("class -> page-header text-center"), "Create new Seminar"),
                        form(attr("class -> form-horizontal", "role -> form", "method -> post", "action -> "+_action),
                            getFields()       
                                )
                            ),

                    errorMessage(),
                    getButtons()
                    
                    ),
                
        
        };
    }
    
    private Element getButtons() {
        return form(attr("id -> back", "name -> back", "action -> /"),
            div(attr( "id -> backBtn", "class-> col-sm-10"),
                input(attr("id -> btn", "name -> submit", "type -> submit",  "value -> Back", "class -> btn btn-primary"))                                     )   
            );
    }
    
    
    private Element[] getFields() {
        List<Element> fields = new ArrayList<Element>();
        
        for(Map.Entry<String, String> field : _fields.entrySet()) {
            Element input = input(attr("type -> "+field.getValue(), "class -> form-control", "id -> "+field.getKey(), "name -> "+field.getKey(), "placeholder -> "+field.getKey()));
            
            if (field.getValue()=="number") {
                input =input(attr("type -> "+field.getValue(), "min -> 0", "class -> form-control", "id -> "+field.getKey(), "name -> "+field.getKey(), "value -> 0")); 
            }
            
            
            fields.add(div(attr("class -> form-group"),
                                label(attr("for -> "+field.getKey(), "class -> col-sm-2 control-label"), field.getKey()),
                                div(attr("class-> col-sm-10"),
                                   
                                    input
                                    )
                                )
                 );    
        }
        
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
    
    private Element errorMessage() {
        switch (_result){
            case SUCCESS:
                return  p(attr("style -> color:green"), "Seminar inserted with success");
            case ERROR:
                return  p(attr("style -> color:red"), "Error: You must compile all fields!");
            default :
                 return p( "");
        }
        
    }
}
