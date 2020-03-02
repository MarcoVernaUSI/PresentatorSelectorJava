package main_package;

public class Candidate {
    private String fname;
    private String surname;
    
    public Candidate(String fname, String surname) {
        //super();
        this.fname = fname;
        this.surname = surname;
    }
    
    public String getFname() {
        return fname;
    }
    public void setFname(String fname) {
        this.fname = fname;
    }
    public String getSurname() {
        return surname;
    }
    public void setSurname(String surname) {
        this.surname = surname;
    }
    
    // Candidate to string method
    public String printCandidate(){
        return (this.getFname() + " " + this.getSurname());
    }
    
    
}