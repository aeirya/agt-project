package com.blockposht.evolutionary.blockchaingame;

import java.util.ArrayList;
import java.util.List;

public class CyclicList<T> {
    private final int maxSize;
    private final List<T> items;
    private int ptr;

    CyclicList(int maxSize) {
        this.maxSize = maxSize;
        items = new ArrayList<>(maxSize);
        ptr = 0;
    }

    public void add(T t) {
        if (ptr >= items.size())
            items.add(ptr, t);
        else items.set(ptr, t);
        ++ptr;
        if (ptr == maxSize) ptr = 0;
    }

    public List<T> getItems() {
        return items;
    }

    public static void main(String[] args) {
        var list = new CyclicList<Integer>(2);
        list.add(10);
        list.add(20);
        list.add(30);
        System.out.println(list.getItems());
    }
}
