package Bench;

import Entities.TCoach;
import Entities.TCoachStates;
import Entities.TContestant;
import Entities.TContestantStates;
import MGeneralRepository.MGeneralRepository;

public class MContestantsBench {
    /**
     * Reference to the general repository of information.
     */
    private final MGeneralRepository repos;

    /**
     * Array that saves which contestants will participate in the trial.
     * Indexed by contestant ID. True means they will play, False means they won't.
     */
    private boolean contestantChosenForTrial[];

    /**
     * Instantiantion of the contestants bench.
     *
     * @param repos Reference to general repository
     */
    public MContestantsBench(MGeneralRepository repos, int n_teams, int n_players) {
        this.repos = repos;
        this.contestantChosenForTrial = new boolean[n_teams * n_players];
    }

    /**
     * Operation Call contestants.
     *
     * Called by Coach.
     */
    public synchronized void callContestants(){
        TCoach coach = (TCoach) Thread.currentThread();
        coach.setCoachState(TCoachStates.ASSEMBLE_TEAM);
        this.repos.setCoachState(coach.getCoachID(), TCoachStates.ASSEMBLE_TEAM);

        //TODO: algorithm that choses which contestants will play.

        /* notifies his team that are in "SEAT_AT_THE_BENCH" state*/
        notifyAll();
    }

    /**
     * Operation Seat Down.
     *
     * Called by contestants.
     */
    public synchronized void seatDown(){
        TContestant contestant = (TContestant) Thread.currentThread();
        contestant.setContestantState(TContestantStates.SEAT_AT_BENCH);
        this.repos.setContestantState(contestant.getContestantId(), TContestantStates.SEAT_AT_BENCH);

        while (this.contestantChosenForTrial[contestant.getContestantId()]) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }

    }

    /**
     * Operation Follow Coach Advice.
     *
     * Called by contestants.
     */
    public synchronized void followCoachAdvice(){
        TContestant contestant = (TContestant) Thread.currentThread();
        contestant.setContestantState(TContestantStates.STAND_IN_POSITION);
        this.repos.setContestantState(contestant.getContestantId(), TContestantStates.STAND_IN_POSITION);
    }

}