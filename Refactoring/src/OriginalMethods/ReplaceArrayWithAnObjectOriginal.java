package OriginalMethods;  

public class ReplaceArrayWithAnObjectOriginal {
    String[] row = new String[3];
    
    public String method() {
        row [0] = "Liverpool";
        row [1] = "15";
        String name = row[0];
        int wins = Integer.parseInt(row[1]); 
        
        return name +""+ wins;
    }
    
}
