package Entities;

import RefereeSite.IMRefereeSite_TReferee;
import RefereeSite.declareGameWinnerbyKnockOut;

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
    public TReferee(int refereeID, IMRefereeSite_TReferee refereeSite){
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
        int numbertrials;
        int numbergames = 0;

        
        // Loop enquanto o número de jogos não for igual a 3
        while (numbergames != 3) {
            this.refereeSite.announceNewGame();
            numbertrials = 0;
            boolean knockout = false;
            
            // Loop para realizar até 6 trials
            while (numbertrials < 6 && knockout != true) {
                this.refereeSite.callTrial();
                this.refereeSite.startTrial();
                numbertrials++;
                this.refereeSite.assertTrialDecision();
            }
    
            // Após 6 trials, declarar o vencedor do jogo
            this.refereeSite.declareGameWinner(numbergames);
            numbergames++;
        }
    
        // Após o número de jogos atingir 3, declarar o vencedor da partida
        this.refereeSite.declareMatchWinner();
    }

}
