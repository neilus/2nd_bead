package hu.elte.progtech.cwjkl1.attores;

import hu.elte.progtech.cwjkl1.attores.GameField.*;
/**
 * Az Áttörés táblajáték modellje
 */
public class GameModel implements FieldListener{
    private GameConfig config;
    private int size;

    public GameConfig getConfig() {
        return config;
    }

    public int getSize() {
        return size;
    }

    public GameField[][] getGameFields() {
        return gameFields;
    }

    public Player getWhosTurn() {
        return whosTurn;
    }

    private GameField[][] gameFields;
    private Player whosTurn;

    public GameModel(GameConfig config){
        this.config = config;
        this.size = config.getTableSize();
        gameFields = GameField.createGameFieldMatrix(size);
        for(int i = 0; i < gameFields.length; i++){
            for(int j = 0; j < gameFields[i].length; j++){
                gameFields[i][j].addEventListener(this);
            }
        }
    }

    public void selectFrom(GameField selected){

    }

    @Override
    public void fieldEventRecieved(FieldEvent event) {
        System.out.println("The gameModel recieved an event: " + event.getGameField().toString());
    }
}
