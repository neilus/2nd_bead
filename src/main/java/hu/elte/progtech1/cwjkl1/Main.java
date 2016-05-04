package hu.elte.progtech1.cwjkl1;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by neilus on 5/4/16.
 */
public class Main extends JFrame{
    private JButton newGameBtn;
    private JSpinner dimSpinner;
    private JSlider dimSlider;
    private JPanel newRubikTablePanel;

    private int dim;
    public Main() {
        super();
        dim = 8;
        JFrame frame = new JFrame("Rubik Table Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.setContentPane(newRubikTablePanel);

        dimSpinner.setValue(dim);
        dimSlider.setValue(dim);

        frame.pack();
        frame.setVisible(true);
        dimSpinner.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                dimSpinner.validate();
                dim = Integer.parseInt( dimSpinner.getValue().toString());
                if(dim < 2){
                    dim = 2;
                    dimSpinner.setValue(dim);
                }
                if(dim > 32){
                    dim = 32;
                    dimSpinner.setValue(dim);
                }
                dimSlider.setValue(dim);
            }
        });
        dimSlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                dim = dimSlider.getValue();
                dimSpinner.setValue(dim);
            }
        });
        newGameBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Dialog hi = new Dialog(frame);
                hi.setTitle("No new agme yet, sorry :(");
                hi.add(new Label("You wanted to start a Rubik Table game size of " + dim));

                hi.setVisible(true);

            }
        });
    }
    public static void main(String[] args){
        Main main = new Main();
    }
}
