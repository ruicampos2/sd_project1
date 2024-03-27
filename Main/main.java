package Main;

import Entities.*;
import RefereeSite.*;
import Bench.*;
import Playground.*;
import MGeneralRepository.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.File;

public class main {

    public static void main(String[] args) {

        TContestant[] contestant = new TContestant[SimulPar.MAX_CONTESTANTS];
        TCoach[] coach = new TCoach[SimulPar.MAX_COACHES];
        TReferee referee;
        int contestantStrength[] = new int[SimulPar.MAX_CONTESTANTS];
        int game[] = new int[SimulPar.GAMES_PER_MATCH];
        int trials[] = new int[SimulPar.TRIALS_PER_GAME];

        // GeneralRepo repos;
        String fileName;
        char opt;
        boolean success; // end of operation flag
        MPlayground playground;
        MContestantsBench bench;
        IMRefereeSite_TReferee referee_site;


        int[] teamA_ids = new int[5];
        int[] teamB_ids = new int[5];

        Scanner scanner = new Scanner(System.in);

        for (int i = 0; i < SimulPar.MAX_CONTESTANTS; i++) {
            contestantStrength[i] = (int) (Math.random()
                    * (SimulPar.MAX_CONTESTANT_STRENGTH - SimulPar.MIN_CONTESTANT_STRENGTH + 1))
                    + SimulPar.MIN_CONTESTANT_STRENGTH;
        }

        // Criando uma instância do TReferee
        referee = new TReferee(1, referee_site);

        playground = new MPlayground(contestant, referee);
        bench = new MContestantsBench(coach, contestant, teamA_ids, teamB_ids);//VER

        

        for (int i = 0; i < SimulPar.MAX_COACHES; i++) {
            coach[i] = new TCoach(i, bench, playground, referee_site);
        }

        for (int i = 0; i < SimulPar.MAX_CONTESTANTS; i++) {
            contestant[i] = new TContestant(i, contestantStrength[i], bench, playground);
            if(i < 5){
                teamA_ids[i] = contestant[i].getContestantId();
            }else{
                teamB_ids[i-5] = contestant[i].getContestantId();
            }
        }

        playground = new MPlayground(contestant, referee);

        /* start of the simulation */
        for (int i = 0; i < SimulPar.MAX_CONTESTANTS; i++) {
            contestant[i].start();
        }

        for (int i = 0; i < SimulPar.MAX_COACHES; i++) {
            coach[i].start();
        }

        referee.start();


        /* waiting for the end of the simulation */
        for (int i = 0; i < SimulPar.MAX_CONTESTANTS; i++) {
            try {
                contestant[i].join();
            } catch (InterruptedException ignored) {
            }

            System.out.println("The contestant " + (i + 1) + " has terminated.");
        }

        for (int i = 0; i < SimulPar.MAX_COACHES; i++) {
            try {
                coach[i].join();
            } catch (InterruptedException ignored) {
            }

            System.out.println("The coach " + (i + 1) + " has terminated.");
        }

        try {
            referee.join();
        } catch (InterruptedException ignored) {
        }
        System.out.println("The referee has terminated.");

        scanner.close();
    }

}