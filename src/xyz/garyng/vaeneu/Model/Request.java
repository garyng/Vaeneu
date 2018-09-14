package xyz.garyng.vaeneu.Model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.OptionalInt;

@Data
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@Builder
public class Request
{
    private int id;
    private int venueId;
    private int requesterId;
    private OptionalInt reviewerId;
    private LocalDateTime startDateTime;
    private LocalDateTime endDateTime;
    private RequestStatus status;
}
