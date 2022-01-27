package com.blockposht.utils.random;

import java.util.Random;

import com.blockposht.utils.Vector;

public class RandomUtils {

    private final Random random;

    public RandomUtils() {
        random = new Random();
    }

    public RandomUtils(Random random) {
        this.random = random;
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

    public int nextInt(int bound) {
        return random.nextInt(bound);
    }

    public Vector randomVector(int n) {
        Vector v = new Vector();
        for (int i=0; i<n; ++i) {
            v.add(random.nextInt());
        }
        return v.normalized();
    }

    public Random getRandom() {
        return random;
    }

}
