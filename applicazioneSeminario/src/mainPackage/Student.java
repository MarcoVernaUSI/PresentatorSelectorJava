package mainPackage;

public class Student {
    
    private final String _name;
    private final String _surname;
    
    public Student(String name, String surname) {
        _name = name;
        _surname = surname;
    }

    public String getName() {
        return _name;
    }

    public String getSurname() {
        return _surname;
    }
}
