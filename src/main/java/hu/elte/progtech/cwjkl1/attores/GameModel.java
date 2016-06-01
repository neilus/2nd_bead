package hu.elte.progtech.cwjkl1.attores;

import hu.elte.progtech.cwjkl1.attores.GameField.*;
import java.util.EventObject;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Az Áttörés táblajáték modellje
 */
public class GameModel implements FieldListener{
    private GameConfig config;
    private int size;
    private Player whosTurn = Player.WHITE;
    private GameField[][] gameFields;

    private List<GameEventListener> eventListeners = new CopyOnWriteArrayList<>();
    public class GameEvent extends EventObject {

        /**
         * Constructs a prototypical Event.
         *
         * @param source The object on which the Event initially occurred.
         * @throws IllegalArgumentException if source is null.
         */
        public GameEvent(Object source) {
            super(source);
        }
    }
    public interface GameEventListener {
        public void gameEventRecieved(GameEvent event);
        public void puppetMovedAction(GameEvent event, GameField from, GameField to);
    }
    public synchronized void addEventListener(GameEventListener listener) {
        eventListeners.add(listener);
    }
    public synchronized void removeEventListener(GameEventListener listener){
        eventListeners.remove(listener);
    }

    /**
     * the gameObject fires the event that a "puppet" has been moved
     */
    private synchronized void moveApuppet(){
        GameEvent event = new GameEvent(this);
        System.out.println("gonna send the event that a puppet has been moved...");
        for(GameEventListener listener:eventListeners){
            listener.puppetMovedAction(event, innen, ide);
        }
    }

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

    @Override
    public void fieldEventRecieved(FieldEvent event) {
        System.out.println("The gameModel recieved an event: " + event.getGameField().toString());
    }

    private int counter = 0;
    private GameField innen = null, ide = null;

    @Override
    public void fieldSelected(FieldEvent event) {
        //Done Take turns
        try {
            GameField source = (GameField) event.getSource();
            if(source.getWhose() != null) {
                if (counter == 0 && source.getWhose() == whosTurn) {
                    innen = source;
                    counter++;
                } else if (source.getWhose() != whosTurn) {
                    ide = source;
                    if (ide.getWhose() == Player.NOBODY) {
                        //Done ures mezore lepni
                        ide.movePuppetTo(innen);
                        //Done fire event that the puppet has moved
                        moveApuppet();
                    } else {
                        //Todo: levenni az ellenfel babujat
                        ide.movePuppetTo(innen);
                        moveApuppet();
                    }

                    whosTurn = (whosTurn == Player.WHITE)? Player.BLACK : Player.WHITE;
                    counter = 0;
                }
            }
        }catch(Exception e) {
            e.printStackTrace();
            System.out.println("\n" + whosTurn.toString() + "\n" + e.getLocalizedMessage());
        }
        //Todo: move the puppets
    }

    @Override
    public void fieldConqueredBy(Player who) {

    }

    @Override
    public void fieldLiberated() {

    }
}
