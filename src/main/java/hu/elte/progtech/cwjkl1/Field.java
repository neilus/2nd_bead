package hu.elte.progtech.cwjkl1;

/**
 * Created by neilus on 5/24/16.
 */
public class Field {
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

    public Field(){
        whose = Player.NOBODY;
    }
    public Field(Player whose, int x, int y) {
        this.x = x;
        this.y = y;
        this.whose = whose;
        this.puppet = new Puppet(whose);
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

    public void conquers(Puppet puppet){
    }
}
