package xyz.garyng.vaeneu.Venue;

import com.google.inject.Inject;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import xyz.garyng.vaeneu.Model.Venue;
import xyz.garyng.vaeneu.NavigationService;
import xyz.garyng.vaeneu.Service.AuthenticationService;
import xyz.garyng.vaeneu.Storage.IStorage;
import xyz.garyng.vaeneu.ViewModelBase;

import java.util.stream.Collectors;

public class VenueListViewModel extends ViewModelBase
{
    // SelectedVenueProperty
    private final ObjectProperty<VenueListItemViewModel> SelectedVenueProperty = new SimpleObjectProperty<>(this, "SelectedVenue");

    public final ObjectProperty<VenueListItemViewModel> SelectedVenueProperty()
    {
        return SelectedVenueProperty;
    }

    public final VenueListItemViewModel getSelectedVenue()
    {
        return SelectedVenueProperty.get();
    }

    public final void setSelectedVenue(Venue value)
    {
        SelectedVenueProperty.set(new VenueListItemViewModel(value));
    }

    private final ObservableList<VenueListItemViewModel> VenuesProperty = FXCollections.observableArrayList();

    public final ObservableList<VenueListItemViewModel> VenuesProperty()
    {
        return VenuesProperty;
    }

    private final IStorage<Venue> _storage;

    @Inject
    public VenueListViewModel(NavigationService navigation, AuthenticationService authentication, IStorage<Venue> storage)
    {
        super(navigation, authentication);
        _storage = storage;

        VenuesProperty.setAll(storage.Data()
                .stream()
                .map(VenueListItemViewModel::new)
                .collect(Collectors.toList()));
    }
}