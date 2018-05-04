import java.util.ArrayList;
import java.util.Collections;



public class GeneticAlg {
    //ELITE_RATIO: the only the best (100/n)% will be chosen for reproduction
    private static final int ELITE_RATIO = 10;
    //MUTATION_CHANCE: the chance that a gene will mutate when the child is created
    private static final double MUTATION_CHANCE = 0.90;
    //immigration ratio: an optional ratio of children to new randomly generated members
    // a value of 1.0 disables this feature, a value of 0 is a completely random search for a solution
    private static final double IMMIGRATION_RATIO = 1.0;

    private static final double MAX_GENERATIONS = 1000;

    public static Board run(ArrayList<Board> pop){
        Board solution = pop.get(0);

        Collections.sort(pop);

        for(int i = 0; i< MAX_GENERATIONS; i++){
            ArrayList<Board> newPop = new ArrayList<>(pop.size());

            //% generated from current population
            for(int j = 0; j<pop.size()*(IMMIGRATION_RATIO); j++){
                Board x = randomSelection(pop);
                Board y = randomSelection(pop);

                Board child = reproduce(x,y);

                if(RenselTools.probability(MUTATION_CHANCE)){
                    child = mutate(child);
                }


                //the insert function sorts children into the new population (so it stays sorted)
                insert(newPop, child);

            }

            //% of new individuals added to the population
            for(int j = ((int)(pop.size()*(IMMIGRATION_RATIO))); j<pop.size(); j++){
                newPop.add(new Board(pop.get(0).getSize()));
            }

            pop = newPop;

            solution = pop.get(0);
            if(solution.fitnessScore == solution.maxAttacks()){
                //System.out.println("Solution found on Gen: " + i);
                return solution;
            }
        }

        return solution;
    }


    public static Board randomSelection(ArrayList<Board> pop){
        //Collections.sort(pop);
        Board result = new Board(pop.get(0).getSize());
        int fitnessVal[] = new int [pop.size()/ ELITE_RATIO];
        int fitSum = 0;
        for(int i = 0; i<pop.size()/ ELITE_RATIO; i++){
            fitSum += pop.get(i).fitnessScore;
            fitnessVal[i] = fitSum;
        }
        int randVal = RenselTools.getRandomInt(0,fitSum);
        for(int i = 0; i<pop.size()/ ELITE_RATIO; i++){
            if(randVal <= fitnessVal[i]){
                result = pop.get(i);
                return result;
            }
        }
        return result;
    }

    //this is the reproduce function as defined in the class slides
    public static Board reproduce(Board x, Board y){
        int n = x.getSize();
        int c = RenselTools.getRandomInt(0,n);
        int [] child = new int[n];
        for(int i=0; i<c; i++){
            child[i] = x.getPos(i);
        }
        for(int i=c; i<n; i++){
            child[i] = y.getPos(i);
        }
        return new Board(n, child);
    }

    //this reproduce function can take an element from x or y at any position
    /*public static Board reproduce(Board x, Board y){
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
    }*/



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
