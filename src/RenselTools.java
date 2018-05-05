import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class RenselTools {
    //get random Int between min and max, inclusive
    public static int getRandomInt(int min, int max){
        return ThreadLocalRandom.current().nextInt(min, max + 1);
    }

    public static boolean probability(double in){
        /*
        the input (in) is a double representing decimal probability.
        the idea behind this method is to allow probabilities as small as 1 in a million to be considered,
        but nothing less would be allowed.
        */
        in*=1000000;
        double rand = (double)getRandomInt(1,1000000);
        return rand < in;
    }


}
