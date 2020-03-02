package main_package;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class View extends JPanel
    implements ListSelectionListener {
    // List of candidates
    private final Candidates candidates;
    private final Log log;
    private final JList<String> candidate_list;
    private final DefaultListModel<String> candidate_listModel;
    private final JTextField speakerName;
    private final JButton removeButton;
    private final JButton selectButton;
    private final JButton addButton;
    private final JButton logButton;

    public View(Candidates candidates, Log log) {
        super(new BorderLayout());

        this.log = log;
        this.candidates = candidates;
        this.candidate_listModel = new DefaultListModel<>();
        updateList();

        // Create the list and put it in a scroll pane.
        this.candidate_list = new JList<>(candidate_listModel);
        candidate_list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        candidate_list.setSelectedIndex(0);
        candidate_list.addListSelectionListener(this);
        candidate_list.setVisibleRowCount(10);
        JScrollPane listScrollPane = new JScrollPane(candidate_list);

        // Create the select speaker button
        this.selectButton = new JButton("Select random speaker");
        selectButton.addActionListener(new SelectListener());

        // Create the remove speaker button
        this.removeButton = new JButton("Remove speaker");
        removeButton.addActionListener(new RemoveListener());

        // Create the add speaker button
        this.addButton = new JButton("Add speaker");
        addButton.addActionListener(new AddListener());

        // Print log button
        this.logButton = new JButton("print Log");
        logButton.addActionListener(new logListener());

        // Create the add speaker button

        // Create the extracted speaker box
        this.speakerName = new JTextField(20);
        this.speakerName.setEditable(false);

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

        JPanel buttonPane2 = new JPanel();
        buttonPane2.setLayout(new BoxLayout(buttonPane2,
            BoxLayout.LINE_AXIS));

        buttonPane2.add(selectButton);
        buttonPane2.add(speakerName);
        buttonPane2.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        add(listScrollPane, BorderLayout.PAGE_START);
        add(buttonPane, BorderLayout.CENTER);
        add(buttonPane2, BorderLayout.PAGE_END);

    }

    // Consistency between view and model
    public void updateList() {
        candidate_listModel.removeAllElements();
        for (Candidate candidate : this.candidates.getCandidates()) {
            candidate_listModel.addElement(candidate.printCandidate());
        }
        if (candidate_listModel.getSize() == 0) { // Nobody's left, disable
                                                  // firing.
            selectButton.setEnabled(false);

        }

    }

    // This method is required by ListSelectionListener.
    @Override
    public void valueChanged(ListSelectionEvent e) {
        if (e.getValueIsAdjusting() == false) {
            if (candidate_list.getSelectedIndex() == -1) {
                // No selection, disable remove button.
                removeButton.setEnabled(false);

            } else {
                // Selection, enable the remove button.
                removeButton.setEnabled(true);
            }
        }
    }

    // Listeners
    class SelectListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            speakerName.setText(candidates.getRandomSpeaker().printCandidate());
        }
    }

    class RemoveListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            // get selected index
            int index = candidate_list.getSelectedIndex();
            candidates.removeSpeakers(new Integer[] { index });
            updateList();
        }
    }

    class logListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println(log.print_log());
            ;
        }
    }

    class AddListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            new AddFrame();
            addButton.setEnabled(false);
        }
    }

    // Add Frame
    class AddFrame {
        private final JFrame f = new JFrame("Add");
        private final JButton innerAddButton;
        private final JTextField fnameField;
        private final JTextField surnameField;

        class innerAddListener implements ActionListener {
            @Override
            public void actionPerformed(ActionEvent e) {
                // get input in boxes
                String fname = fnameField.getText();
                String surname = surnameField.getText();
                if ((fname.trim().length() > 0) & (surname.trim().length() > 0)) {
                    candidates.addSpeaker(fname, surname);
                    updateList();
                }
                addButton.setEnabled(true);
                f.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
                f.dispose();
            }
        }

        class closeListener extends WindowAdapter {
            @Override
            public void windowClosing(WindowEvent e) {
                addButton.setEnabled(true);
            }
        }

        public AddFrame() {
            // f.setUndecorated(true);
            // f.getRootPane().setWindowDecorationStyle(JRootPane.NONE);
            // f.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
            f.setTitle("Add candidate");
            f.setBounds(20, 20, 300, 150);
            f.setResizable(false);
            f.setVisible(true);
            f.addWindowListener(new closeListener());

            // Create the add speaker button
            this.innerAddButton = new JButton("Add speaker");
            innerAddButton.addActionListener(new innerAddListener());

            this.fnameField = new JTextField(20);
            this.surnameField = new JTextField(20);

            // Create a panel that uses BoxLayout.
            JPanel innerPane = new JPanel();
            innerPane.setLayout(new BoxLayout(innerPane,
                BoxLayout.Y_AXIS));

            innerPane.add(new JLabel("Name:"));
            innerPane.add(this.fnameField);
            innerPane.add(new JLabel("Surname:"));
            innerPane.add(this.surnameField);
            innerPane.add(this.innerAddButton);
            innerPane.add(Box.createHorizontalStrut(5));
            innerPane.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

            f.getContentPane().add(innerPane);
        }

    }

}