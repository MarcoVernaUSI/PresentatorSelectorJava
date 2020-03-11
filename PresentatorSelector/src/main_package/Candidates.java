package main_package;

import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONObject;

public class Candidates extends JsonDb<Candidate>{

    public Candidates(String path) {
        super(path);
    }
    
    @Override
    public JSONObject writeObject(Candidate candidate) {
        JSONObject obj = new JSONObject();
        obj.put("fname",candidate.getFname());
        obj.put("surname",candidate.getSurname());
        return obj;
    }
    
    @Override
    public Candidate readObject(JSONObject obj) {
        return new Candidate((String) obj.get("fname"), (String) obj.get("surname"));
    }    
    ////////////////////////////
    

    // add speaker to the list
    public void addSpeaker(String fname, String surname) {
        Candidate speaker = new Candidate(fname, surname);
        _database.add(speaker);
        update();
    }
    
    // return a speaker
    public Candidate getSpeaker(int i) {
        return _database.get(i);
    }
    
    // return random speaker
    public Candidate getRandomSpeaker() {
        // Create a temporary list without the absent
        List<Candidate> listWithoutAbsents = new ArrayList<>(_database);
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
        for (Candidate candidate : _database) {
            if (candidate.printCandidate().equals(speaker)) {
                _database.remove(idx);
                break;
            }
            idx++;     
        }
        update();
    }

    // set a given speaker absent
    public void setAbsent(String speaker) {
        for (Candidate candidate : _database) {
            if (candidate.printCandidate().equals(speaker)) {
                candidate.setAbsent(!candidate.isAbsent());
                break;
            }
        }
    }
    
    // check if a given speaker is absent
    public boolean checkAbsent(String speaker) {
        boolean _default = false;
        for (Candidate candidate : _database) {
            if (candidate.printCandidate().equals(speaker)){ 
                _default = candidate.isAbsent();
                }}
        return _default;
    }
    
    public List<String> printCandidates() {
        List<String>candidatesList = new ArrayList<>();
        for (Candidate candidate : _database) {
            candidatesList.add(candidate.printCandidate());
        }
        return candidatesList;
    }

}
