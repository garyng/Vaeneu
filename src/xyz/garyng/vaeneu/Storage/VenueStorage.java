package xyz.garyng.vaeneu.Storage;

import xyz.garyng.vaeneu.Fakes.Fakes;
import com.google.inject.TypeLiteral;
import xyz.garyng.vaeneu.Model.Venue;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class VenueStorage extends JsonStorage<Venue>
{
    @Override
    String GetFilename()
    {
        return VenueStorage.class.getName();
    }

    @Override
    Type getCollectionType()
    {
        return new TypeLiteral<ArrayList<Venue>>()
        {
        }.getType();
    }

    @Override
    public List<Venue> GetDefaultData()
    {
        return Fakes.FakeVenues();
    }
}
