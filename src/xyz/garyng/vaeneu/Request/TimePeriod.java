package xyz.garyng.vaeneu.Request;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.TemporalUnit;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Getter
@AllArgsConstructor
public class TimePeriod
{
    private LocalTime startTime;
    private LocalTime endTime;

    public static TimePeriod FromDateTime(LocalDateTime start, LocalDateTime end)
    {
        return new TimePeriod(start.toLocalTime(), end.toLocalTime());
    }

    public static TimePeriod BetweenTimePeriods(TimePeriod start, TimePeriod end)
    {
        return new TimePeriod(start.endTime, end.startTime);
    }

    public static List<TimePeriod> FindGaps(List<TimePeriod> occupied)
    {
        LocalTime earliest = LocalTime.of(8, 0);
        LocalTime latest = LocalTime.of(20, 0);

        List<TimePeriod> periods = occupied.stream()
                .sorted(Comparator.comparing(tp -> tp.startTime))
                .collect(Collectors.toList());

        periods.add(0, new TimePeriod(earliest, earliest));
        periods.add(new TimePeriod(latest, latest));

        List<TimePeriod> gaps = new ArrayList<>();

        for (int i = 1; i < periods.size(); i++)
        {
            TimePeriod gap = TimePeriod.BetweenTimePeriods(periods.get(i - 1), periods.get(i));
            if (!gap.IsZeroPeriod())
            {
                gaps.add(gap);
            }
        }

        return gaps;
    }

    public static List<TimePeriod> AggregateAdjacent(List<TimePeriod> periods)
    {
        List<TimePeriod> sortedPeriods = periods.stream()
                .sorted(Comparator.comparing(tp -> tp.startTime))
                .collect(Collectors.toList());

        List<TimePeriod> aggregated = new ArrayList<>();

        TimePeriod aggregate = sortedPeriods.get(0);

        for (int i = 1; i < sortedPeriods.size(); i++)
        {
            TimePeriod current = sortedPeriods.get(i);
            if (aggregate.IsEndingRightBefore(current))
            {
                aggregate = aggregate.Merge(current);
            } else
            {
                aggregated.add(aggregate);
                aggregate = current;
            }
        }
        aggregated.add(aggregate);
        return aggregated;
    }

    public boolean IsZeroPeriod()
    {
        return startTime.equals(endTime);
    }

    public List<TimePeriod> Explode(long amountToAdd, TemporalUnit unit)
    {
        LocalTime current = startTime;
        List<TimePeriod> periods = new ArrayList<>();

        Function<LocalTime, LocalTime> increment = time -> time.plus(amountToAdd, unit);

        while (increment.apply(current).isBefore(endTime) || increment.apply(current).equals(endTime))
        {
            periods.add(new TimePeriod(current, increment.apply(current)));
            current = increment.apply(current);
        }
        return periods;
    }

    /**
     * Check whether the current {@link TimePeriod} ends right before {@code timePeriod}.
     */
    public boolean IsEndingRightBefore(TimePeriod timePeriod)
    {
        return endTime.equals(timePeriod.startTime);
    }

    public TimePeriod Merge(TimePeriod timePeriod)
    {
        return new TimePeriod(startTime, timePeriod.endTime);
    }

    @Override
    public String toString()
    {
        return String.format("%1$tl:%1$tM %1$Tp - %2$tl:%2$tM %2$Tp", startTime, endTime);
    }
}
