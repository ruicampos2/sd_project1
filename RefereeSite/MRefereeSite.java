package RefereeSite;

import java.util.*;

import Entities.TReferee;

public class MRefereeSite {

    private boolean gamestart = false;
    private TReferee refstate;


    //colocar aqui
    //Funções: announceNewGame, callTrial, informReferee, startTrial, assertTrialDecision, 
    //declareGameWinner, declareMatchWinner

    public synchronized void announceNewGame(){
        gamestart = true;
        System.out.println("GAME START");
        
        refstate.setRefereeState(2);
        
    }

    public synchronized void callTrial() {
        // Implementation
    }
    
    public synchronized void startTrial() {
        // Implementation
    }

    public synchronized void informReferee(){
        //implem
    }

    public synchronized void assertTrialDecision(){
        //do
    }
    

    public synchronized void declareGameWinner(){
        //do
    }

    public synchronized void declareMatchWinner(){
        //do
    }
}
