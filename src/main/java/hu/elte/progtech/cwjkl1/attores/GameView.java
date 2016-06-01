package hu.elte.progtech.cwjkl1.attores;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


/**
 * A táblajáték ablaka, melyen keresztül lehet vezérelni a játékot
 */
public class GameView extends JFrame implements ActionListener, GameModel.GameEventListener{
    private Main mainWindow;
    private GameModel game;
    private int fieldSize = 64;
    private FieldView[][] fieldViews;

    public GameView(GameConfig config, Main mainWindow) {
        int tableSize = config.getTableSize();
        // set up some stuff on this new window
        System.out.println("Let's getting started!");
        this.mainWindow = mainWindow;

        fieldSize *= config.getTableSize();
        setSize(fieldSize, fieldSize);
        mainWindow.getGameWindows().add(this);

        System.out.println("table size is: " + tableSize);
        JPanel gameBoard = new JPanel();
        gameBoard.setLayout(new GridLayout(tableSize, tableSize));

        game = new GameModel(config);
        fieldViews = FieldView.createFieldViewMatrix(game.getGameFields());
        addFieldViews(gameBoard, fieldViews);

        System.out.println("the gameFields should be added to the board");
        setTitle(game.getWhosTurn().toString() + " players turn");
        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(gameBoard);
    }

    private void addFieldViews(JPanel panel,  FieldView[][] fields){
        for (int i = 0; i < fields.length; i++) {
            for (int j = 0; j < fields[i].length; j++) {
                panel.add(fields[j][i]);
//                fields[j][i].addActionListener(this);
            }
        }
    }

    /**
     * Invoked when an action occurs.
     *
     * @param e
     */
    @Override
    public void actionPerformed(ActionEvent e) {
//        JToggleButton field = (JToggleButton) e.getSource();
//        System.out.println("You pressed field " + field.getText());
    }

    @Override
    public void gameEventRecieved(GameModel.GameEvent event) {
        System.out.println("some game event recieved");
    }

    @Override
    public void puppetMovedAction(GameModel.GameEvent event, GameField from, GameField to) {
        FieldView egyik, masik;

        for(FieldView[] row:fieldViews){
            for(FieldView field:row){
                if(field.getGameFieldModel().equals(from)){
                    egyik = field;
                } else if(field.getGameFieldModel().equals(to)){
                    masik = field;
                }
            }
        }
//        egyik.setText();
        System.out.println("Move a puppet from " + from + " to " + to);
    }
}
