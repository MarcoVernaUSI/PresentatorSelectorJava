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

    // add speaker to the list
    public void addSpeaker(String fname, String surname) {
        Candidate speaker = new Candidate(fname, surname);
        _candidates.add(speaker);
        _db.update(_candidates);
    }
    
    // return a speaker
    public Candidate getSpeaker(int i) {
        return _candidates.get(i);
    }
    
    // return random speaker
    public Candidate getRandomSpeaker() {
        // Create a temporary list without the absent
        List<Candidate> listWithoutAbsents = new ArrayList<>(_candidates);
        for (int i = listWithoutAbsents.size()-1; i >= 0; i--) {
            if (listWithoutAbsents.get(i).isAbsent()) {
                listWithoutAbsents.remove(i);
            }}
        int i = (int)(Math.random()*listWithoutAbsents.size());
        // if all absents
        if (listWithoutAbsents.size()!=0) {
            return listWithoutAbsents.get(i);
        }else {
            return null;
        }
    }
    
    // remove a speaker from the list
    public void removeSpeakers(String speaker) {
        int idx = 0;
        for (Candidate candidate : _candidates) {
            if (candidate.printCandidate().equals(speaker)) {
                _candidates.remove(idx);
                break;
            }
            idx++;     
        }
        _db.update(_candidates);
    }

    // set a given speaker absent
    public void setAbsent(String speaker) {
        for (Candidate candidate : _candidates) {
            if (candidate.printCandidate().equals(speaker)) {
                candidate.setAbsent(!candidate.isAbsent());
                break;
            }
        }
    }
    
    // check if a given speaker is absent
    public boolean checkAbsent(String speaker) {
        boolean _default = false;
        for (Candidate candidate : _candidates) {
            if (candidate.printCandidate().equals(speaker)){ 
                _default = candidate.isAbsent();
                }}
        return _default;
    }
    
    public List<String> printCandidates() {
        List<String>candidatesList = new ArrayList<>();
        for (Candidate candidate : _candidates) {
            candidatesList.add(candidate.printCandidate());
        }
        return candidatesList;
    }

}
