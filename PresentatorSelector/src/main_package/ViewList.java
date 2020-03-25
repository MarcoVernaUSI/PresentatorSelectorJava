package main_package;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.ListCellRenderer;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;

// List Panel
public class ViewList {
    private final JList<String> candidateList;
    private final DefaultListModel<String> candidateListModel;
    private final Selector _selector;
    
    public ViewList (View view) {
        candidateListModel = new DefaultListModel<>();
        _selector = view.getSelector();
        
        // Create the list and put it in a scroll pane.
        candidateList = new JList<>(candidateListModel);
        candidateList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        candidateList.setSelectedIndex(0);
        candidateList.addListSelectionListener(view);
        candidateList.setVisibleRowCount(10);
        candidateList.setCellRenderer(new MyListCellRenderer());
        
        // add mouse listener for absents
        candidateList.addMouseListener(new AbsentListener());
    }


    public void updateList() {
        candidateListModel.removeAllElements();
        for (String speaker : _selector.getSpeakers()) {
            candidateListModel.addElement(speaker);
        }
    }
    
    public int getNumberOfSpeakers() {
        return candidateListModel.getSize();
    }
    
    public JScrollPane createPanel() {
        return new JScrollPane(candidateList);
    }
    
    public String getSelectedValue() {
        return candidateList.getSelectedValue();
    }
    
    public int getSelectedIndex() {
        return candidateList.getSelectedIndex();
    }
    
    public String getElementAt(int index) {
        return candidateListModel.getElementAt(index);
    }    
    
    class AbsentListener extends MouseAdapter {
        @Override
        public void mousePressed(MouseEvent e) {
            if ( SwingUtilities.isRightMouseButton(e) ) {      
                candidateList.setSelectedIndex(candidateList.locationToIndex(e.getPoint()));

                 JPopupMenu menu = new JPopupMenu();
                 JMenuItem setAbsent = new JMenuItem("toggle absent or not");
                 setAbsent.addActionListener(new ActionListener() {
                     @Override
                    public void actionPerformed(ActionEvent e) {
                         _selector.setAbsent(candidateList.getSelectedValue());
                     }
                 });
                 menu.add(setAbsent);
                 menu.show(candidateList, e.getPoint().x, e.getPoint().y);            
             }
         }
    }
    
    class MyListCellRenderer extends JLabel implements ListCellRenderer {
        public MyListCellRenderer() {
            setOpaque(true);
        }
        @Override
        public Component getListCellRendererComponent(JList paramlist, Object value, int index, boolean isSelected, boolean cellHasFocus) {
            setText(value.toString());
            if (_selector.checkAbsent(value.toString())) {
                setBackground(Color.RED);
            } else
                setBackground(Color.white);
            if (isSelected) {
                setBackground(getBackground().darker());
           }
            return this;
        }
    }
}