package main_package;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Candidates {
    private ArrayList<Candidate> candidates;
    private final Log log;
    private final Database_interface db;

    public Candidates(ArrayList<Candidate> candidates, Log log, Database_interface db) {
        super();
        this.candidates = candidates;
        this.log = log;
        this.db = db;
    }
    
    // add speaker to the list
    public void addSpeaker(String fname, String surname) {
        Candidate speaker = new Candidate(fname, surname);
        this.candidates.add(speaker);
        Action action = Action.ADDED;
        this.log.save_entry(speaker, action);
        db.update_database(this.candidates);
    }
    
    // return a speaker
    public Candidate getSpeaker(int i) {
        return this.candidates.get(i);
    }
    
    // remove multiple speakers from the list
    public void removeSpeakers(Integer[] indices) {
        
        // sort the indices in reverse way
        Arrays.sort(indices, Collections.reverseOrder());
        Action action = Action.REMOVED;
        for (Integer i : indices) {
            // save on the log
            this.log.save_entry(this.candidates.get(i), action);
            this.candidates.remove((int)i);
        }
        this.db.update_database(this.candidates);
    }
    
    // Load the default list of names from the standard database
    public void restore_default() {
        this.candidates = this.db.load_database(true);
        this.db.update_database(this.candidates);
    }

    public ArrayList<Candidate> getCandidates() {
        return candidates;
    }

}


