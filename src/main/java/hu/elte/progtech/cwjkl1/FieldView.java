package hu.elte.progtech.cwjkl1;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by neilus on 5/24/16.
 */
public class FieldView extends JToggleButton implements ActionListener{

    public FieldView(){
        addActionListener(this);
    }
    /**
     * Invoked when an action occurs.
     *
     * @param e
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        FieldView fieldView = (FieldView) e.getSource();
        System.out.println("You pressed fieldView " + fieldView.getText());
    }
}
