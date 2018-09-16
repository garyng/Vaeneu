package xyz.garyng.vaeneu.Query;

import com.google.inject.Inject;
import xyz.garyng.vaeneu.Model.Request;
import xyz.garyng.vaeneu.Model.RequestStatus;
import xyz.garyng.vaeneu.Storage.IStorage;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class GetRequestsByVenueIdAndDateQueryHandler implements IQueryHandler<GetRequestsByVenueIdAndDate, List<Request>>
{

    private final IStorage<Request> _storage;

    @Inject
    GetRequestsByVenueIdAndDateQueryHandler(IStorage<Request> storage)
    {
        _storage = storage;
    }

    @Override
    public Optional<List<Request>> Execute(GetRequestsByVenueIdAndDate parameter)
    {
        List<Request> result = _storage.Data()
                .stream()
                .filter(r -> r.venueId() == parameter.venueId() &&
                        r.startDateTime().toLocalDate().equals(parameter.date()) &&
                        (r.status().equals(RequestStatus.Pending) || r.status().equals(RequestStatus.Accepted)))
                .collect(Collectors.toList());

        return Optional.of(result);
    }
}
