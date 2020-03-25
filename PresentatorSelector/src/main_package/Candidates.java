package main_package;

import java.util.ArrayList;
import java.util.List;

public class Candidates {
    private final List<Candidate> _candidates;
    private final JsonDb<Candidate> _db;

    
    public Candidates(String path) {
        _db = new JsonDb<>(path,new CandidateParser());
        _candidates = _db.load();
    }

    
    public void addSpeaker(String name) {
        _candidates.add(new Candidate(name));
        _db.update(_candidates);
    }
    
    
    public Candidate getSpeaker(int i) {
        return _candidates.get(i);
    }
    
    
    public boolean allAbsent() {
        for (Candidate candidate : _candidates) {
            if (!candidate.isAbsent()) {
                return false;
            }
        }
        return true;
    }
    

    public Candidate getRandomSpeaker() {
        List<Candidate> listWithoutAbsents = new ArrayList<>();
        for (Candidate candidate : _candidates) {
            if (!candidate.isAbsent()) {
                listWithoutAbsents.add(candidate);
            }
        }
        int i = (int)(Math.random()*listWithoutAbsents.size());
        return listWithoutAbsents.get(i);
    }
    

    public void removeSpeaker(String speaker) {
        _candidates.remove(findIndex(speaker));
        _db.update(_candidates);
    }


    public void setAbsent(String speaker) {
        find(speaker).setAbsent(!find(speaker).isAbsent());
    }
    
    
    public boolean checkAbsent(String speaker) {
        return find(speaker).isAbsent();
    }
    
    
    private Candidate find(String speaker){
        return _candidates.get(findIndex(speaker));
    }
    
    
    private int findIndex(String speaker){
        int idx = 0;
        for (Candidate candidate : _candidates) {
            if (candidate.printCandidate().equals(speaker)){ 
                return idx;
            }
            idx++;
        }
        throw new RuntimeException("Speaker not found!");
    }
    
    
    public List<String> printCandidates() {
        List<String>candidatesList = new ArrayList<>();
        for (Candidate candidate : _candidates) {
            candidatesList.add(candidate.printCandidate());
        }
        return candidatesList;
    }

}
