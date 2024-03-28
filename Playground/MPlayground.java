package Playground;

import Entities.TCoach;
import Entities.TCoachStates;
import Entities.TContestant;
import Entities.TContestantStates;
import MGeneralRepository.MGeneralRepository;

import static java.lang.Thread.sleep;

public class MPlayground {

    /**
     *  Reference to the general repository of information.
     */
    private final MGeneralRepository repos;

    /**
     * Number of contestants that are ready.
     */
    private int nContestantsReady;

    /**
     * Instantiantion of the playground
     *
     * @param repos Reference to general repository
     */
    public MPlayground(MGeneralRepository repos) {
        this.repos = repos;

        this.nContestantsReady = 0;
    }

    //Funções: getReady, amDone, ReviewNotes

    /**
     * Operation get ready.
     *
     * Called by contestants when they are ready to play.
     */
    public synchronized void getReady(){
        TContestant contestant = (TContestant) Thread.currentThread();
        contestant.setContestantState(TContestantStates.DO_YOUR_BEST);
        this.repos.setContestantState(contestant.getContestantID(), TContestantStates.DO_YOUR_BEST);

        this.nContestantsReady++;

    }

    /**
     * Operation pull the rope.
     *
     * Called by contestants when they are playing.
     */
    public synchronized void pullTheRope() {
        try {
            sleep ((long) (1 + 100 * Math.random ()));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * Operation 'am done'.
     *
     * Called by contestants when they stopped pulling the rope.
     */
    public synchronized void amDone(){

        /*
        while( ) { //TODO condition??? maybe just use a flag

            try{
                wait(); // waits for operation assertTrialDecision
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        */
    }

    /**
     * Operation review notes.
     *
     * Called by coach.
     */
    public synchronized void reviewNotes(){
        TCoach coach = (TCoach) Thread.currentThread();
        coach.setCoachState(TCoachStates.WAIT_FOR_REFEREE_COMMAND);
        this.repos.setCoachState(coach.getCoachID(), TCoachStates.WAIT_FOR_REFEREE_COMMAND);

        /*
        while( ) { //TODO condition??? maybe just use a flag

            try{
                wait(); // waits for operation callTrial
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        */
    }




}
