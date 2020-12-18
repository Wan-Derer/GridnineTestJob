package com.gridnine.testing;

import java.util.List;

public class IntersectingSegments extends Filter {

    @Override
    public boolean isExclude(Flight flight) {
        List<Segment> segmentList = flight.getSegments();
        if (segmentList.size() <= 1) {
            return false;
        }

        for (int i = 0; i < segmentList.size() - 1; i++) {
            if (segmentList.get(i).getArrivalDate().isAfter
                    (segmentList.get(i + 1).getDepartureDate())) {
                return true;
            }
        }

        return false;
    }



}
