package hu.elte.progtech.cwjkl1.attores;

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


    /**
     * beállítja a tábla kívánt méretét
     * @param tableSize
     */
    public void setTableSize(Integer tableSize) {
        this.tableSize = tableSize;
    }

    /**
     * lekérdezi a tábla méretét
     * @return
     */
    public Integer getTableSize() {
        return tableSize;
    }

    /**
     * megmondja a megengedett konfigurációból melyik van épp kiválasztva
     * @return
     */
    public int getTableSizeIndex() {
        return validDimensions.indexOf(tableSize);
    }

    /**
     * be van-e már konfigurálva
     * @return true ha beconfigurálták már, false ha nem
     */
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
