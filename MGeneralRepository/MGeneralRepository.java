package MGeneralRepository;


import Entities.TContestantStates;
import Main.SimulPar;

public class MGeneralRepository {

    /**
     * State of each contestant.
     */
    private int [] contestantStates;

    public MGeneralRepository (int n_teams) {
        this.contestantStates = new int[n_teams];
    }

    /**
     * Set the new state of the contestant
     *
     * @param id ID of the contestant to set
     * @param state New state of the contestant
     */
    public synchronized void setContestantState(int id, int state){
        contestantStates[id] = state;
        printState();
    }

    /**
     * Prints state
     */
    //TODO
    private void printState(){

    }


}
