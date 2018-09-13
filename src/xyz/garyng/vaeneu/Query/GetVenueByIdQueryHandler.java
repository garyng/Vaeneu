package xyz.garyng.vaeneu.Query;

import com.google.inject.Inject;
import xyz.garyng.vaeneu.Model.Venue;
import xyz.garyng.vaeneu.Storage.IStorage;

import java.util.Optional;

public class GetVenueByIdQueryHandler implements IQueryHandler<GetVenueById, Venue>
{
    private IStorage<Venue> _storage;

    @Inject
    public GetVenueByIdQueryHandler(IStorage<Venue> storage)
    {
        _storage = storage;
    }

    @Override
    public Optional<Venue> Execute(GetVenueById parameter)
    {
        return _storage.Data()
                .stream()
                .filter(v -> v.id() == parameter.id())
                .findFirst();
    }
}
