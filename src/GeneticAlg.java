import java.util.ArrayList;
import java.util.Collections;



public class GeneticAlg {
    //eliteRatio: the only the best (100/n)% will be chosen for reproduction
    private static final int eliteRatio = 5;
    private static final double mutationChance = 0.90;

    public static Board run(ArrayList<Board> pop){
        Board solution = pop.get(0);

        Collections.sort(pop);

        for(int i=0; i<10000; i++){
            ArrayList<Board> newPop = new ArrayList<>(pop.size());


            /*for(int j=0; j<pop.size()/10; j++){
                newPop.add(pop.get(j));
            }*/

            //75% generated from current population
            for(int j=0; j<pop.size()*(.75); j++){
                Board x = randomSelection(pop);
                Board y = randomSelection(pop);

                Board child = reproduce(x,y);

                if(RenselTools.probability(mutationChance)){
                    child = mutate(child);
                }

                //the insert function adds
                insert(newPop, child);
                //newPop.add(child);

                //System.out.println("Fitness: " + j + " " + child.fitnessScore());
            }

            //25% is new individuals added to the population (this helps ensure we don't get stuck in a local max)
            for(int j = ((int)(pop.size()*(.75))); j<pop.size(); j++){
                newPop.add(new Board(pop.get(0).getSize()));
            }
            //population = newPop
            pop = newPop;
            //Collections.sort(pop);
            //for(int j = 0; j<pop.size(); j++){
            solution = pop.get(0);
            if(solution.fitnessScore == solution.maxAttacks()){
                //System.out.println("Solution found on Gen: " + i);
                return solution;
            }
            //}
            //System.out.println("Gen: " + i);
        }

        return solution;
    }


    public static Board randomSelection(ArrayList<Board> pop){
        //Collections.sort(pop);
        Board result = new Board(pop.get(0).getSize());
        int fitnessVal[] = new int [pop.size()/eliteRatio];
        int fitSum = 0;
        for(int i=0; i<pop.size()/eliteRatio; i++){
            fitSum += pop.get(i).fitnessScore;
            fitnessVal[i] = fitSum;
        }
        int randVal = RenselTools.getRandomInt(0,fitSum);
        for(int i=0; i<pop.size()/eliteRatio; i++){
            if(randVal <= fitnessVal[i]){
                result = pop.get(i);
                return result;
            }
        }
        return result;
    }

    //this is the function as defined in the class slides
    /*public static Board reproduce(Board x, Board y){
        int n = x.getSize();
        int c = RenselTools.getRandomInt(0,n);
        int [] child = new int[n];
        for(int i=0; i<c; i++){
            child[i] = x.getPos(i);
        }
        for(int i=c; i<n; i++){
            child[i] = y.getPos(i);
        }
        Board result = new Board(n, child);
        return result;
    }*/

    //this reproduce function can take an element from x or y at any position
    public static Board reproduce(Board x, Board y){
        int n = x.getSize();
        int [] child = new int[n];
        for(int i=0; i<n; i++){
            if(RenselTools.probability(0.5)){
                child[i] = x.getPos(i);
            }else{
                child[i] = y.getPos(i);
            }

        }
        return new Board(n, child);
    }

    public static Board mutate(Board x){
        Board result = new Board(x);
        int index = RenselTools.getRandomInt(0,(x.getSize()-1));
        int position = RenselTools.getRandomInt(1,x.getSize());
        result.setPos(index, position);
        result.fitnessScore();
        return result;
    }

    private static void insert(ArrayList<Board> arrList, Board in){
        for(int i=0; i<arrList.size(); i++){
            if(in.fitnessScore >= arrList.get(i).fitnessScore){
                arrList.add(i, in);
                return;
            }
        }
        //if this is the worse than every element
        arrList.add(in);
    }

}
