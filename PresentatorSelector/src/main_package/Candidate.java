package main_package;

public class Candidate {
    private final String _name;
    private boolean _absent = false;
    
    // Old Constructor rimuovere dopo refactoring
    public Candidate(String fname, String surname) {
        _name = fname+" "+surname;
    }
    
    public Candidate(String name) {
        _name = name;
    }
    
    public boolean isAbsent() {
        return _absent;
    }

    public void setAbsent(boolean absent) {
        _absent = absent;
    }
    
    // Candidate to string method
    public String printCandidate(){
        return (_name);
    }
}