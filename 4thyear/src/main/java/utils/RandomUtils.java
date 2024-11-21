package utils;

import java.security.SecureRandom;
import java.util.Random;

/**
 * Created by dindar.oz on 9/29/2017.
 */
public class RandomUtils {
    static Random rng = new SecureRandom();

    public static double randomBinomial()
    {
        return  -1 + rng.nextDouble()*2; //rng.nextDouble()-rng.nextDouble();
    }

    public static int randomInt(int size) {
        return rng.nextInt(size);
    }

    public static long randomLong() {
        return rng.nextLong();
    }
}
