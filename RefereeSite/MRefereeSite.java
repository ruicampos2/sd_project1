package RefereeSite;

import Entities.TCoach;
import Entities.TCoachStates;
import Entities.TReferee;
import Entities.TRefereeStates;
import Main.SimulPar;

public class MRefereeSite {

    private int ncoaches = 0;
    private int nplayers= 0;
    private int mark = 0;
    private int TeamApoints = 0;
    private int TeamBpoints = 0;
    private int numbergames = 0;
    private int numbertrials = 0;



    //colocar aqui
    //Funções: announceNewGame, callTrial, informReferee, startTrial, assertTrialDecision, 
    //declareGameWinner, declareMatchWinner

    public synchronized void announceNewGame(){
        numbergames++;
        System.out.println("GAME START");
        
        TReferee refstate = (TReferee) Thread.currentThread();
        refstate.setRefereeState(TRefereeStates.START_OF_A_GAME);


    
    }

    public synchronized void callTrial() {
        System.out.println("WAITING FOR TEAMS READY");

        //COLOCAR teams ready
        while(ncoaches != SimulPar.MAX_COACHES){
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        TReferee refstate = (TReferee) Thread.currentThread();
        refstate.setRefereeState(TRefereeStates.TEAMS_READY);


        notifyAll();

    }
    
    public synchronized void startTrial() {
        System.out.println("TRIAL START");
        numbertrials++;
        while(nplayers != 6){
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        TReferee refstate = (TReferee) Thread.currentThread();
        refstate.setRefereeState(TRefereeStates.WAIT_FOR_TRIAL_CONCLUSION);


        notifyAll();
    }

    public synchronized void informReferee(int coachID, int teamAplayers, int teamBplayers){
        //questão porque não sei se deverá ser !=6 ou !=3 porque é cada treinador
        
        nplayers = teamAplayers + teamBplayers;

    
        while(nplayers != 6){
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("Teams made by the coaches");

        TCoach coachstate = (TCoach) Thread.currentThread();
        coachstate.setCoachState(TCoachStates.WATCH_TRIAL);

        notifyAll();
    }

    public synchronized void assertTrialDecision(){
        
        //VER COMO FAZER
        //Acordar os treinadores again
        while(ncoaches != SimulPar.MAX_COACHES){
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }


        notifyAll();

    }
    

    public synchronized void declareGameWinner(int numbergames){
        //-6 -5 -4 -3 -2 -1 0 1 2 3 4 5 6
        //CAMPO DE JOGO
        //Se numero for negativo, ganha equipa A, positivo equipa B
        while(nplayers != 6){
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }


        while(numbertrials < 6){
            if (mark >= 4){
                System.out.println("Team B is the game winner by knock out!");
                TeamBpoints++;
            }
            else if (mark <= -4) {
                System.out.println("Team A is the game winner by knock out!");
                TeamApoints++;
            }
        }

        if (numbertrials == 6){
            if(mark == 0){
                System.out.println("It's a draw!");
            }
            else if(mark > 0 && mark < 6){
                System.out.println("Team B is the game winner!");
                TeamBpoints++;
            }
            else if(mark < 0 && mark > -6){
                System.out.println("Team A is the game winner!");
                TeamApoints++;
            }
        }

        if (numbergames == 3){
            TReferee refstate = (TReferee) Thread.currentThread();
            refstate.setRefereeState(TRefereeStates.END_OF_A_GAME);
        }
        else {
            TReferee refstate = (TReferee) Thread.currentThread();
            refstate.setRefereeState(TRefereeStates.START_OF_A_GAME);
        }

        notifyAll();
    }

    public synchronized void declareMatchWinner(){
        if (TeamApoints > TeamBpoints){
            System.out.println("Team A wins the match!");
        }
        else {
            System.out.println("Team B wins the match!");
        }
        

        TReferee refstate = (TReferee) Thread.currentThread();
        refstate.setRefereeState(TRefereeStates.END_OF_THE_MATCH);
    }
}
