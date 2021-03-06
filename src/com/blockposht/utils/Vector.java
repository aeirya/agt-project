package com.blockposht.utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import com.blockposht.utils.serialize.ISerializable;
import com.blockposht.utils.serialize.ISerializer;

public class Vector implements ISerializable {
    public final List<Double> list;

    public static Vector zeros(int n) {
        return fill(n, 0);
    } 
    
    public static Vector ones(int n) {
        return fill(n, 1);
    }
    
    public static Vector fill(int n, int x) {
        var v = new Vector();
        for (int i=0; i<n; ++i)
            v.add(x);
        return v;
    }

    public Vector() {
        list = new ArrayList<>();
    }
    
    public Vector(List<Double> list) {
        this.list = list;
    }

    /** add vectors */
    public Vector add(Vector vec) {
        return new Vector(
            IntStream
                .range(0, size())
                .mapToObj(i->get(i)+vec.get(i))
                .collect(Collectors.toList())
            );
    }

    /** mult vector and scalar */
    public Vector mult(double scalar) {
        return new Vector(
            list.stream().map(i -> i*scalar).collect(Collectors.toList())
        );
    }

    /** sliding mean with vec, with weight of beta */
    public Vector slidingMean(Vector vec, float beta) {
        return this.mult((1-beta)).add(vec.mul(beta));
    }

    /** add item */
    public void add(Double item) {
        list.add(item);
    }

    public void add(Integer i) {
        add((double)i);
    }

    public void set(int index, Double element) {
        list.set(index, element);
    }

    public void set(int index, int element) {
        set(index, (double) element);
    }

    public Double max() {
        return list.stream().collect(Collectors.maxBy(Comparator.naturalOrder())).orElse(0.0);
    }

    public Double sum() {
        return list.stream().reduce((a,b) -> a+b).orElse(0.0);
    }

    public Vector normalized() {
        var sum = this.sum();
        return new Vector(list.stream().map(x -> x/sum).collect(Collectors.toList()));
    }

    public Vector cumulative() {
        var v = new Vector();
        double sum = 0;
        for (int i=0; i<size(); ++i) {
            sum += get(i);
            v.add(sum);
        }
        return v;
    }

    public Vector divide(Vector v) {
        if (v.size() != this.size()) return null;
        List<Double> l = new ArrayList<>();
        for (int i=0; i<size(); ++i) {
            l.add(this.get(i)/v.get(i));
        }
        return new Vector(l);
    }

    public Vector mul(double x) {
        Vector v = new Vector();
        for (int i=0; i<list.size(); ++i) {
            v.add(get(i)*x);
        }
        return v;
    }

    public Vector div(double x) {
        return this.mul(1.0/x);
    }

    @Override
    public String toString() {
        return list.toString();
    }

    public Double get(int i) {
        return list.get(i);
    }

    public int size() {
        return list.size();
    }

    @Override
    public void serialize(ISerializer ser) throws IOException {
        ser.serialize(list);
    }

}
