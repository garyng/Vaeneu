package xyz.garyng.vaeneu.Venue;

import com.google.inject.Inject;
import de.saxsys.mvvmfx.utils.commands.Action;
import de.saxsys.mvvmfx.utils.commands.Command;
import de.saxsys.mvvmfx.utils.commands.DelegateCommand;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import lombok.Getter;
import xyz.garyng.vaeneu.Command.ICommandDispatcher;
import xyz.garyng.vaeneu.Model.Venue;
import xyz.garyng.vaeneu.NavigationService;
import xyz.garyng.vaeneu.Query.GetVenueById;
import xyz.garyng.vaeneu.Query.IQueryDispatcher;
import xyz.garyng.vaeneu.Request.SelectDateTimeViewModel;
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

    @Getter
    private final Command addRequestCommand;


    @Inject
    public VenueDetailsViewModel(NavigationService navigation, AuthenticationService authentication,
                                 IQueryDispatcher queryDispatcher, ICommandDispatcher commandDispatcher)
    {
        super(navigation, authentication, queryDispatcher, commandDispatcher);
        addRequestCommand = new DelegateCommand(() -> new Action()
        {
            @Override
            protected void action()
            {
                _navigation.GoTo(SelectDateTimeViewModel.class, vm -> vm.setSelectedVenue(getVenue()));
            }
        });
    }
}