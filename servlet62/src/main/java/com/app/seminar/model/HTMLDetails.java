package com.app.seminar.model;

public class HTMLDetails extends SeminarDetails {


    public HTMLDetails(Seminar seminar) {
        super(seminar);
    }

    //@Override
    //protected String getHeader() {
    //    return "<html>\n<head>\n\t<title>"+getSeminar().getCourse().getCourseName()+" "+getSeminar().getStartDate()+"</title>\n</head>\n";
    //}
    
    @Override
    protected String getHeader() {
        return "<html>\n<head>\n\t<title>"+getSeminar().getCourse().getCourseName()+" "+getSeminar().getStartDate()+"</title>\n</head>\n";
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
