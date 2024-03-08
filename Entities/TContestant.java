package Entities;

import Bench.IMContestantsBench_TContestants;

public class TContestant implements Runnable{
    private final int contestantID;
    private final IMContestantsBench_TContestants y;

    private TContestant(int contestantID, IMContestantsBench_TContestants y){
        this.contestantID = contestantID;
        this.y = y;
    }

    public static Runnable getInstance(int contestantID, IMContestantsBench_TContestants y){
        return new TContestant(contestantID, y);
    }

    @Override
    public void run(){
        boolean run = true;
        while (run) {
            //complete
        }
        
    }
    
}    
