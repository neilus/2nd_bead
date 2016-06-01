package hu.elte.progtech.cwjkl1.attores;

import hu.elte.progtech.cwjkl1.attores.GameField.FieldListener;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by neilus on 5/24/16.
 */
public class FieldView extends JToggleButton implements ActionListener, FieldListener{
    public GameField getGameFieldModel() {
        return gameFieldModel;
    }

    private GameField gameFieldModel;

    private FieldView(GameField gameFieldModel){
        this.gameFieldModel = gameFieldModel;
        this.setName("" + gameFieldModel.getX() + ", " + gameFieldModel.getY());
        this.setText(gameFieldModel.whose());
        gameFieldModel.addEventListener(this);
        addActionListener(this);
    }

    public String toString(){
        return this.gameFieldModel.toString();
    }
    public static FieldView createFieldView(GameField gameFieldModel) {
        return new FieldView(gameFieldModel);
    }

    public static FieldView[][] createFieldViewMatrix(GameField[][] gameFields){
        FieldView[][] views;
        views = new FieldView[gameFields.length][gameFields.length];

        for (int i = 0; i < views.length; i++) {
            for (int j = 0; j < views[i].length; j++) {
                views[i][j] = new FieldView(gameFields[i][j]);
            }
        }

        return views;
    }

    public void setGameFieldModel(GameField field){
        this.gameFieldModel = field;
    }

    public String whose() {
        return this.gameFieldModel.whose();
    }
    /**
     * Invoked when an action occurs.
     *
     * @param e
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        FieldView fieldView = (FieldView) e.getSource();
        fieldView.getGameFieldModel().selectThisField();
//        System.out.println("You pressed fieldView " + fieldView.getName() + " - " + fieldView.whose() );

    }

    @Override
    public void fieldEventRecieved(GameField.FieldEvent event) {

    }

    @Override
    public void fieldSelected(GameField.FieldEvent event) {

    }

    @Override
    public void fieldConqueredBy(Player who) {
        setText(who.toString());
        this.setSelected(false);
    }

    @Override
    public void fieldLiberated() {
        setText("");
        this.setSelected(false);
    }
}
