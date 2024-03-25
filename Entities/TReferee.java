package Entities;

import RefereeSite.IMRefereeSite_TReferee;

public class TReferee extends Thread {

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

    @Override
    public void run(){
        boolean run = true;
        while (run) {
            this.refereeSite.announceNewGame();
            this.refereeSite.callTrial();
            
            try {
                sleep ((long) (1 + 100 * Math.random ()));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            this.refereeSite.startTrial();
            try {
                sleep ((long) (1 + 100 * Math.random ()));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            this.refereeSite.assertTrialDecision();

            this.refereeSite.declareGameWinner();

            this.refereeSite.declareMatchWinner();
        



        }
    }
    
}
