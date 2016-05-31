package hu.elte.progtech.cwjkl1.attores;

/**
 * Just a board game puppet model
 */
public class Puppet {
    private Player whose;
    private boolean alive;

    /**
     * Creating a new Puppet object
     * @param whose the "color" of the puppet, which player owns it
     */
    public Puppet(Player whose){
        this.whose = whose;
        this.alive = true;
    }

    /**
     * Kill this puppet
     */
    void die(){
        this.alive = false;
    }
}
