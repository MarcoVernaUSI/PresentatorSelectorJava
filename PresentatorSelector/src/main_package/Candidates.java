package main_package;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Candidates {
    private final List<Candidate> candidates;
    private final Log log;
    private final DatabaseInterface db;

    public Candidates(List<Candidate> candidates, Log log, DatabaseInterface db) {
        super();
        this.candidates = candidates;
        this.log = log;
        this.db = db;
    }
     
    // add speaker to the list
    public void addSpeaker(String fname, String surname) {
        Candidate speaker = new Candidate(fname, surname);
        candidates.add(speaker);
        Action action = Action.ADDED;
        log.save_entry(speaker, action);
        db.updateDatabase(this.candidates);
    }
    
    // return a speaker
    public Candidate getSpeaker(int i) {
        return candidates.get(i);
    }
    
    // return random speaker
    public Candidate getRandomSpeaker() {
        // Create a temp list without the absent
        List<Candidate> tmpList = new ArrayList<>(candidates);
        for (int i = tmpList.size()-1; i >= 0; i--) {
            if (tmpList.get(i).isAbsent()) {
                tmpList.remove(i);
            }
         }
        int i = (int)(Math.random()*((tmpList.size()-1)+1));
        return tmpList.get(i);
    }
    
    // remove multiple speakers from the list
    public void removeSpeakers(Integer[] indices) {
        
        // sort the indices in reverse way
        Arrays.sort(indices, Collections.reverseOrder());
        Action action = Action.REMOVED;
        for (Integer i : indices) {
            // save on the log
            log.save_entry(candidates.get(i), action);
            candidates.remove((int)i);
        }
        db.updateDatabase(candidates);
    }
    
 // remove multiple speakers from the list
    public void removeSpeakers(String speaker) {
        Action action = Action.REMOVED;
        int idx = 0;
        for (Candidate candidate : candidates) {
            if (candidate.printCandidate().equals(speaker)) {
                log.save_entry(candidate, action);
                candidates.remove(idx);
                break;
            }
            idx++;
            // save on the log
        }
        db.updateDatabase(candidates);
    }

    public void setAbsent(String speaker) {
        for (Candidate candidate : candidates) {
            if (candidate.printCandidate().equals(speaker)) {
                candidate.setAbsent(!candidate.isAbsent());
                break;
            }
        }
    }
    
    public boolean checkAbsent(String speaker) {
        boolean _default = false;
        for (Candidate candidate : candidates) {
            if (candidate.printCandidate().equals(speaker)){ 
                _default = candidate.isAbsent();
                }
        }
        return _default;
    }
    
    
    public List<Candidate> getCandidates() {
        return candidates;
    }

}


