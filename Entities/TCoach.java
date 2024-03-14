package Entities;

import Bench.IMContestantsBench_TCoach;

public class TCoach implements Runnable{

    private final int coachID;
    private final IMContestantsBench_TCoach x;

    /**
     * Coach state.
     */
    private int coachState;

    private TCoach(int coachid, IMContestantsBench_TCoach x){
        this.coachID = coachid;
        this.x = x;
    }

    /**
     * Get coach state.
     *
     * @return coach state
     */
    public int getCoachState() {
        return coachState;
    }

    /**
     *  Set coach state
     *
     * @param coachState new coach state
     */
    public void setCoachState(int coachState) {
        this.coachState = coachState;
    }

    public static Runnable getInstance(int coachID, IMContestantsBench_TCoach x){
        return new TCoach(coachID, x);
    }

    @Override
    public void run(){
        boolean run = true;
        while (run) {
            //complete
        }
        
    }
    
}
