package mainPackage;

public class SeminarHTLM extends SeminarDetails {
 
    public SeminarHTLM(String seminarName, String location, int totalSeats, Course course) {
        super(seminarName, location, totalSeats, course);
    }

    @Override
    protected String getHeader() {
        return "<html>\n<head>\n\t<title>"+getCourse().getCourseName()+" "+getCourse().getStartingDate()+"</title>\n</head>\n";
    }

    @Override
    protected String getBody() {
        String body = "<body>\n\t<div>"+getCourse().getCourseName()+"</div>\n\t<ul>\n"+
            "\t\t<li>"+getCourse().getDescription()+"</li>\n"+
            "\t\t<li>"+getLocation()+"</li>\n"+
            "\t\t<li>"+getSeatLeft()+"</li>\n\t</ul>"+
            "<div>partecipanti</div>\n\t</ul>\n";
            
            for (Student partecipant: getStudentsList()) {
                body+=("\t\t<li>"+partecipant.getName()+" "+partecipant.getSurname()+"</li>\n");
            }
        return body;
    }

    @Override
    protected String getFooter() {
        return "\t</ul>\n</body></html>";
    }

}
