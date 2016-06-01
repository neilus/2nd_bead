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
        public FieldEvent(Object source) {
            super(source);
            this.gameField = gameField;
        }

        public GameField getGameField() {
            return gameField;
        }
    }

    public interface FieldListener {
        /// fixme: törölhető
        public void fieldEventRecieved(FieldEvent event);
        /// Kiválasztották ezt a mezőt
        public void fieldSelected(FieldEvent event);
        /// elfoglalták ezt a mezőt
        public void fieldConqueredBy(Player who);
        /// felszabadult ez a mező
        public void fieldLiberated();
        /// elmozgattak egy bábut
        public void puppetMoved(FieldEvent e);
    }

    /// melyik játékoshoz tartozik ez a mező
    public Player getWhose() {
        return whose;
    }

    /// mi a játékos neve, akihez ez a mező tartozik
    public String whose() {
        return (whose == Player.NOBODY)? "" : whose.toString();
    }

    /// állítsuk be kihez tartozik ez a mező
    public void setWhose(Player whose) {
        this.whose = whose;
    }

    /**
     * fires the event to tell everyone that this field got free
     */
    private void liberateField(){
        for(FieldListener listener:eventListeners) {
            listener.fieldLiberated();
        }
    }

    /**
     * fires an event to tell everyone that this field got conquered
     * @param who which player conquered the field
     */
    private void conqueredFieldBy(Player who){
        for(FieldListener listener:eventListeners){
            listener.fieldConqueredBy(who);
        }
    }

    /**
     * firing events when the puppet got moved already
     */
    private void movePuppet(){
        FieldEvent event = new FieldEvent(this);
        for(FieldListener listener:eventListeners){
            listener.puppetMoved(event);
        }
    }

    /**
     * Move the "puppet" from this to here
     * @param here
     */
    public void movePuppetFrom(GameField here){
        here.setWhose(this.getWhose());
        here.conqueredFieldBy(this.getWhose());
        this.liberateField();
        this.setWhose(Player.NOBODY);
        here.movePuppet();
    }

    /**
     * Move the "puppet" from here to this
     * @param here
     */
    public void movePuppetTo(GameField here){
        this.setWhose(here.getWhose());
        this.conqueredFieldBy(here.getWhose());
        here.liberateField();
        here.setWhose(Player.NOBODY);
        this.movePuppet();
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
    private List<FieldListener> eventListeners = new CopyOnWriteArrayList<>();

    public synchronized void addEventListener(FieldListener game){
        eventListeners.add(game);
    }

    public synchronized void remoteEventListener(FieldListener game){
        eventListeners.remove(game);
    }

    /**
     * esemeny triggerelese
     */
    public synchronized void selectThisField() {
        FieldEvent event = new FieldEvent(this);
        for(FieldListener listener:eventListeners){
            listener.fieldSelected(event);
        }
    }

    /**
     * esemeny triggerelese
     */
    public synchronized void fireFieldEvent() {
        FieldEvent fieldEvent = new FieldEvent(this);
        for(FieldListener listener:eventListeners){
            listener.fieldEventRecieved(fieldEvent);
        }
    }

    public void gotSelected(){
        fireFieldEvent();
    }

//    public GameField(){
//        whose = Player.NOBODY;
//    }
    public GameField(Player whose, int x, int y) {
        this.x = x;
        this.y = y;
        this.whose = whose;
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

    @Override
    public String toString(){
        return whose() + "(" + x + ", " + y + ")";
    }
}
