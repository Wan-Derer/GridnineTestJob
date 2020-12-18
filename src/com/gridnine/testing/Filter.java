package com.gridnine.testing;

// Here we have to define some common fields/methods for all filters
// Filer contains some rules to _exclude_ Flight from list
public abstract class Filter {

    // method isExclude returns true if the Flight meets filter criteria
    boolean isExclude(Flight flight) {
        return false;
    }

    // here we can add checking if flight == null if applicable
    // also we can add checking if flight is not contains segments if applicable

}
