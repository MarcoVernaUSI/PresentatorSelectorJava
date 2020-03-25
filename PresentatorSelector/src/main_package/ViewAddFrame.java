package main_package;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

// Add Frame
public class ViewAddFrame {
    private final JFrame _frame = new JFrame("Add");
    private final JButton innerAddButton;
    private final JTextField nameField;
    private final ViewList _candidatesList;
    private final Selector _selector;
    private final ViewButtons _buttons;
    
    public ViewAddFrame(View view) {
        _frame.setTitle("Add candidate");
        _frame.setBounds(20, 20, 300, 105);
        _frame.setResizable(false);
        _frame.setVisible(true);
        _frame.addWindowListener(new closeListener());
        _candidatesList = view.getCandidateList();
        _selector = view.getSelector();
        _buttons = view.getButtons();

        // Create the add speaker button
        this.innerAddButton = new JButton("Add speaker");
        innerAddButton.addActionListener(new innerAddListener());

        this.nameField = new JTextField(20);
        
        _frame.getContentPane().add(createInnerPanel());
    }
    
 // Create a panel that uses BoxLayout.
    private JPanel createInnerPanel() {
        JPanel innerPane = new JPanel();
        innerPane.setLayout(new BoxLayout(innerPane,
            BoxLayout.Y_AXIS));
        innerPane.add(new JLabel("Name:"));
        innerPane.add(this.nameField);
        innerPane.add(this.innerAddButton);
        innerPane.add(Box.createHorizontalStrut(5));
        innerPane.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        return innerPane;
    }

    class innerAddListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            // get input in boxes
            String name = nameField.getText();
            if (name.trim().length() > 0) {
                _selector.add(name);
                _candidatesList.updateList();
            }
            _buttons.abilitateAddButton(true);
            _frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
            _frame.dispose();
        }
    }

    class closeListener extends WindowAdapter {
        @Override
        public void windowClosing(WindowEvent e) {
            _buttons.abilitateAddButton(true);
        }
    }
}