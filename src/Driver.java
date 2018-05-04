import jdk.management.resource.internal.TotalResourceContext;

import java.sql.Time;
import java.util.ArrayList;

public class Driver {
    public static void main(String [] args){

        final int N = 100;
        int nTests = 100;


        /*Board b = new Board(N);



        b.printBoardStr();
        b.printBoard();
        System.out.println();
        System.out.println("Number of attacks: " + b.totalAttacks());
        */


        long totalElapsed = 0;
        int successCount = 0;
        long time1;
        long elapsedTime1;
        Board result;


        for(int i=0; i<nTests; i++) {


            time1 = System.nanoTime();
            result = SimAnneal.run(new Board(N));
            elapsedTime1 = System.nanoTime() - time1;

            totalElapsed += elapsedTime1;

            if(result.totalAttacks() == 0){
                successCount++;
            }
        }

        double successRate = (double)successCount/(double)nTests;

        System.out.println("\nTime elapsed for " + nTests + " tests: " + totalElapsed + "ns, (" + (totalElapsed / 1000000000.0) + "s)");
        System.out.println("Average time for each test: " + totalElapsed/nTests + "ns, (" + ((totalElapsed/nTests) / 1000000000.0) + "s)");

        System.out.println("Success Rate = " + successRate);



        System.out.println("\n\n");






        long totalElapsed2 = 0;
        int successCount2=0;
        long time2;
        long elapsedTime2;
        Board sol;

        for(int k=0; k<nTests; k++) {
            ArrayList<Board> pop = new ArrayList<>();

            for (int i = 0; i < N * 2; i++) {
                pop.add(new Board(N));
            }

            time2 = System.nanoTime();
            sol = GeneticAlg.run(pop);
            elapsedTime2 = System.nanoTime() - time2;

            totalElapsed2 += elapsedTime2;

            if(sol.fitnessScore == sol.maxAttacks()){
                successCount2++;
            }



            //sol.printBoardStr();
            //System.out.println("Total attacks: " + sol.totalAttacks());
            //System.out.println("Time elapsed: " + elapsedTime2 + "ns, (" + (elapsedTime2 / 1000000000.0) + "s)");
        }

        double successRate2 = (double)successCount2/(double)nTests;

        System.out.println("\nTime elapsed for " + nTests + " tests: " + totalElapsed2 + "ns, (" + (totalElapsed2 / 1000000000.0) + "s)");
        System.out.println("Average time for each test: " + totalElapsed2/nTests + "ns, (" + ((totalElapsed2/nTests) / 1000000000.0) + "s)");

        System.out.println("Success Rate = " + successRate2);

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








