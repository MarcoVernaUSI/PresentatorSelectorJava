package com.app.seminar.model;

import static com.github.manliogit.javatags.lang.HtmlHelper.*;

import com.github.manliogit.javatags.element.Element;

public class HTMLDetails extends SeminarDetails {


    public HTMLDetails(Seminar seminar) {
        super(seminar);
    }
    
    protected String getHeader2() {
        return div(attr("class -> row"),
            div(attr("class -> col-lg-8 col-md-7 col-sm-6"),
                h1(_seminar.getCourse().getCourseName())
              )
            );}
    
    @Override
    protected Element getHeader() {
        return "<html>\n<head>\n\t<title>"+getSeminar().getCourse().getCourseName()+" "+getSeminar().getStartDate()+"</title>\n</head>\n";
    }
    

    protected String getBody2() {
        String body = "<body>\n\t<div>"+getSeminar().getCourse().getCourseName()+"</div>\n\t<ul>\n"+
            "\t\t<li>"+getSeminar().getCourse().getDescription()+"</li>\n"+
            "\t\t<li>"+getSeminar().getLocation()+"</li>\n"+
           "\t\t<li>"+getSeminar().getSeatLeft()+"</li>\n\t</ul>"+
            "<div>partecipanti</div>\n\t</ul>\n";
            
           for (Student partecipant: getSeminar().getStudentsList()) {
                body+=("\t\t<li>"+partecipant.getName()+" "+partecipant.getSurname()+"</li>\n");
            }
        return body;
    }
    
    @Override
    protected String getBody() {
        String body = "<body>\n\t<div>"+getSeminar().getCourse().getCourseName()+"</div>\n\t<ul>\n"+
            "\t\t<li>"+getSeminar().getCourse().getDescription()+"</li>\n"+
            "\t\t<li>"+getSeminar().getLocation()+"</li>\n"+
           "\t\t<li>"+getSeminar().getSeatLeft()+"</li>\n\t</ul>"+
            "<div>partecipanti</div>\n\t</ul>\n";
            
           for (Student partecipant: getSeminar().getStudentsList()) {
                body+=("\t\t<li>"+partecipant.getName()+" "+partecipant.getSurname()+"</li>\n");
            }
        return body;
    }
    

    @Override
    protected String getFooter() {
        return "\t</ul>\n</body></html>";
    }

}
