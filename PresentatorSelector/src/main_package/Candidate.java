package main_package;

public class Candidate {
    private final String fname;
    private String surname;
    private boolean absent = false;
    
    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Candidate(String fname, String surname) {
        this.fname = fname;
        this.surname = surname;
    }
    
    public boolean isAbsent() {
        return absent;
    }

    public void setAbsent(boolean absent) {
        this.absent = absent;
    }

    public String getFname() {
        return fname;
    }
    
    public void setFname(String surname) {
        this.surname = surname;
    }
   
    public String getSurname() {
        return surname;
    }
    
    // Candidate to string method
    public String printCandidate(){
        return (fname + " " + surname);
    }
}