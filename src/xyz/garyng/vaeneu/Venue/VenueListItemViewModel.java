package xyz.garyng.vaeneu.Venue;

import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;
import com.google.inject.assistedinject.AssistedInject;
import de.saxsys.mvvmfx.utils.commands.Action;
import de.saxsys.mvvmfx.utils.commands.Command;
import de.saxsys.mvvmfx.utils.commands.DelegateCommand;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import xyz.garyng.vaeneu.Command.ICommandDispatcher;
import xyz.garyng.vaeneu.Model.Venue;
import xyz.garyng.vaeneu.NavigationService;
import xyz.garyng.vaeneu.Query.IQueryDispatcher;
import xyz.garyng.vaeneu.Service.AuthenticationService;
import xyz.garyng.vaeneu.ViewModelBase;

public class VenueListItemViewModel extends ViewModelBase
{
    // NameProperty
    private final StringProperty NameProperty = new SimpleStringProperty(this, "Name");

    public final StringProperty NameProperty()
    {
        return NameProperty;
    }

    public final String getName()
    {
        return NameProperty.get();
    }

    public final void setName(String value)
    {
        NameProperty.set(value);
    }

    // DescriptionProperty
    private final StringProperty DescriptionProperty = new SimpleStringProperty(this, "Description");

    public final StringProperty DescriptionProperty()
    {
        return DescriptionProperty;
    }

    public final String getDescription()
    {
        return DescriptionProperty.get();
    }

    public final void setDescription(String value)
    {
        DescriptionProperty.set(value);
    }

    // CapacityProperty
    private final IntegerProperty CapacityProperty = new SimpleIntegerProperty(this, "Capacity");

    public final IntegerProperty CapacityProperty()
    {
        return CapacityProperty;
    }

    public final int getCapacity()
    {
        return CapacityProperty.get();
    }

    public final void setCapacity(int value)
    {
        CapacityProperty.set(value);
    }

    public Command getGoToVenueDetailsCommand()
    {
        return GoToVenueDetailsCommand;
    }

    private final Command GoToVenueDetailsCommand;

    @Inject
    public VenueListItemViewModel(NavigationService navigation, AuthenticationService authentication,
                                  IQueryDispatcher queryDispatcher, ICommandDispatcher commandDispatcher,
                                  @Assisted Venue venue)
    {
        super(navigation, authentication, queryDispatcher, commandDispatcher);
        setName(venue.name());
        setDescription(venue.description());
        setCapacity(venue.capacity());

        GoToVenueDetailsCommand = new DelegateCommand(() -> new Action()
        {
            @Override
            protected void action()
            {
                _navigation.GoTo(VenueDetailsViewModel.class, vm ->
                        vm.setVenueId(venue.id()));
            }
        });
    }
}