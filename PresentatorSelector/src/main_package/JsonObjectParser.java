package main_package;

import org.json.simple.JSONObject;

public interface JsonObjectParser<T>  {  
    abstract public T readObject(JSONObject obj);
    abstract public JSONObject writeObject(T  obj);
}


