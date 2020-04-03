package main_package;

import java.util.List;

public class Selector{
    private final Candidates _candidates;        
    private final Log _log;
    
    public Selector(String candidatesPath, String logPath) {
        _candidates = new Candidates(candidatesPath);
        _log = new Log(logPath);
    }
    
    public String select() {
        if (_candidates.allAbsent()) {
            return "No speaker avaiable!";
        } else {
            return _candidates.getRandomSpeaker().printCandidate();
        }
    }
    
    public void remove(String speaker){
        _candidates.removeSpeaker(speaker);  
    }
    
    public void add(String name){
        if (!_candidates.printCandidates().contains(name)) {
            _candidates.addSpeaker(name);   
        }
    }
    
    public List<String> getSpeakers() {
        return _candidates.printCandidates();
    }

    public String printLog() {
        return _log.printLog();
    }
    
    public void setAbsent(String speaker) {
        _candidates.setAbsent(speaker);
        _log.saveEntry(speaker,_candidates.checkAbsent(speaker));
    }

    public boolean checkAbsent(String speaker) {
        return _candidates.checkAbsent(speaker);
    }
    
    public  void clearLog() {
        _log.clearLog();
    }
}