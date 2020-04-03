package main_package;

public class Candidate {
    private final String _name;
    private boolean _absent = false;
    
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