package Entities;

import Bench.IMContestantsBench_TCoach;
public class TCoach implements Runnable{

    private final int coachID;
    private final IMContestantsBench_TCoach x;

    private TCoach(int coachid, IMContestantsBench_TCoach x){
        this.coachID = coachid;
        this.x = x;
    }

    public static Runnable getInstance(int coachID, IMContestantsBench_TCoach x){
        return new TCoach(coachID, x);
    }

    @Override
    public void run(){
        boolean run = true;
        while (run) {
            //complete
        }
        
    }
    
}
