package hu.elte.progtech.cwjkl1;

import java.util.ArrayList;
import java.util.List;

/**
 * Játék konfigurációs osztály
 * ---------------------------
 *
 * Ezen keresztül lehet a játéknak érvényes konfigurációt beállítani.
 *
 */
public class GameConfig {

    private List<Integer> validDimensions;
    private Integer tableSize = null;

    public GameConfig(){
        validDimensions = new ArrayList<Integer>();
        validDimensions.add(6);
        validDimensions.add(8);
        validDimensions.add(10);

        tableSize = validDimensions.get(0); // the 1st valid dimension will be the default size
    }


    public void setTableSize(Integer tableSize) {
        this.tableSize = tableSize;
    }
    public Integer getTableSize() {
        return tableSize;
    }
    public int getTableSizeIndex() {
        return validDimensions.indexOf(tableSize);
    }
    public boolean isConfigured(){
        return tableSize != null;
    }

    /**
     *
     * @return Támogatott táblaméretek
     */
    public List<Integer> getValidDimensions() {
        return validDimensions;
    }
}
