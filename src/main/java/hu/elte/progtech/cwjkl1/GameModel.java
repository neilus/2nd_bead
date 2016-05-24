package hu.elte.progtech.cwjkl1;


/**
 * Az Áttörés táblajáték modellje
 */
public class GameModel {
    private GameConfig config;
    private int size;
    private Field[][] fields;

    public GameModel(GameConfig config){
        this.config = config;
        this.size = config.getTableSize();
        fields = new Field[size][size];
    }
}
