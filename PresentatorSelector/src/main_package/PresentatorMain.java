package main_package;

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
}