package hu.elte.progtech.cwjkl1.attores;

import hu.elte.progtech.cwjkl1.attores.GameField.*;

import javax.swing.*;
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
        public Player getWhoWon() {
            return whoWon;
        }

        Player whoWon = Player.NOBODY;
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
        public void gameOverEvent(GameEvent event);
        public void puppetMovedAction(GameEvent event, GameField from, GameField to);
    }
    public synchronized void addEventListener(GameEventListener listener) {
        eventListeners.add(listener);
    }
    public synchronized void removeEventListener(GameEventListener listener){
        eventListeners.remove(listener);
    }

    private synchronized void gameOver(Player winner){
        GameEvent gameOverEvent = new GameEvent(this);
        gameOverEvent.whoWon = winner;
        for(GameEventListener listener:eventListeners){
            listener.gameOverEvent(gameOverEvent);
        }
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

    public boolean elore(GameField ide, GameField innen){
        if(innen.getWhose() == Player.BLACK) {
            //Done: csak eszak fele mehet
            if(innen.getY() - ide.getY() == 1) {
                if (Math.abs(ide.getX() - innen.getX()) == 1) {
                   return true;
                } else if(ide.getX() == innen.getX() && ide.getWhose() == Player.NOBODY){
                    return true;
                }
            }
        } else if(innen.getWhose() == Player.WHITE){
            //Done: csak del fele mehet
            if(ide.getY() - innen.getY() == 1){
                if( Math.abs(ide.getX() - innen.getX()) == 1 ) {
                    return true;
                } else if( ide.getX() == innen.getX() && ide.getWhose() == Player.NOBODY){
                    return true;
                }
            }
        }

        return false;
    }

    @Override
    public void fieldSelected(FieldEvent event) {
        //Done Take turns
        System.out.println(whosTurn.toString() + "'s turn");
        if(whosTurn != Player.NOBODY) {
            try {
                GameField source = (GameField) event.getSource();
                if (source.getWhose() != null) {
                    if (counter == 0 && source.getWhose() == whosTurn) {
                        innen = source;
                        counter++;
                    } else if (counter == 1 && source.getWhose() != whosTurn) {
                        ide = source;
                        if (ide.getWhose() == Player.NOBODY) {
                            //Done ures mezore lepni
                            if (elore(ide, innen)) {
                                ide.movePuppetTo(innen);
                                moveApuppet();
                                counter++;
                            }
                        } else {
                            //Done: levenni az ellenfel babujat
                            if (elore(ide, innen)) {
                                ide.movePuppetTo(innen);
                                moveApuppet();
                                counter++;
                            }
                        }
                        if (counter >= 2) {
                            whosTurn = (whosTurn == Player.WHITE) ? Player.BLACK : Player.WHITE;
                            counter = 0;
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("\n" + whosTurn.toString() + "\n" + e.getLocalizedMessage());
            }
        }
    }

    @Override
    public void fieldConqueredBy(Player who) {

    }

    @Override
    public void fieldLiberated() {

    }

    @Override
    public void puppetMoved(FieldEvent event) {
        GameField source = (GameField) event.getSource();
        if(source.getWhose() == Player.BLACK){
            // Todo: tud-e meg eszak fele lepni?
            if(source.getY() == 0){
                // nem tud magasabbra lepni, tehat nyert
                this.whosTurn = Player.NOBODY;
                System.out.println("Player " + source.whose() + " wins");
                gameOver(source.getWhose());
            }
        } else if (source.getWhose() == Player.WHITE){
            // Todo: tud-e meg del fele lepni?
            if(source.getY() == this.size -1){
                // nem tud alacsonyabbra lepni, tehat nyert
                this.whosTurn = Player.NOBODY;
                System.out.println(("Player " + source.whose() + " wins!"));
                gameOver(source.getWhose());
            }
        }
    }
}
