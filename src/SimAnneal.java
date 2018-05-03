public class SimAnneal {
    public static Board run(Board in){
        Board current;
        Board next;

        current = new Board(in);
        Double E = Math.E;
        Double t = 100000.0;

        Double deltaE;
        int randRow, randIndex;

        //simulated annealing attmepts to find a global minimum (as it is described in class)

        for(int i=0; i < Integer.MAX_VALUE ; i++){
            t = schedule((double)i);
            //T = schedule(t)
            //if T = 0 then return current
            if(t == 0){
                return current;
            }
            next = new Board(current);
            //move a random queen to a random location

            randIndex = RenselTools.getRandomInt(0,(next.getSize()-1));
            randRow = RenselTools.getRandomInt( 1, next.getSize());

            next.moveQueen(randIndex, randRow);
            if(next.totalAttacks() == 0){
                return next;
            }
            deltaE = (double)(next.totalAttacks() - current.totalAttacks());
            //System.out.println("nextAttacks, currentAttacks = " + (next.totalAttacks() + ", " + current.totalAttacks()));
            //System.out.println("deltaE = " + deltaE);
            //System.out.println("t = " + t);
            //System.out.println("Math.pow(E, (deltaE/t)) = " + Math.pow(E, (-deltaE/t)));
            if(deltaE < 0){
                current = next;
            }
            else if(RenselTools.probability(Math.pow(E, (-deltaE/t))))
                current = next;
        }
        return current;
    }

    private static double schedule(double t){
        //System.out.println("schedule(" + t + ") = " + ( -(1.0/10000.0)*(t)  + 30.0));
        return ( -(1.0/100000.0)*(t)  + 1.0);
    }
}
