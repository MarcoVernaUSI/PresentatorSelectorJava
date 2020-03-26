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
    private final JList<String> _candidateList;
    private final DefaultListModel<String> _candidateListModel;
    private final Selector _selector;
    
    public ViewList (View view) {
        _candidateListModel = new DefaultListModel<>();
        _selector = view.getSelector();
        
        _candidateList = createScrollPaneList(_candidateListModel, new MyListCellRenderer());
        
        _candidateList.addListSelectionListener(view);
        _candidateList.addMouseListener(new AbsentListener());
    }


    private JList<String> createScrollPaneList(DefaultListModel<String> listModel, ListCellRenderer<String> cellRenderer) {
        JList<String> list = new JList<>(listModel);
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.setSelectedIndex(0);
        list.setVisibleRowCount(10);
        list.setCellRenderer(cellRenderer);
        return list;
    }


    public void updateList() {
        _candidateListModel.removeAllElements();
        for (String speaker : _selector.getSpeakers()) {
            _candidateListModel.addElement(speaker);
        }
    }
    
    public int getNumberOfSpeakers() {
        return _candidateListModel.getSize();
    }
    
    public JScrollPane createPanel() {
        return new JScrollPane(_candidateList);
    }
    
    public String getSelectedValue() {
        return _candidateList.getSelectedValue();
    }
    
    public int getSelectedIndex() {
        return _candidateList.getSelectedIndex();
    }
    
    public String getElementAt(int index) {
        return _candidateListModel.getElementAt(index);
    }    
    
    class AbsentListener extends MouseAdapter {
        @Override
        public void mousePressed(MouseEvent e) {
            if ( SwingUtilities.isRightMouseButton(e) ) {      
                _candidateList.setSelectedIndex(_candidateList.locationToIndex(e.getPoint()));

                 JPopupMenu menu = new JPopupMenu();
                 JMenuItem setAbsent = new JMenuItem("toggle absent or not");
                 setAbsent.addActionListener(new ActionListener() {
                     @Override
                    public void actionPerformed(ActionEvent e) {
                         _selector.setAbsent(_candidateList.getSelectedValue());
                     }
                 });
                 menu.add(setAbsent);
                 menu.show(_candidateList, e.getPoint().x, e.getPoint().y);            
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