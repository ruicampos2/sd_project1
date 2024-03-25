package Bench;

import Entities.TContestant;
import Entities.TContestantStates;
import MGeneralRepository.MGeneralRepository;

public class MContestantsBench {
    //colocar aqui
    //Funções: callContestants, followCoachAdvice, seatDown

    /**
     *  Reference to the general repository of information.
     */
    private final MGeneralRepository repos;


    /**
     * Instantiantion of the contestants bench.
     *
     * @param repos Reference to general repository
     */
    public MContestantsBench(MGeneralRepository repos) {
        this.repos = repos;


    }


    /**
     * Operation Follow Coach Advice.
     *
     *
     */
    public synchronized void followCoachAdvice() {
        TContestant contestant = (TContestant) Thread.currentThread();
        contestant.setContestantState(TContestantStates.STAND_IN_POSITION);
        this.repos.setContestantState(contestant.getContestantId(), TContestantStates.STAND_IN_POSITION);
    }



}