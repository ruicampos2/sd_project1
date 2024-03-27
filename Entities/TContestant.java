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
    private final int contStrength;

    /**
     * Reference to the contestants bench.
     */
    private final IMContestantsBench_TContestants contestantsBench;

    /**
     * Reference to the playground.
     */
    private final IMPlayground_TContestants playground;

    /**
     * State of the contestant.
     */
    private int contestantState;

    /**
     * Instantiation of the Contestant thread.
     * @param contestantID ID of the contestant
     * @param contestantsBench Reference to the contestants bench
     * @param playground Reference to the playground
     */
    private TContestant(int contestantID, int contStrength,
                        IMContestantsBench_TContestants contestantsBench, IMPlayground_TContestants playground){
        this.contestantID = contestantID;
        this.contStrength = contStrength;
        this.contestantsBench = contestantsBench;
        this.playground = playground;
        this.contestantState = TContestantStates.SEAT_AT_BENCH;
    }

    /**
     * Get the ID of the Contestant.
     *
     * @return contestant ID
     */
    public int getContestantId() {
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

    public int getContestantStrength()
    {
        return contStrength;
    }

    @Override
    public void run(){
        boolean run = true;
        while (run) {
            //complete
            this.contestantsBench.followCoachAdvice();
            this.playground.getReady();
            try {
                sleep ((long) (1 + 100 * Math.random ()));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            this.playground.pullTheRope();
            this.playground.amDone();
            this.contestantsBench.sitDown();

        }
        
    }
    
}    
