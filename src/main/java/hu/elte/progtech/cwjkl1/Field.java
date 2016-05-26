package hu.elte.progtech.cwjkl1;

/**
 * Created by neilus on 5/24/16.
 */
public class Field {
    Turn whose;

    public Field(){
        whose = Turn.NOBODY;
    }

    public void conquersWhite(){
        whose = Turn.WHITE;
    }
    public void conquersBlack(){
        whose = Turn.BLACK;
    }
    public void unConquered(){
        whose = Turn.NOBODY;
    }

    public void conquers(Puppet puppet){

    }
}
