package Playground;

import Entities.TContestant;
import Entities.TContestantStates;
import MGeneralRepository.MGeneralRepository;

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

    public synchronized void pullTheRope() {



    }

    public synchronized void amDone(){
        //write
    }

    public synchronized void ReviewNotes(){
        //write
    }




}
