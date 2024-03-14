package Entities;

import RefereeSite.IMRefereeSite_TReferee;

public class TReferee implements Runnable {

    private final int refereeID;
    private final IMRefereeSite_TReferee z;

    /**
     * Referee state.
     */
    private int refereeState;

    private TReferee(int refereeID, IMRefereeSite_TReferee z){
        this.refereeID = 1;
        this.z = z;
    }

    public int getRefereeState() {
        return refereeState;
    }

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
