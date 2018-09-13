package xyz.garyng.vaeneu.Venue;

import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;
import com.google.inject.assistedinject.AssistedInject;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import xyz.garyng.vaeneu.Model.Venue;
import xyz.garyng.vaeneu.NavigationService;
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

    // private final Command GoToVenueDetailsCommand;

    @Inject
    public VenueListItemViewModel(NavigationService navigation, AuthenticationService authentication, @Assisted Venue venue)
    {
        super(navigation, authentication);
        setName(venue.name());
        setDescription(venue.description());
        setCapacity(venue.capacity());
    }
}