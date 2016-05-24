package hu.elte.progtech.cwjkl1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static com.sun.xml.internal.fastinfoset.alphabet.BuiltInRestrictedAlphabets.table;

/**
 * A táblajáték ablaka, melyen keresztül lehet vezérelni a játékot
 */
public class GameView extends JFrame implements ActionListener{
    private Main mainWindow;

    public GameView(GameConfig config, Main mainWindow) {
        System.out.println("Let's getting started!");
        this.mainWindow = mainWindow;
        setTitle("Áttörés táblajáték");
        setSize(640, 360);
        mainWindow.getGameWindows().add(this);

        int tableSize = config.getTableSize();
        System.out.println("table size is: " + tableSize);
        JPanel gameBoard = new JPanel();

        gameBoard.setLayout(new GridLayout(tableSize, tableSize));

        for(int i = 0; i < tableSize; i++){
            for(int j = 0; j < tableSize; j++) {
                JButton field = new JButton();
                field.setName("" + i + ", " + j);
                field.setText("" + i + ", " + j);
                field.addActionListener(this);
                gameBoard.add(field);
                System.out.println("field " + i + ", " + j + " added to the board...");
            }
        }
        System.out.println("the fields should be added to the board");

        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(gameBoard);
        System.out.println("where the fuck is the window?");
    }

    /**
     * Invoked when an action occurs.
     *
     * @param e
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        JButton field = (JButton)e.getSource();
        System.out.println("You pressed field " + field.getText());
    }
}
