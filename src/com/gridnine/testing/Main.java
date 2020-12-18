package com.gridnine.testing;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        // Flights list
        List<Flight> flightList = FlightBuilder.createFlights();
        System.out.println("Complete flights list:");
        for (Flight flight : flightList) {
            System.out.println(flight);
        }

        // Filter instances
        FlightAlreadyCompleted flightAlreadyCompleted = new FlightAlreadyCompleted();
        IntersectingSegments intersectingSegments = new IntersectingSegments();
        TooLongIdle tooLongIdle = new TooLongIdle(2,0);

        // Filter sets
        // We can make complex filters combining primitive ones
        List<Filter> filters1 = new ArrayList<>();
        filters1.add(flightAlreadyCompleted);
            // here we can add other filters
            // Fillets will apply in addition order

        List<Filter> filters2 = new ArrayList<>();
        filters2.add(intersectingSegments);
        List<Filter> filters3 = new ArrayList<>();
        filters3.add(tooLongIdle);

        //  Apply filers and print filtered lists
        applyFiltersAndPrintList(flightList, filters1, "Filters Set #1");
        applyFiltersAndPrintList(flightList, filters2, "Filters Set #2");
        applyFiltersAndPrintList(flightList, filters3, "Filters Set #3");

    }

    private static void applyFiltersAndPrintList
            (List<Flight> flightList, List<Filter> filters, String filtersName) {

        LinkedList<Flight> tempList = new LinkedList<>(flightList);

        for(Flight flight: flightList){
            for (Filter filter:filters){
                // here we have to catch and process filters exceptions!!!
                if (filter.isExclude(flight)){
                    tempList.remove(flight);
                    break;
                }
            }
        }

        System.out.println("\nFlights list filtered by " + filtersName + ":");
        for (Flight flight : tempList) {
            System.out.println(flight);
        }

    }
}
