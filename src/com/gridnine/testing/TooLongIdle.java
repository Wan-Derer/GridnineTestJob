package com.gridnine.testing;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;
import java.util.List;

public class TooLongIdle extends Filter {
    private long maxIdleTimeMillis;
    private IntersectingSegments intersecting = new IntersectingSegments();

    public TooLongIdle(int idleTimeHours, int idleTimeMinutes) {
        if (idleTimeHours < 0 || idleTimeMinutes < 0 || idleTimeMinutes > 59) {
            throw new IllegalArgumentException("Hours: " + idleTimeHours +
                    "\nMinutes: " + idleTimeMinutes);
        }
        this.maxIdleTimeMillis = (idleTimeMinutes + 60L * idleTimeHours) * 60 * 1000;
    }

    @Override
    public boolean isExclude(Flight flight) {
        if (intersecting.isExclude(flight)) {
            throw new IllegalArgumentException("Filter is not applicable!\n" +
                    "This flight contains intersecting segments");
        }

        List<Segment> segmentList = flight.getSegments();
        if (segmentList.size() <= 1) {
            return false;
        }

        long flightIdleTime = 0;

        for (int i = 0; i < segmentList.size() - 1; i++) {
            LocalDateTime departure = segmentList.get(i + 1).getDepartureDate();
            LocalDateTime arrival = segmentList.get(i).getArrivalDate();

            long departureMmillis =
                    Date.from(departure.toInstant(ZoneOffset.ofHours(0))).getTime();
            long arrivalMmillis =
                    Date.from(arrival.toInstant(ZoneOffset.ofHours(0))).getTime();

            flightIdleTime += departureMmillis - arrivalMmillis;
            if (flightIdleTime > maxIdleTimeMillis) {
                return true;
            }

        }

        return false;
    }
}
