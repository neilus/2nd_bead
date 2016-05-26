package hu.elte.progtech.cwjkl1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


/**
 * A táblajáték ablaka, melyen keresztül lehet vezérelni a játékot
 */
public class GameView extends JFrame implements ActionListener{
    private Main mainWindow;
    private GameModel game;

    public GameView(GameConfig config, Main mainWindow) {
        int tableSize = config.getTableSize();
        // set up some stuff on this new window
        System.out.println("Let's getting started!");
        this.mainWindow = mainWindow;
        setTitle("Áttörés táblajáték - " + tableSize + "x" + tableSize);
        setSize(640, 360);
        mainWindow.getGameWindows().add(this);

        System.out.println("table size is: " + tableSize);
        JPanel gameBoard = new JPanel();

        gameBoard.setLayout(new GridLayout(tableSize, tableSize));
        game = new GameModel(config);


        for(int i = 0; i < tableSize; i++){
            for(int j = 0; j < tableSize; j++) {
                FieldView fieldView = new FieldView();
                fieldView.setName("" + i + ", " + j);
                fieldView.setText("" + i + ", " + j);

                gameBoard.add(fieldView);
                System.out.println("fieldView " + i + ", " + j + " added to the board...");
            }
        }
        System.out.println("the fields should be added to the board");

        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(gameBoard);
    }

    /**
     * Invoked when an action occurs.
     *
     * @param e
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        JToggleButton field = (JToggleButton) e.getSource();
        System.out.println("You pressed field " + field.getText());
    }
}
