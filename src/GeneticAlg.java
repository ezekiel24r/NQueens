import java.util.ArrayList;

public class GeneticAlg {
    public static Board run(ArrayList<Board> pop){
        Board solution;

        for(int i=0; /*i<Integer.MAX_VALUE*/; i++){
            ArrayList<Board> newPop = new ArrayList<>(pop.size());

            for(int j=0; j<pop.size(); j++){
                Board x = new Board(randomSelection(pop));
                Board y = new Board(randomSelection(pop));
                //select x with fitness probabiliry
                //select y with fitness probability
                //create child with reproduce(x, y)
                Board child = reproduce(x,y);

                if(RenselTools.probability(0.05)){
                    child = mutate(child);
                }
                //add child newPop
                newPop.add(child);
            }
            //population = newPop
            pop = newPop;
            for(int j = 0; j<pop.size(); j++){
                if(pop.get(j).totalAttacks() == 0){
                    solution = pop.get(j);
                    return solution;
                }
            }
            //System.out.println("Gen: " + i);
        }

        //return solution;
    }


    public static Board randomSelection(ArrayList<Board> pop){
        Board result = new Board(pop.get(0).getSize());
        int fitnessVal[] = new int [pop.size()];
        int fitSum = 0;
        for(int i=0; i<pop.size(); i++){
            fitSum += pop.get(i).fitnessScore();
            fitnessVal[i] = fitSum;
        }
        int randVal = RenselTools.getRandomInt(0,fitSum);
        for(int i=0; i<pop.size(); i++){
            if(randVal <= fitnessVal[i]){
                result = pop.get(i);
            }
        }
        return result;
    }

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
        Board result = new Board(n, child);
        return result;
    }

    public static Board mutate(Board x){
        Board result = new Board(x);
        int index = RenselTools.getRandomInt(0,(x.getSize()-1));
        int position = RenselTools.getRandomInt(1,x.getSize());
        result.setPos(index, position);
        return result;
    }

}
