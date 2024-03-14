package Entities;

import RefereeSite.IMRefereeSite_TReferee;

public class TReferee implements Runnable {

    /**
     * ID of the referee.
     */
    private final int refereeID;

    /**
     * Reference to the Referee Site.
     */
    private final IMRefereeSite_TReferee refereeSite;

    /**
     * State of the referee.
     */
    private int refereeState;

    /**
     * Instantiation of the referee thread.
     *
     * @param refereeID ID of the Referee
     * @param refereeSite Reference to the Referee Site
     */
    private TReferee(int refereeID, IMRefereeSite_TReferee refereeSite){
        this.refereeID = 1;
        this.refereeSite = refereeSite;
        this.refereeState = TRefereeStates.START_OF_THE_MATCH;
    }

    /**
     * Get the state of the Referee.
     *
     * @return State of referee
     */
    public int getRefereeState() {
        return refereeState;
    }

    /**
     * Set the state of the Referee
     *
     * @param refereeState New state of the referee
     */
    public void setRefereeState(int refereeState) {
        this.refereeState = refereeState;
    }

    public static Runnable getInstance(int refereeID, IMRefereeSite_TReferee z){
        return new TReferee(refereeID, z);
    }

    @Override
    public void run(){
        boolean run = true;
        while (run) {
            //complete
        }
        
    }
    
}
