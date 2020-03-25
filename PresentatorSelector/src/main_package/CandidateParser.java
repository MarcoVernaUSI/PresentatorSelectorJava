package main_package;

import org.json.simple.JSONObject;

public class CandidateParser implements JsonObjectParser<Candidate>{
    
    @Override
    public Candidate readObject(JSONObject obj){
        return new Candidate((String) obj.get("name"));
    }    
    
    @Override
    public JSONObject writeObject(Candidate  candidate){
        JSONObject obj = new JSONObject();
        obj.put("fname",candidate.printCandidate());
        return obj;
    }

}
