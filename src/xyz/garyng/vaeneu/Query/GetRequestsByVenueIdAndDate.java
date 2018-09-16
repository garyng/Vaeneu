package xyz.garyng.vaeneu.Query;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class GetRequestsByVenueIdAndDate implements IQuery
{
    private int venueId;
    private LocalDate date;
}


