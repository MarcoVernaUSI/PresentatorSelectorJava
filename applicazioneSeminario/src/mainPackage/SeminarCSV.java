package mainPackage;

public class SeminarCSV extends SeminarDetails {

    public SeminarCSV(String seminarName, String location, int totalSeats, Course course) {
        super(seminarName, location, totalSeats, course);
    }

    @Override
    protected String getHeader() {
        return  "\""+getCourse().getNumber()+"\";\""+getCourse().getCourseName()+"\";\""+getCourse().getStartingDate()+"\";\""+getLocation()+"\";\""+getSeatLeft()+"\"\n";
    }

    @Override
    protected String getBody() {
        String body ="";
        // Body        
        for (Student partecipant: getStudentsList()) {
            body+=("\""+partecipant.getName()+ "\";\""+partecipant.getSurname()+"\"\n");
        }
        return body;
    }

    @Override
    protected String getFooter() {
        return "";
    }

}
