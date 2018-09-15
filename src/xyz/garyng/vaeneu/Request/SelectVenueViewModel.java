package xyz.garyng.vaeneu.Request;

import com.google.inject.Inject;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lombok.Getter;
import xyz.garyng.vaeneu.Command.ICommandDispatcher;
import xyz.garyng.vaeneu.Factory.ViewModelsFactory;
import xyz.garyng.vaeneu.Model.Venue;
import xyz.garyng.vaeneu.NavigationService;
import xyz.garyng.vaeneu.Query.GetAllVenue;
import xyz.garyng.vaeneu.Query.IQueryDispatcher;
import xyz.garyng.vaeneu.Service.AuthenticationService;
import xyz.garyng.vaeneu.Venue.VenueListItemViewModel;
import xyz.garyng.vaeneu.Venue.VenueListViewModel;
import xyz.garyng.vaeneu.ViewModelBase;

import java.util.stream.Collectors;

public class SelectVenueViewModel extends ViewModelBase
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

    public final void setSelectedVenue(VenueListItemViewModel value)
    {
        SelectedVenueProperty.set(value);
    }

    @Getter
    private final ObservableList<VenueListItemViewModel> venuesProperty = FXCollections.observableArrayList();

    private final ViewModelsFactory _factory;

    @Inject
    public SelectVenueViewModel(NavigationService navigation, AuthenticationService authentication,
                                IQueryDispatcher queryDispatcher, ICommandDispatcher commandDispatcher,
                                ViewModelsFactory factory)
    {
        super(navigation, authentication, queryDispatcher, commandDispatcher);
        _factory = factory;

        _queryDispatcher.DispatchList(Venue.class, new GetAllVenue())
                .map(venues ->
                        venues.stream()
                                .map(_factory::CreateVenueListItemViewModel)
                                .collect(Collectors.toList()))
                .ifPresent(venuesProperty::setAll);
    }
}