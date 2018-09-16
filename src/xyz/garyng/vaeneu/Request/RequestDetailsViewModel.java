package xyz.garyng.vaeneu.Request;

import com.google.inject.Inject;
import de.saxsys.mvvmfx.utils.commands.Action;
import de.saxsys.mvvmfx.utils.commands.Command;
import de.saxsys.mvvmfx.utils.commands.DelegateCommand;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import lombok.Getter;
import xyz.garyng.vaeneu.Command.ICommandDispatcher;
import xyz.garyng.vaeneu.Model.Request;
import xyz.garyng.vaeneu.Model.Venue;
import xyz.garyng.vaeneu.NavigationService;
import xyz.garyng.vaeneu.Query.GetVenueById;
import xyz.garyng.vaeneu.Query.IQueryDispatcher;
import xyz.garyng.vaeneu.Service.AuthenticationService;
import xyz.garyng.vaeneu.Venue.VenueDetailsViewModel;
import xyz.garyng.vaeneu.ViewModelBase;

public class RequestDetailsViewModel extends ViewModelBase
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
        OnRequestChanged();
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


    @Getter
    private final Command GoToVenueDetailsCommand;

    @Inject
    public RequestDetailsViewModel(NavigationService navigation, AuthenticationService authentication,
                                   IQueryDispatcher queryDispatcher, ICommandDispatcher commandDispatcher)
    {
        super(navigation, authentication, queryDispatcher, commandDispatcher);
        GoToVenueDetailsCommand = new DelegateCommand(() -> new Action()
        {
            @Override
            protected void action()
            {
                _navigation.GoTo(VenueDetailsViewModel.class, vm ->
                {
                    vm.setVenueId(getVenue().id());
                    vm.setDisableRequesting(true);
                });
            }
        });
    }

    private void OnRequestChanged()
    {
        Request request = getRequest();
        Venue venue = _queryDispatcher.Dispatch(Venue.class, new GetVenueById(request.venueId())).get();
        setVenue(venue);
    }
}