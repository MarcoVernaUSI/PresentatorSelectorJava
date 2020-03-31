package com.app.seminar.model;

public class CSVDetails extends SeminarDetails{

    public CSVDetails(Seminar seminar) {
        super(seminar);
    }

    @Override
    protected String getHeader() {
        return  "\""+getSeminar().getNumber()+"\";\""+getSeminar().getCourse().getCourseName()+"\";\""+getSeminar().getStartDate()+"\";\""+getSeminar().getLocation()+"\";\""+getSeminar().getSeatLeft()+"\"\n";
    }

    @Override
    protected String getBody() {
        String body ="";
        // Body        
        for (Student partecipant: getSeminar().getStudentsList()) {
            body+=("\""+partecipant.getName()+ "\";\""+partecipant.getSurname()+"\"\n");
        }
        return body;
    }

    @Override
    protected String getFooter() {
        return "";
    }
}
