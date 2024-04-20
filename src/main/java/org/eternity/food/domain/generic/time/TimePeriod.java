package org.eternity.food.domain.generic.time;

import jakarta.persistence.Embeddable;
import lombok.ToString;
import org.eternity.base.domain.ValueObject;

import java.time.LocalTime;

@Embeddable
@ToString
public class TimePeriod extends ValueObject<TimePeriod> {
    private LocalTime startTime;
    private LocalTime endTime;

    public static TimePeriod between(LocalTime startTime, LocalTime endTime) {
        return new TimePeriod(startTime, endTime);
    }

    public TimePeriod(LocalTime startTime, LocalTime endTime) {
        this.startTime = startTime;
        this.endTime = endTime;
    }

    TimePeriod() {
    }

    public boolean contains(LocalTime datetime) {
        return (datetime.isAfter(startTime) || datetime.equals(startTime)) &&
               (datetime.isBefore(endTime) || datetime.equals(endTime));
    }

    public TimePeriod putOffHours(long hours) {
        return new TimePeriod(startTime.plusHours(hours), endTime.plusHours(hours));
    }
}
