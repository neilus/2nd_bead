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
    private int fieldSize = 256;

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
        game = new GameModel(config);


        for(int i = 0; i < tableSize; i++){
            for(int j = 0; j < tableSize; j++) {
                Player player;
                if(i < 2) {
                    player = Player.WHITE;
                } else if (i > tableSize - 3) {
                    player = Player.BLACK;
                } else {
                    player = Player.NOBODY;
                }
                FieldView fieldView = new FieldView(new Field(player, j, i));
                gameBoard.add(fieldView);
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
