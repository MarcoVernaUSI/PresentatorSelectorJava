package main_package;

public class Candidate {
    private final String _name;
    private final String _fname;
    private final String _surname;
    private boolean _absent = false;
    
    public Candidate(String fname, String surname) {
        _fname = fname;
        _surname = surname;
        _name = fname+" "+surname;
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
   
    public String getSurname() {
        return _surname;
    }
    
    // Candidate to string method
    public String printCandidate(){
        return (_name);
    }
}