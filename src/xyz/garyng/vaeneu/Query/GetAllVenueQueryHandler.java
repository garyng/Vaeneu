package xyz.garyng.vaeneu.Query;

import com.google.inject.Inject;
import xyz.garyng.vaeneu.Model.Venue;
import xyz.garyng.vaeneu.Storage.IStorage;

import java.util.List;
import java.util.Optional;

public class GetAllVenueQueryHandler implements IQueryHandler<GetAllVenue, List<Venue>>
{
    private IStorage<Venue> _storage;

    @Inject
    public GetAllVenueQueryHandler(IStorage<Venue> storage)
    {
        _storage = storage;
    }

    @Override
    public Optional<List<Venue>> Execute(GetAllVenue parameter)
    {
        return Optional.of(_storage.Data());
    }
}
