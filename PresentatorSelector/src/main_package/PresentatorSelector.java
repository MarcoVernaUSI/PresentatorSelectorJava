package main_package;

public class PresentatorSelector {

    public static void main(String[] args) {
        
        // Create database interface
        Database_interface db = new Database_interface("src/candidates.json");

        // create log
        Log log = new Log("src/log.json");
        
        // load database
        Candidates candidates = new Candidates(db.load_database(), log, db);
        
        // Create GUI
        View gui=new View();
        
        gui.createAndShowGUI();
        
        
        ////////////////////// DEBUGGONE
        // restore default candidates aand clear log
        candidates.restore_default();
        log.clear_log();
        
        
       // debug part vediamo se carica bene il database
       debug_print(candidates);
       
       // aggiungiamo speaker
       candidates.addSpeaker("Frank Delano", "Roosvelt");
       System.out.println("\n Aggiunto roosvelt \n");
       debug_print(candidates);
       System.out.println(log.print_log());
       
        // rimuoviamone 2
       candidates.removeSpeakers(new Integer[] {2,3});
       System.out.println("\n rimosso il terzo e quarto \n");
       debug_print(candidates);
       System.out.println(log.print_log());
        
       
       }
    
    public static void debug_print(Candidates candidates) {
        for (Candidate candidate : candidates.getCandidates()) {
            System.out.println(candidate.printCandidate());
        }   
    }

}