package com.blockposht.simulation;

import java.util.List;

public interface IPopulationGenerator<T> {
    List<T> generatePopulation();
}
