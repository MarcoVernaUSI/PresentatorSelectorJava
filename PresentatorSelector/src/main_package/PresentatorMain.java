package main_package;

import java.util.List;

import javax.swing.JComponent;
import javax.swing.JFrame;

public class PresentatorMain {

    public static void main(String[] args) {
        
        Selector selector = new Selector("src/candidates.json", "src/log.json");
        
       // Create View
       final View gui = new View(selector);
     
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
    
    public static class Selector{
        private final Candidates _candidates;        
        private final Log _log;
        private final JsonDatabase _candidates_db;
        private final JsonDatabase _log_db;
        
        public Selector(String candidatesPath, String logPath) {
            super();
            _candidates_db = new JsonDatabase(candidatesPath);
            _log_db = new JsonDatabase(logPath);
            
            _candidates = new Candidates(_candidates_db);
            _log = new Log(_log_db);
        }
        
        public String select() {
            return _candidates.getRandomSpeaker().printCandidate();
        }
        
        public void remove(String speaker){
            _candidates.removeSpeakers(speaker);  
        }
        
        public void add(String fname, String surname){
            _candidates.addSpeaker(fname, surname);
        }
        
        public List<String> getSpeakers() {
            return _candidates.printCandidates();
        }

        public String printLog() {
            return _log.printLog();
        }
        
        public void setAbsent(String speaker) {
            _candidates.setAbsent(speaker);
            _log.saveEntry(speaker,_candidates.checkAbsent(speaker));
        }

        public boolean checkAbsent(String speaker) {
            return _candidates.checkAbsent(speaker);
        }

        
        
    }
}