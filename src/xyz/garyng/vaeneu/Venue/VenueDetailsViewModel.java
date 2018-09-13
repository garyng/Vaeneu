package xyz.garyng.vaeneu.Venue;

import com.google.inject.Inject;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import xyz.garyng.vaeneu.Command.ICommandDispatcher;
import xyz.garyng.vaeneu.Model.Venue;
import xyz.garyng.vaeneu.NavigationService;
import xyz.garyng.vaeneu.Query.GetVenueById;
import xyz.garyng.vaeneu.Query.IQueryDispatcher;
import xyz.garyng.vaeneu.Service.AuthenticationService;
import xyz.garyng.vaeneu.ViewModelBase;

public class VenueDetailsViewModel extends ViewModelBase
{
    public final void setVenueId(int id)
    {
        Venue venue = _queryDispatcher.Dispatch(Venue.class,
                new GetVenueById(id)).get();
        setVenue(venue);
    }

    // VenueProperty
    private final ObjectProperty<Venue> VenueProperty = new SimpleObjectProperty<>(this, "Venue");

    public final ObjectProperty<Venue> VenueProperty()
    {
        return VenueProperty;
    }

    public final Venue getVenue()
    {
        return VenueProperty.get();
    }

    public final void setVenue(Venue value)
    {
        VenueProperty.set(value);
    }

    @Inject
    public VenueDetailsViewModel(NavigationService navigation, AuthenticationService authentication, IQueryDispatcher queryDispatcher, ICommandDispatcher commandDispatcher)
    {
        super(navigation, authentication, queryDispatcher, commandDispatcher);
    }
}