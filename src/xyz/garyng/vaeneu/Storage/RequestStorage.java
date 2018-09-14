package xyz.garyng.vaeneu.Storage;

import com.google.inject.TypeLiteral;
import xyz.garyng.vaeneu.Model.Request;
import xyz.garyng.vaeneu.Model.RequestStatus;

import java.lang.reflect.Type;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.OptionalInt;

public class RequestStorage extends JsonStorage<Request>
{
    @Override
    String GetFilename()
    {
        return Request.class.getName();
    }

    @Override
    Type getCollectionType()
    {
        return new TypeLiteral<ArrayList<Request>>()
        {
        }.getType();
    }

    @Override
    public List<Request> GetDefaultData()
    {
        LocalDateTime base = LocalDate.now().plusDays(1).atStartOfDay();

        return new ArrayList<>()
        {
            {
                // 9.30 - 12.30
                add(Request.builder()
                        .id(1)
                        .venueId(1)
                        .requesterId(1)
                        .reviewerId(OptionalInt.empty())
                        .status(RequestStatus.Pending)
                        .startDateTime(base.plusHours(9).plusMinutes(30))
                        .endDateTime(base.plusHours(12).plusMinutes(30))
                        .build());

                // 8 - 9, 10 - 11, 11.30 - 14.30, 17 - 18

                add(Request.builder()
                        .id(2)
                        .venueId(1)
                        .requesterId(1)
                        .reviewerId(OptionalInt.empty())
                        .status(RequestStatus.Pending)
                        .startDateTime(base.plusDays(1).plusHours(8))
                        .endDateTime(base.plusDays(1).plusHours(9))
                        .build());
                add(Request.builder()
                        .id(3)
                        .venueId(1)
                        .requesterId(1)
                        .reviewerId(OptionalInt.empty())
                        .status(RequestStatus.Pending)
                        .startDateTime(base.plusDays(1).plusHours(10))
                        .endDateTime(base.plusDays(1).plusHours(11))
                        .build());
                add(Request.builder()
                        .id(4)
                        .venueId(1)
                        .requesterId(1)
                        .reviewerId(OptionalInt.empty())
                        .status(RequestStatus.Pending)
                        .startDateTime(base.plusDays(1).plusHours(11).plusMinutes(30))
                        .endDateTime(base.plusDays(1).plusHours(14).plusMinutes(30))
                        .build());
                add(Request.builder()
                        .id(5)
                        .venueId(1)
                        .requesterId(1)
                        .reviewerId(OptionalInt.empty())
                        .status(RequestStatus.Pending)
                        .startDateTime(base.plusDays(1).plusHours(17))
                        .endDateTime(base.plusDays(1).plusHours(18))
                        .build());
            }
        };
    }
}
