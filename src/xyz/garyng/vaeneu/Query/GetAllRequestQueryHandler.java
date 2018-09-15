package xyz.garyng.vaeneu.Query;

import com.google.inject.Inject;
import xyz.garyng.vaeneu.Model.Request;
import xyz.garyng.vaeneu.Storage.IStorage;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class GetAllRequestQueryHandler implements IQueryHandler<GetAllRequest, List<Request>>
{
    private final IStorage<Request> _storage;

    @Inject
    GetAllRequestQueryHandler(IStorage<Request> storage)
    {
        _storage = storage;
    }


    @Override
    public Optional<List<Request>> Execute(GetAllRequest parameter)
    {
        return Optional.of(
                _storage.Data()
                        .stream()
                        .filter(v -> v.requesterId() == parameter.userId())
                        .collect(Collectors.toList()));
    }
}
