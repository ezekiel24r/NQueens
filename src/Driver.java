import jdk.management.resource.internal.TotalResourceContext;

import java.sql.Time;
import java.util.ArrayList;

public class Driver {
    public static void main(String [] args){

        final int N = 21;


        Board b = new Board(N);
        /*
        //a solved initial state for testing
        int [] arr = {3,1,4,2};
        Board b = new Board(4, arr);
        */
        b.printBoardStr();
        b.printBoard();
        System.out.println();
        System.out.println("Number of attacks: " + b.totalAttacks());

        long time1 = System.nanoTime();
        Board result = SimAnneal.run(b);
        long elapsedTime1 = System.nanoTime() - time1;


        System.out.println();
        result.printBoardStr();
        result.printBoard();
        System.out.println("Number of attacks: " + result.totalAttacks());

        System.out.println("Fitness score of this board = " + result.fitnessScore());
        System.out.println("Maximum possible fitness score = " + result.maxAttacks());
        System.out.println("Time elapsed: " + elapsedTime1 + "ns, (" + (elapsedTime1/1000000000.0) + "s)");

        System.out.println("\n\n");



        ArrayList<Board> pop = new ArrayList<>();

        int popSize = 10;
        long totalElapsed2 = 0;
        int failCount=0;
        int successCount=0;
        for(int k=0; k<popSize; k++) {


            for (int i = 0; i < N * 2; i++) {
                pop.add(new Board(N));
            }
            long time2 = System.nanoTime();
            Board sol = GeneticAlg.run(pop);

            long elapsedTime2 = System.nanoTime() - time2;
            totalElapsed2 += elapsedTime2;

            if(sol.fitnessScore == sol.maxAttacks()){
                successCount++;
            }



            //sol.printBoardStr();
            //System.out.println("Total attacks: " + sol.totalAttacks());
            //System.out.println("Time elapsed: " + elapsedTime2 + "ns, (" + (elapsedTime2 / 1000000000.0) + "s)");
        }

        double successRate = (double)successCount/(double)popSize;

        System.out.println("Time elapsed for 10 tests: " + totalElapsed2 + "ns, (" + (totalElapsed2 / 1000000000.0) + "s)");
        System.out.println("Success Rate = " + successRate);

        //test
        /*Board x = new Board(8);
        x.printBoardStr();
        Board y = new Board(8);
        y.printBoardStr();
        Board z = GeneticAlg.reproduce(x,y);
        z.printBoardStr();
        */

        //here is some code to test my probability method
        /*

        int heads = 0;
        int tails = 0;
        for(int i=0; i<1000000; i++) {
            if(RenselTools.probability(.50)){
                heads++;
            }
            else
                tails++;
        }
        System.out.println("Heads: " + heads);
        System.out.println("Tails: " + tails);

        */
    }

}








