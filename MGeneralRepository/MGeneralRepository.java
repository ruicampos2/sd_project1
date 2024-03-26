package MGeneralRepository;


import Entities.TContestantStates;
import Main.SimulPar;

public class MGeneralRepository {

    /**
     * State of each contestant.
     */
    private int [] contestantStates;

    /**
     * State of each coach.
     */
    private int [] coachStates;

    /**
     * Instatiation of the general repository.
     *
     * @param n_teams Number of teams in simulation.
     * @param n_players Number of players per team.
     */
    public MGeneralRepository (int n_teams, int n_players) {
        this.contestantStates = new int[n_teams * n_players];
        this.coachStates = new int[n_teams];
    }

    /**
     * Set the state of a contestant.
     *
     * @param id ID of the contestant to set
     * @param state New state of the contestant
     */
    public synchronized void setContestantState(int id, int state){
        contestantStates[id] = state;
        printState();
    }

    /**
     * Set the state of a coach.
     *
     * @param id ID of the coach to set.
     * @param state New state of the coach.
     */
    public synchronized void setCoachState(int id, int state) {
        coachStates[id] = state;
        printState();
    }

    /**
     * Prints state
     */
    //TODO
    private void printState(){

    }


}
