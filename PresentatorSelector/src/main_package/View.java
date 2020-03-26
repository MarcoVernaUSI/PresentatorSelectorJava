package main_package;

import java.awt.BorderLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class View extends JPanel implements ListSelectionListener {
    private final Selector _selector;
    private final ViewList _candidateList;
    private final ViewButtons _buttons;

    public View(Selector selector) {
        super(new BorderLayout());
                                                     
        _selector = selector;
        _buttons = new ViewButtons(this);
        _candidateList = new ViewList(this);
        
        buildLayout();
        updateView();
    }

    
    private void buildLayout() {
        JLabel description = new JLabel("right click for toggle/untoggle absent status");
        add(description,BorderLayout.AFTER_LINE_ENDS);
        add(_candidateList.createPanel(), BorderLayout.PAGE_START);
        add(_buttons.createUpperPanel(), BorderLayout.CENTER);
        add(_buttons.createBottomPanel(), BorderLayout.PAGE_END);
    }

    
    // This method is required by ListSelectionListener.
    @Override
    public void valueChanged(ListSelectionEvent e) {
        if (e.getValueIsAdjusting() == false) {
            _buttons.updateButtons();
        }
    }
    
    
    public void updateView() {
        _candidateList.updateList();
        _buttons.updateButtons();
    }
    
    
    public ViewList getCandidateList() {
        return _candidateList;
    }
    
    
    public Selector getSelector() {
        return _selector;
    }
    
    
    public ViewButtons getButtons() {
        return _buttons;
    }
}
    
    
