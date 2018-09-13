package xyz.garyng.vaeneu.Fakes;

import com.github.javafaker.Faker;
import org.apache.commons.lang3.mutable.MutableInt;
import xyz.garyng.vaeneu.Model.Venue;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Fakes
{
    public static Venue FakeVenue(MutableInt id, String type, int capacity)
    {
        Faker faker = new Faker();
        return Venue.builder()
                .id(id.getAndIncrement())
                .name(String.format("%s #%04d", type, id.getValue()))
                .description(faker.lorem().sentence(25))
                .capacity(capacity)
                .build();
    }

    public static List<Venue> FakeVenues()
    {
        class Data
        {
            public String type;
            public int capacity;

            public Data(String type, int capacity)
            {
                this.type = type;
                this.capacity = capacity;
            }
        }
        MutableInt id = new MutableInt(1);
        return Stream.of(new Data("Tutorial Room", 35),
                new Data("Main Hall", 400),
                new Data("Exam Hall", 500),
                new Data("Lecture Hall", 100))
                .flatMap(data -> IntStream.range(0, 20)
                        .mapToObj(i ->
                                Fakes.FakeVenue(id, data.type, data.capacity)))
                .collect(Collectors.toList());
    }
}
