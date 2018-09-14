package xyz.garyng.vaeneu.Venue;

import com.google.inject.Inject;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import xyz.garyng.vaeneu.Command.ICommandDispatcher;
import xyz.garyng.vaeneu.Factory.ViewModelsFactory;
import xyz.garyng.vaeneu.Model.Venue;
import xyz.garyng.vaeneu.NavigationService;
import xyz.garyng.vaeneu.Query.GetAllVenue;
import xyz.garyng.vaeneu.Query.IQueryDispatcher;
import xyz.garyng.vaeneu.Service.AuthenticationService;
import xyz.garyng.vaeneu.ViewModelBase;

import java.util.List;
import java.util.Optional;
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
        SelectedVenueProperty.set(_factory.CreateVenueListItemViewModel(value));
    }

    private final ObservableList<VenueListItemViewModel> VenuesProperty = FXCollections.observableArrayList();

    public final ObservableList<VenueListItemViewModel> VenuesProperty()
    {
        return VenuesProperty;
    }

    private final ViewModelsFactory _factory;

    @Inject
    public VenueListViewModel(NavigationService navigation, AuthenticationService authentication,
                              IQueryDispatcher queryDispatcher, ICommandDispatcher commandDispatcher,
                              ViewModelsFactory factory)
    {
        super(navigation, authentication, queryDispatcher, commandDispatcher);
        _factory = factory;

        Optional<List<Venue>> result = _queryDispatcher.DispatchList(Venue.class, new GetAllVenue());
        result.map(venues ->
                venues.stream()
                        .map(_factory::CreateVenueListItemViewModel)
                        .collect(Collectors.toList()))
                .ifPresent(VenuesProperty::setAll);
    }
}