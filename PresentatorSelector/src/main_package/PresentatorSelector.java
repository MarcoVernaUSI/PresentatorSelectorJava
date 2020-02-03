package main_package;

import java.util.ArrayList;

public class PresentatorSelector {

    public static void main(String[] args) {
        
        // Create database interface
        Database_interface db = new Database_interface("src/candidates.json");
        
        
        
        ArrayList<Candidate>  candidates = db.load_database();
        System.out.println(candidates);
    }

}