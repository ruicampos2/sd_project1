package Entities;

import Bench.IMContestantsBench_TContestants;
import Playground.IMPlayground_TContestants;

public class TContestant extends Thread{

    /**
     * ID of the contestant.
     */
    private final int contestantID;

    /**
     * Strength of the Contestant
     */
    private int contestantStrength;

    /**
     * State of the contestant.
     */
    private int contestantState;

    /**
     * Reference to the contestants bench.
     */
    private final IMContestantsBench_TContestants contestantsBench;

    /**
     * Reference to the playground.
     */
    private final IMPlayground_TContestants playground;


    /**
     * Instantiation of the Contestant thread.
     * @param contestantID ID of the contestant
     * @param contestantsBench Reference to the contestants bench
     * @param playground Reference to the playground
     */
    private TContestant(int contestantID, int contStrength,
                        IMContestantsBench_TContestants contestantsBench, IMPlayground_TContestants playground){
        this.contestantID = contestantID;
        this.contestantStrength = contStrength;
        this.contestantsBench = contestantsBench;
        this.playground = playground;
        this.contestantState = TContestantStates.SEAT_AT_BENCH;
    }

    /**
     * Get the ID of the Contestant.
     *
     * @return contestant ID
     */
    public int getContestantID() {
        return this.contestantID;
    }

    /**
     * Get the state of the Contestant.
     * 
     * @return contestant state
     */
    public int getContestantState() {
        return this.contestantState;
    }

    /**
     * Set the state of the Contestant.
     * 
     * @param contestantState New state of the contestant
     */
    public void setContestantState(int contestantState) {
        this.contestantState = contestantState;
    }

    /**
     * Get the strength of the contestant.
     *
     * @return
     */
    public int getContestantStrength(){
        return contestantStrength;
    }

    /**
     * Set the strength of the contestant.
     *
     * @param strength New strength of the contestant.
     */
    public void setContenstantStrength(int strength) {
        this.contestantStrength = strength;
    }

    /**
     * Increments the strength of the contestant.
     *
     * @return The new strength of the contestant.
     */
    public int incrementContestantStrength() {
        return ++this.contestantStrength;
    }

    /**
     * Decrements the strength of the contestant.
     *
     * @return The new strength of the contestant.
     */
    public int decrementContestantStrength() {
        return --this.contestantStrength;
    }

    @Override
    public void run(){
        boolean run = true;
        while (run) {

            this.contestantsBench.followCoachAdvice();
            this.playground.getReady();

            this.playground.pullTheRope(); //random sleep
            this.playground.amDone(); //blocks here??

            this.contestantsBench.sitDown();

        }
        
    }
    
}    
