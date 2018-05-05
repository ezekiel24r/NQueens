public class SimAnneal {
    public static Board run(Board in){
        Board current;
        Board next;

        current = new Board(in);
        Double E = Math.E;
        Double t;

        Double deltaE;
        int randRow, randIndex;

        /*This simulated annealing function tries to find a global minimun, rather than a muximum
            I did this because I wanted to use the number of totalAttacks to measure how good a boardState was,
            and wanted to search for 0 totalAttacks.
         */

        for(int i=0;/* i < Integer.MAX_VALUE */; i++){
            t = schedule((double)i);
            if(t == 0){
                return current;
            }
            next = new Board(current);

            //move a random queen to a random position
            randIndex = RenselTools.getRandomInt(0,(next.getSize()-1));
            randRow = RenselTools.getRandomInt( 1, next.getSize());

            next.moveQueen(randIndex, randRow);
            if(next.totalAttacks() == 0){
                return next;
            }

            deltaE = (double)(next.totalAttacks() - current.totalAttacks());

            //I look for when deltaE is less than 0, because I am trying to find a minimum.
            if(deltaE < 0){
                current = next;
            }
            //For the same reason as above, I had to use -deltaE in this power function.
            else if(RenselTools.probability(Math.pow(E, (-deltaE/t))))
                current = next;
        }
    }

    private static double schedule(double t){
        return ((-(1.0 / 40000.0) * (t)) + .50);
    }
}
