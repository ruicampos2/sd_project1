package Entities;

import Bench.IMContestantsBench_TCoach;

public class TCoach implements Runnable{

    /**
     * ID of the coach
     */
    private final int coachID;

    /**
     * Reference to contestants bench.
     */
    private final IMContestantsBench_TCoach contestantsBench;

    /**
     * Coach state.
     */
    private int coachState;

    /**
     *
     * @param coachId ID of the coach
     * @param contestantsBench Reference to contestants bench
     */
    private TCoach(int coachId, IMContestantsBench_TCoach contestantsBench){
        this.coachID = coachId;
        this.coachState = TCoachStates.WAIT_FOR_REFEREE_COMMAND;
        this.contestantsBench = contestantsBench;
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

    public static Runnable getInstance(int coachID, IMContestantsBench_TCoach contestantsBench){
        return new TCoach(coachID, contestantsBench);
    }

    @Override
    public void run(){
        boolean run = true;
        while (run) {
            //complete
        }
        
    }
    
}
