package main_package;

public class Candidate {
    private String _fname;
    private String _surname;
    private boolean _absent = false;
    
    public Candidate(String fname, String surname) {
        _fname = fname;
        _surname = surname;
    }
    
    public boolean isAbsent() {
        return _absent;
    }

    public void setAbsent(boolean absent) {
        _absent = absent;
    }

    public String getFname() {
        return _fname;
    }
    
    public void setFname(String surname) {
        _fname = surname;
    }
   
    public String getSurname() {
        return _surname;
    }
    
    public void setSurname(String surname) {
        _surname = surname;
    }
    
    // Candidate to string method
    public String printCandidate(){
        return (_fname + " " + _surname);
    }
}