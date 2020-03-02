package main_package;

import javax.swing.JComponent;
import javax.swing.JFrame;

public class PresentatorSelector {

    public static void main(String[] args) {
        
        // Create database interface
        DatabaseInterface db = new DatabaseInterface("src/candidates.json");

        // create log
        Log log = new Log("src/log.json");
        
        // load database
        Candidates candidates = new Candidates(db.load_database(), log, db);
        
        // RIMUOVERE DOPO SVILUPPO
        // restore default candidates and clear log
        candidates.restore_default();
        log.clear_log();
   
       // Create View
       final View gui = new View(candidates, log);
     
       // Run the GUI
       javax.swing.SwingUtilities.invokeLater(new Runnable() {
       @Override
       public void run() {
           createAndShowGUI(gui);
           }
       });
    }
    
    // Method to create and run the GUI
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