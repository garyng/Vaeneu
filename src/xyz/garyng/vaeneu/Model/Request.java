package xyz.garyng.vaeneu.Model;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.OptionalInt;

@Data
@Builder
public class Request
{
    private int id;
    private int venueId;
    private int requesterId;
    @Builder.Default
    private OptionalInt reviewerId = OptionalInt.empty();
    private LocalDateTime startDateTime;
    private LocalDateTime endDateTime;
    @Builder.Default
    private RequestStatus status = RequestStatus.Pending;
}
