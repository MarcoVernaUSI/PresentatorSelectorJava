package main_package.builders;

import main_package.Candidate;

public class CandidateBuilder {
    
    public static final String fname = "Bob";
    public static final String surname = "Semple";
    public static final boolean absent = false;
    
    private final Candidate _candidate;
    
    public CandidateBuilder() {
        _candidate = new Candidate(fname,surname);
        _candidate.setAbsent(absent);    
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
