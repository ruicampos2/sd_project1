package Bench;

import Entities.TCoach;
import Entities.TCoachStates;
import Entities.TContestant;
import Entities.TContestantStates;
import MGeneralRepository.MGeneralRepository;
import Main.SimulPar;

public class MContestantsBench {
    /**
     * Reference to the general repository of information.
     */
    private final MGeneralRepository repos;

    /**
     * Array that saves which contestants will participate in the trial.
     * Indexed by contestant ID. True means they will play, False means they won't.
     */
    private boolean [] contestantChosenForTrial;

    /**
     * Array that saves each contestants strength.
     * Indexed by contestant ID.
     */
    private int [] contestantStrength;

    /**
     * Number of players in position per team.
     */
    private int [] nPlayersInPosition;

    /**
     * Instantiation of the contestants bench.
     *
     * @param repos Reference to general repository
     * @param initialStrength Each player's initial strength value. Has to be higher than 0.
     */
    public MContestantsBench(MGeneralRepository repos, int initialStrength) {
        this.repos = repos;
        this.contestantChosenForTrial = new boolean[SimulPar.NUMBER_OF_TEAMS * SimulPar.NUMBER_OF_PLAYERS];

        this.contestantStrength = new int[SimulPar.NUMBER_OF_TEAMS * SimulPar.NUMBER_OF_PLAYERS];
        if (initialStrength <= 0) throw new IllegalArgumentException("Parameter initialStrength has to be higher than 0.");
        for (int i = 0; i < contestantStrength.length; i++)
            contestantStrength[i] = initialStrength;

        this.nPlayersInPosition = new int[SimulPar.NUMBER_OF_TEAMS];
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

        /* Algorithm that finds strongest contestants */
        // TODO: might need more testing and better code...
        int i = coach.getCoachID() * SimulPar.NUMBER_OF_PLAYERS;
        int min = this.contestantStrength[i];
        int minIdx = i;
        for (; i < (coach.getCoachID() + 1 ) * SimulPar.NUMBER_OF_PLAYERS; i++) {
            if (i < SimulPar.NUMBER_OF_PLAYERS_IN_TRIAL) {
                this.contestantChosenForTrial[i] = true;
                if (min > this.contestantStrength[i]) {
                    min = this.contestantStrength[i];
                    minIdx = i;
                }
            } else if (this.contestantStrength[i] > min) {
                this.contestantChosenForTrial[minIdx] = false;
                this.contestantChosenForTrial[i] = true;

                /* find new minimum */
                boolean flag = false;
                for (int j = 0; j < (coach.getCoachID() + 1 ) * SimulPar.NUMBER_OF_PLAYERS; j++) {
                    if ( this.contestantChosenForTrial[j] && (this.contestantStrength[j] < min || !flag) ){
                        min = this.contestantStrength[j];
                        minIdx = j;
                        flag = true;
                    }
                }
            }
        }

        /* Updates each contestanst's strength */
        for (i = 0; i < this.contestantStrength.length; i++) {
            if (this.contestantChosenForTrial[i]) {
                this.contestantStrength[i]--;
            } else {
                this.contestantStrength[i]++;
            }
        }

        /* notifies his team that are in "SEAT_AT_THE_BENCH" state*/
        notifyAll();

        /* Blocks while all contestants get ready */
        while (this.nPlayersInPosition[coach.getCoachID()] < SimulPar.NUMBER_OF_PLAYERS) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Operation Seat Down.
     *
     * Called by contestants.
     */
    public synchronized void seatDown(){
        TContestant contestant = (TContestant) Thread.currentThread();
        contestant.setContestantState(TContestantStates.SEAT_AT_BENCH);
        this.repos.setContestantState(contestant.getContestantID(), TContestantStates.SEAT_AT_BENCH);

        while (!this.contestantChosenForTrial[contestant.getContestantID()]) {
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

        int contestantID = contestant.getContestantID();
        this.repos.setContestantState(contestantID, TContestantStates.STAND_IN_POSITION);

        this.nPlayersInPosition[contestantID / SimulPar.NUMBER_OF_PLAYERS]++;

        /* notifies coach in "ASSEMBLE_TEAM" state */
        notifyAll();

        //TODO needs to be waken up by startTrial from referee!!
        /*
        while () {

            try{
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }*/

    }

}