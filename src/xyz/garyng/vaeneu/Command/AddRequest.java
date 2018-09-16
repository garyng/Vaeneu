package xyz.garyng.vaeneu.Command;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@Builder
public class AddRequest implements ICommand
{
    private int venueId;
    private int requesterId;
    private LocalDate date;
    private LocalTime startTime;
    private LocalTime endTime;
}

