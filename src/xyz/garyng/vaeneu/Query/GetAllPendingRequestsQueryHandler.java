package xyz.garyng.vaeneu.Query;

import com.google.inject.Inject;
import xyz.garyng.vaeneu.Model.Request;
import xyz.garyng.vaeneu.Model.RequestStatus;
import xyz.garyng.vaeneu.Storage.IStorage;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class GetAllPendingRequestsQueryHandler implements IQueryHandler<GetAllPendingRequests, List<Request>>
{

    private final IStorage<Request> _storage;

    @Inject
    public GetAllPendingRequestsQueryHandler(IStorage<Request> storage)
    {
        _storage = storage;
    }

    @Override
    public Optional<List<Request>> Execute(GetAllPendingRequests parameter)
    {
        return Optional.of(
                _storage.Data()
                        .stream()
                        .filter(r -> r.status().equals(RequestStatus.Pending))
                        .collect(Collectors.toList()));
    }
}
