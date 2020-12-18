package com.gridnine.testing;

import java.time.LocalDateTime;
import java.util.List;

public class FlightAlreadyCompleted extends Filter {

    @Override
    public boolean isExclude(Flight flight){
        List<Segment> segmentList = flight.getSegments();
        Segment firstSegment = segmentList.get(0);

        return firstSegment.getArrivalDate().isBefore(LocalDateTime.now());
    }

}
