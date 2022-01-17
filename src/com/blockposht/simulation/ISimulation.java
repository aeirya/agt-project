package com.blockposht.simulation;

import com.blockposht.utils.serialize.ISerializable;

public interface ISimulation {
    void go();
    
    default void go(int rounds) {
        for (int i=0; i<rounds; ++i) {
            go();
        }
    }
    
    ISerializable getResult();
}
