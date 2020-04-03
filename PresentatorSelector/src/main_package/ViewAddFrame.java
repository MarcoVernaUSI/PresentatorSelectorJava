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
    private final JFrame _frame;
    private final JButton _innerAddButton;
    private final JButton _thisWindowOpeningButton;
    private final JTextField _nameField;
    private final View _view;
    
    public ViewAddFrame(View view, JButton thisWindowOpeningButton) {
        _view = view;
        _thisWindowOpeningButton = thisWindowOpeningButton;
        _frame = buildFrame();
        _innerAddButton = _view.getButtons().createButton("Add speaker", new innerAddListener());
        _nameField = new JTextField(20);
    }

    
    private JFrame buildFrame() {
        JFrame frame = new JFrame("Add");
        frame.setTitle("Add candidate");
        frame.setBounds(20, 20, 300, 105);
        frame.setResizable(false);
        frame.setVisible(true);
        frame.addWindowListener(new closeListener());
        frame.getContentPane().add(createInnerPanel());
        return frame;
    }
    

    private JPanel createInnerPanel() {
        JPanel innerPane = new JPanel();
        innerPane.setLayout(new BoxLayout(innerPane,
            BoxLayout.Y_AXIS));
        innerPane.add(new JLabel("Name:"));
        innerPane.add(_nameField);
        innerPane.add(_innerAddButton);
        innerPane.add(Box.createHorizontalStrut(5));
        innerPane.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        return innerPane;
    }

    
    class innerAddListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            // get input in boxes
            String name = _nameField.getText();
            if (name.trim().length() > 0) {
                _view.getSelector().add(name);
                _view.getCandidateList().updateList();
            }
            _thisWindowOpeningButton.setEnabled(true);
            _frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
            _frame.dispose();
        }
    }

    
    class closeListener extends WindowAdapter {
        @Override
        public void windowClosing(WindowEvent e) {
            _thisWindowOpeningButton.setEnabled(true);
        }
    }
}