package mainPackage;

import java.util.Date;

public class Course {
    private final String _courseName;
    private final int _number;
    private final String _description;
    private final Date _startingDate;
    
    public Course(String courseName, int number, String description, Date startingDate) {
        _courseName = courseName;
        _number = number;
        _description = description;
        _startingDate = startingDate;
    }

    public String getCourseName() {
        return _courseName;
    }

    public int getNumber() {
        return _number;
    }

    public String getDescription() {
        return _description;
    }

    public Date getStartingDate() {
        return _startingDate;
    }
}
