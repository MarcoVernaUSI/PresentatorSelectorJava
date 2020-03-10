package main_package;

import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class Candidates {
    

    private final List<Candidate> _candidates;
    private JsonDatabase _db;
    
    public Candidates() {
        _candidates = new ArrayList<>();
    }  

    public Candidates loadDatabase(JsonDatabase db){
        _db = db;
        _candidates.clear();
        for (JSONObject obj :  db.load()) {
            _candidates.add(new Candidate((String) obj.get("fname"), (String) obj.get("surname")));   
        }
        return this;
    }
    

    public void dumpDatabase(){
        JSONArray objectsList = new JSONArray();
        for (Candidate candidate : _candidates) {
            JSONObject obj = new JSONObject();
            obj.put("fname",candidate.getFname());
            obj.put("surname",candidate.getSurname());
            objectsList.add(obj);
        }
        _db.update(objectsList);
    }
    
    
    // add speaker to the list
    public void addSpeaker(String fname, String surname) {
        Candidate speaker = new Candidate(fname, surname);
        _candidates.add(speaker);
        dumpDatabase();
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
        dumpDatabase();
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
