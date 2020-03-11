package main_package;

import org.json.simple.JSONObject;

public class CandidateParser implements JsonObjectParser<Candidate>{
    
    @Override
    public Candidate readObject(JSONObject obj){
        return new Candidate((String) obj.get("fname"), (String) obj.get("surname"));
    }    
    
    @Override
    public JSONObject writeObject(Candidate  candidate){
        JSONObject obj = new JSONObject();
        obj.put("fname",candidate.getFname());
        obj.put("surname",candidate.getSurname());
        return obj;
    }

}
