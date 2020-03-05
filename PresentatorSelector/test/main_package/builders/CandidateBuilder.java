package main_package.builders;

import main_package.Candidate;

public class CandidateBuilder {
    
    public static final String Fname = "Bob";
    public static final String Surname = "Semple";
    public static final boolean Absent = false;
    
    private final Candidate _candidate;
    
    public CandidateBuilder() {
        _candidate = new Candidate(Fname,Surname);
        _candidate.setAbsent(Absent);    
        }
    
    public CandidateBuilder withName(String _fname, String _surname){
        _candidate.setFname(_fname);
        _candidate.setSurname(_surname);
        return this;
    }
    
    public CandidateBuilder withAbsent(boolean _absent){
        _candidate.setAbsent(_absent);
        return this;
    }
    
    public Candidate build() {
        return _candidate;
    }
    
    
}
