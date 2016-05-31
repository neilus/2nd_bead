package hu.elte.progtech.cwjkl1.attores;


import java.util.EventObject;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Created by neilus on 5/24/16.
 */
public class GameField {

    public class FieldEvent extends EventObject {
        private GameField gameField;
        /**
         * Constructs a prototypical Event.
         *
         * @param source The object on which the Event initially occurred.
         * @throws IllegalArgumentException if source is null.
         */
        public FieldEvent(Object source, GameField gameField) {
            super(source);
            this.gameField = gameField;
        }

        public GameField getGameField() {
            return gameField;
        }
    }

    public interface FieldListener {
        public void fieldEventRecieved(FieldEvent event);
    }

    public Player getWhose() {
        return whose;
    }

    public String whose() {
        return (whose == Player.NOBODY)? "" : whose.toString();
    }

    public void setWhose(Player whose) {
        this.whose = whose;
    }

    public Puppet getPuppet() {
        return puppet;
    }

    public void setPuppet(Puppet puppet) {
        this.puppet = puppet;
    }

    private int x;

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    private int y;
    private Player whose;
    private Puppet puppet;
    private List<FieldListener> eventListeners = new CopyOnWriteArrayList<>();

    public synchronized void addEventListener(FieldListener game){
        eventListeners.add(game);
    }

    public synchronized void remoteEventListener(FieldListener game){
        eventListeners.remove(game);
    }

    public synchronized void fireFieldEvent() {
        FieldEvent fieldEvent = new FieldEvent(this, this);
        for(FieldListener listener:eventListeners){
            listener.fieldEventRecieved(fieldEvent);
        }
    }
    public void gotSelected(){
        fireFieldEvent();
    }

    public GameField(){
        whose = Player.NOBODY;
    }
    public GameField(Player whose, int x, int y) {
        this.x = x;
        this.y = y;
        this.whose = whose;
        this.puppet = new Puppet(whose);
    }

    public static GameField[][] createGameFieldMatrix(int size){
        GameField[][] fields = new GameField[size][size];
        for(int i = 0; i < size; i++){
            for(int j = 0; j < size; j++){
                Player player;
                if(i < 2) {
                    player = Player.WHITE;
                } else if (i > size - 3) {
                    player = Player.BLACK;
                } else {
                    player = Player.NOBODY;
                }
                fields[j][i] = new GameField(player, j, i);
            }
        }
        return fields;
    }
    public void conquersWhite(){
        whose = Player.WHITE;
    }
    public void conquersBlack(){
        whose = Player.BLACK;
    }
    public void unConquered(){
        whose = Player.NOBODY;
    }

    public void conquers(Puppet puppet){}

    @Override
    public String toString(){
        return whose() + "(" + x + ", " + y + ")";
    }
}
