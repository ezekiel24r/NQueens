import java.util.ArrayList;

public class Driver {
    public static void main(String [] args){



        Board b = new Board(21);
        /*
        //a solved initial state for testing
        int [] arr = {3,1,4,2};
        Board b = new Board(4, arr);
        */
        b.printBoardStr();
        b.printBoard();
        System.out.println();
        System.out.println("Number of attacks: " + b.totalAttacks());

        Board result = SimAnneal.run(b);

        System.out.println();
        result.printBoardStr();
        result.printBoard();
        System.out.println("Number of attacks: " + result.totalAttacks());

        System.out.println("Fitness score of this board = " + result.fitnessScore());
        System.out.println("Maximum possible fitness score = " + result.maxAttacks());



        ArrayList<Board> pop = new ArrayList<>();

        for(int i=0; i<8; i++){
            pop.add(new Board(8));
        }
        Board sol = GeneticAlg.run(pop);
        sol.printBoardStr();
        System.out.println("Total attacks: " + sol.totalAttacks());


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








