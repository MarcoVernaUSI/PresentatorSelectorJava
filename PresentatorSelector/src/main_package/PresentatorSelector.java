package main_package;

import javax.swing.JComponent;
import javax.swing.JFrame;

public class PresentatorSelector {

    public static void main(String[] args) {
        
        // Create database interface
        Database_interface db = new Database_interface("src/candidates.json");

        // create log
        Log log = new Log("src/log.json");
        
        // load database
        Candidates candidates = new Candidates(db.load_database(), log, db);
        
        
        ////////////////////// DEBUGGONE
        // restore default candidates aand clear log
        candidates.restore_default();
        log.clear_log();
        
        
       // debug part vediamo se carica bene il database
       //debug_print(candidates);
       //System.out.println(candidates.getRandomSpeaker().printCandidate());
       
       // aggiungiamo speaker
     //  candidates.addSpeaker("Frank Delano", "Roosvelt");
      // System.out.println("\n Aggiunto roosvelt \n");
      // debug_print(candidates);
      // System.out.println(log.print_log());
       
        // rimuoviamone 2
    //   candidates.removeSpeakers(new Integer[] {2,3});
    //   System.out.println("\n rimosso il terzo e quarto \n");
    //   debug_print(candidates);
    //   System.out.println(log.print_log());
        
       
       // Create View
       final View gui = new View(candidates);
     
       javax.swing.SwingUtilities.invokeLater(new Runnable() {
       @Override
       public void run() {
           createAndShowGUI(gui);
           }
       });
    }
    
    public static void debug_print(Candidates candidates) {
        for (Candidate candidate : candidates.getCandidates()) {
            System.out.println(candidate.printCandidate());
        }   
        
        
    }
    
    public static void createAndShowGUI(View gui) {
        //Create and set up the window.
        JFrame frame = new JFrame("Presentator Selector");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
 
        //Create and set up the content pane.
        JComponent newContentPane = gui;
        newContentPane.setOpaque(true); //content panes must be opaque
        frame.setContentPane(newContentPane);
 
        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }
    

}