package com.blockposht.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.stream.Collectors;

import com.blockposht.utils.random.RandomUtils;

import org.junit.Test;

public class RandomVectorTest {
    @Test
    public void randomVectorTest() {
        RandomUtils rand = new RandomUtils();
        var v1 = rand.randomVector(10);
        var v2 = rand.randomVector(10);
        assertEquals(true, v1.list.size() == v2.list.size());
        assertEquals(0, (int) Math.round(1-v1.list.stream().reduce((a,b)->a+b).orElseGet(null)));
        assertEquals(0, (int) Math.round(1-v2.list.stream().reduce((a,b)->a+b).orElseGet(null)));
    }
}
