package hu.elte.progtech.cwjkl1;

/**
 * Just a board game puppet model
 */
public class Puppet {
    private Turn whose;
    private boolean alive;

    /**
     * Creating a new Puppet object
     * @param whose the "color" of the puppet, which player owns it
     */
    public Puppet(Turn whose){
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
