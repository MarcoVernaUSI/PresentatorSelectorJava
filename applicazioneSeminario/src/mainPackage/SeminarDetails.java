package mainPackage;

import java.util.ArrayList;
import java.util.List;

public abstract class SeminarDetails {
    private final String seminarName;
    private final String location;
    private final int totalSeats;
    private final Course course;
    private final List<Enrollment> enrollments;
    

    public SeminarDetails(String seminarName, String location, int totalSeats, Course course) {
        this.seminarName = seminarName;
        this.location = location;
        this.totalSeats = totalSeats;
        this.course = course;
        this.enrollments = new ArrayList<>();
    }
    
    public void addEnrollment(Enrollment enrollment) {
        enrollments.add(enrollment);
    }

    public int getSeatLeft() {
        return totalSeats - enrollments.size();
    }

    public String getLocation() {
        return location;
    }

    public List<Student> getStudentsList() {
        List<Student> studentList = new ArrayList<>();
        for(Enrollment enrollment : enrollments) {
            studentList.add(enrollment.getStudent());
        }
        return studentList;
    }

    public String getName() {
        return seminarName+" "+getCourse().getCourseName()+" "+getCourse().getNumber();
    }

    protected abstract String getHeader();

    protected abstract String getBody();

    protected abstract String getFooter();
    
    public String getDescription() {
        return getHeader()+getBody()+getFooter();
    }

    public Course getCourse() {
        return course;
    }
}


