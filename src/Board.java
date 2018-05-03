import java.util.ArrayList;

public class Board {
    private ArrayList<Integer> queenPos;
    private int SIZE;

    Board(int n){
        queenPos = new ArrayList<>();
        SIZE = n;
        randomize();
    }

    Board(int n, int [] array){
        queenPos = new ArrayList<>();
        SIZE = n;
        setBoard(array);
    }

    Board(Board in){
        queenPos = new ArrayList<>();
        SIZE = in.getSize();
        if(queenPos.isEmpty()) {
            this.queenPos.addAll(in.queenPos);
        }
    }


    public void setBoard(int [] in){
        for(int i=0; i<SIZE; i++){
            queenPos.add(i,in[i]);
        }
    }

    public void moveQueen(int index, int row){
        queenPos.set(index, row);
    }

    public void randomize(){
        for(int i=0; i<SIZE; i++){
            queenPos.add(i, RenselTools.getRandomInt(1, SIZE));
        }
    }

    //return the total number of attack moves that can be made.
    public int totalAttacks(){
        int result = 0;

        for(int i=0; i<SIZE; i++){
            int row = queenPos.get(i);
            int up = row+1;
            int down = row-1;
            for(int j=i+1; j<SIZE; j++){
                if(row == queenPos.get(j)){
                    result++;
                }
                //there is only one queen per column, so no need to run all checks if one is found in attack path.
                else if(up == queenPos.get(j)){
                    result++;
                }
                else if(down == queenPos.get(j)){
                    result++;
                }
                up++;
                down--;
            }
        }
        //since we were only counting attacks towards the right, double it to include attacks that were to the left.
        return result*2;
    }

    public int maxAttacks(){
        return ((SIZE)*(SIZE-1));
    }

    //fitnessScore used so that higher values is a better board position.
    public int fitnessScore(){
        return (maxAttacks() - totalAttacks());
    }



    public int getPos(int n){
        return queenPos.get(n);
    }

    public void setPos(int index, int pos){
        queenPos.set(index, pos);
    }

    public int getSize() {
        return SIZE;
    }

    public void printBoardStr(){
        for(int i=0; i<SIZE; i++){
            System.out.print(queenPos.get(i) + " ");

        }
        System.out.println();
    }

    public void printBoard(){
        for(int i=0; i<SIZE; i++){
            System.out.println();
            for(int j=0; j<SIZE; j++){
                if(i+1 == queenPos.get(j)){
                    System.out.print("[Q]");
                }else{
                    System.out.print("[ ]");
                }
            }

        }
        System.out.println();
    }
}
