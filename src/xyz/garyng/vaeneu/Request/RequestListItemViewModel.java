package xyz.garyng.vaeneu.Request;

import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import xyz.garyng.vaeneu.Command.ICommandDispatcher;
import xyz.garyng.vaeneu.Model.Request;
import xyz.garyng.vaeneu.Model.Venue;
import xyz.garyng.vaeneu.NavigationService;
import xyz.garyng.vaeneu.Query.GetVenueById;
import xyz.garyng.vaeneu.Query.IQueryDispatcher;
import xyz.garyng.vaeneu.Service.AuthenticationService;
import xyz.garyng.vaeneu.ViewModelBase;

public class RequestListItemViewModel extends ViewModelBase
{
    // RequestProperty
    private final ObjectProperty<Request> RequestProperty = new SimpleObjectProperty<>(this, "Request");

    public final ObjectProperty<Request> RequestProperty()
    {
        return RequestProperty;
    }

    public final Request getRequest()
    {
        return RequestProperty.get();
    }

    public final void setRequest(Request value)
    {
        RequestProperty.set(value);
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
    public RequestListItemViewModel(NavigationService navigation, AuthenticationService authentication,
                                    IQueryDispatcher queryDispatcher, ICommandDispatcher commandDispatcher,
                                    @Assisted Request request)
    {
        super(navigation, authentication, queryDispatcher, commandDispatcher);
        setRequest(request);
        queryDispatcher.Dispatch(Venue.class, new GetVenueById(request.venueId()))
                .ifPresent(this::setVenue);
    }
}