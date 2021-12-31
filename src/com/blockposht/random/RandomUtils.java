package com.blockposht.random;

import java.util.List;
import java.util.Random;

// import org.joml;
public class RandomUtils {

    private final Random random;

    public RandomUtils() {
        random = new Random();
    }

    public int choose(Vector weights) {
        double x = random.nextDouble();
        var v = weights;
        if (v.sum() == 0.0) {
            int n = weights.size();
            v = Vector.ones(n).div(n);
        }
        if (v.sum() != 1) v = v.normalized();
        v = v.cumulative();

        for (int i=0; i<v.size(); ++i) {
            if (v.get(i)>=x) return i;
        }  
        return -1;
    }

    public double nextDouble() {
        return random.nextDouble();
    }

    public Random getRandom() {
        return random;
    }

}
