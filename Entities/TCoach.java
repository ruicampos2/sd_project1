package Entities;

import Bench.IMContestantsBench_TCoach;
import Playground.IMPlayground_TCoach;
import RefereeSite.IMRefereeSite_TCoach;

public class TCoach extends Thread{

    /**
     * ID of the coach
     */
    private final int coachID;

    /**
     * Reference to contestants bench.
     */
    private final IMContestantsBench_TCoach contestantsBench;

    /**
     * Reference to Playground
     */
    private final IMPlayground_TCoach playground;

    /**
     * Reference to RefereeSite
     */
    private final IMRefereeSite_TCoach refereesite;

    /**
     * Coach state.
     */
    private int coachState;

    /**
     *
     * @param coachId ID of the coach
     * @param contestantsBench Reference to contestants bench
     */
    private TCoach(int coachId, IMContestantsBench_TCoach contestantsBench, IMPlayground_TCoach playground, IMRefereeSite_TCoach refereesite){
        this.coachID = coachId;
        this.coachState = TCoachStates.WAIT_FOR_REFEREE_COMMAND;
        this.contestantsBench = contestantsBench;
        this.playground = playground;
        this.refereesite = refereesite;
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



    @Override
    public void run(){
        boolean run = true;
        while (run) {
            try {
                sleep ((long) (1 + 100 * Math.random ()));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            this.contestantsBench.callContestants();

            try {
                sleep ((long) (1 + 100 * Math.random ()));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            this.refereesite.informReferee();

            try {
                sleep ((long) (1 + 100 * Math.random ()));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            this.playground.ReviewNotes();
        }
        
    }
    
}
