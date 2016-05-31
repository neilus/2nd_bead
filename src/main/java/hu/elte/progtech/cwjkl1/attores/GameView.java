package hu.elte.progtech.cwjkl1.attores;

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
    private int fieldSize = 64;

    public GameView(GameConfig config, Main mainWindow) {
        int tableSize = config.getTableSize();
        // set up some stuff on this new window
        System.out.println("Let's getting started!");
        this.mainWindow = mainWindow;
        setTitle("Áttörés táblajáték - " + tableSize + "x" + tableSize);
        fieldSize *= config.getTableSize();
        setSize(fieldSize, fieldSize);
        mainWindow.getGameWindows().add(this);

        System.out.println("table size is: " + tableSize);
        JPanel gameBoard = new JPanel();

        gameBoard.setLayout(new GridLayout(tableSize, tableSize));
        try {
            game = new GameModel(config);
            GameField[][] gameFields = null;
            try {
                gameFields = GameField.createGameFieldMatrix(tableSize);
            }catch(Exception e){
                System.out.println("Fucking gameFields: " + e.getLocalizedMessage());
            }
            FieldView[][] fieldViews;
            try {
                fieldViews = FieldView.createFieldViewMatrix(gameFields);
                System.out.println(fieldViews.toString());
                for (int i = 0; i < tableSize; i++) {
                    for (int j = 0; j < tableSize; j++) {
                        gameBoard.add(fieldViews[j][i]);
                    }
                }
            }catch (Exception e){
                System.out.println("fucking fieldViews: " + e.getLocalizedMessage());
            }
        }catch(Exception e){
            System.out.println(e.getLocalizedMessage());
        }
        System.out.println("the gameFields should be added to the board");

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
