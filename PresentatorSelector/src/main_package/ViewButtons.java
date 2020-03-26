package main_package;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class ViewButtons {
    private final View _view;
    
    private final JTextField speakerNameBox;
    private final JButton removeButton;
    private final JButton selectButton;
    private final JButton addButton;
    private final JButton logButton;

    public ViewButtons(View view) {
        _view = view;
        
        selectButton = createButton("Select random speaker",new SelectListener());
        removeButton = createButton("Remove speaker",new RemoveListener());
        addButton = createButton("Add speaker",new AddListener());
        logButton = createButton("Print Log",new LogListener());
        
        // Create the extracted speaker box
        speakerNameBox = new JTextField(20);
        speakerNameBox.setEditable(false);
    }
    
    
    public JButton createButton(String text, ActionListener listener) {
        JButton button = new JButton(text);
        button.addActionListener(listener);
        return button;
    }
    
    
    public JPanel createUpperPanel() {
     // Create a panel that uses BoxLayout.
        JPanel upperPane = new JPanel();
        upperPane.setLayout(new BoxLayout(upperPane,
            BoxLayout.LINE_AXIS));
        upperPane.add(removeButton);
        upperPane.add(new JSeparator(SwingConstants.VERTICAL));
        upperPane.add(Box.createHorizontalStrut(5));
        upperPane.add(addButton);
        upperPane.add(logButton);
        upperPane.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        return upperPane;
    }
    
    
    public JPanel createBottomPanel() {
        JPanel bottomPane = new JPanel();
        bottomPane.setLayout(new BoxLayout(bottomPane,
            BoxLayout.LINE_AXIS));
        bottomPane.add(selectButton);
        bottomPane.add(speakerNameBox);
        bottomPane.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        return bottomPane;
    }
    
    
    public void updateButtons() {
        if (_view.getCandidateList().getNumberOfSpeakers() == 0) {  
            selectButton.setEnabled(false);
        }
        if (_view.getCandidateList().getNumberOfSpeakers() > 0 & !selectButton.isEnabled()) {  
            selectButton.setEnabled(true);
        }
        if (_view.getCandidateList().getSelectedIndex() == -1) {
            // No selection, disable remove button.
            removeButton.setEnabled(false);
        } else {
            // Selection, enable the remove button.
            removeButton.setEnabled(true);
        }
    }
    
    
    // Buttons Listeners
    class SelectListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            speakerNameBox.setText(_view.getSelector().select());
        }
    }

    
    class RemoveListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            // get selected index
            String speaker = _view.getCandidateList().getSelectedValue();  
            _view.getSelector().remove(speaker);  
            _view.updateView();
        }
    }

    
    class LogListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println(_view.getSelector().printLog());
        }
    }

    
    class AddListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            new ViewAddFrame(_view, addButton);
            addButton.setEnabled(false);
        }
    }
}