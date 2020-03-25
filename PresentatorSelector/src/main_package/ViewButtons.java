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
    
    private final JTextField speakerName;
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
        speakerName = new JTextField(20);
        speakerName.setEditable(false);
    }
    
    private JButton createButton(String text, ActionListener listener) {
        JButton button = new JButton(text);
        button.addActionListener(listener);
        return button;
    }
    
    
    public JPanel createUpperPanel() {
     // Create a panel that uses BoxLayout.
        JPanel buttonPane = new JPanel();
        buttonPane.setLayout(new BoxLayout(buttonPane,
            BoxLayout.LINE_AXIS));
        buttonPane.add(removeButton);
        buttonPane.add(new JSeparator(SwingConstants.VERTICAL));
        buttonPane.add(Box.createHorizontalStrut(5));
        buttonPane.add(addButton);
        buttonPane.add(logButton);
        buttonPane.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        return buttonPane;
    }
    
    public JPanel createBottomPanel() {
        JPanel buttonPane = new JPanel();
        buttonPane.setLayout(new BoxLayout(buttonPane,
            BoxLayout.LINE_AXIS));
        buttonPane.add(selectButton);
        buttonPane.add(speakerName);
        buttonPane.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        return buttonPane;
    }
    
    public void abilitateAddButton(boolean flag) {
        addButton.setEnabled(flag);    
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
            speakerName.setText(_view.getSelector().select());
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
            new ViewAddFrame(_view);
            addButton.setEnabled(false);
        }
    }
}