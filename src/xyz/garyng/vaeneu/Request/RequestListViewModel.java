package xyz.garyng.vaeneu.Request;

import com.google.inject.Inject;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lombok.Getter;
import xyz.garyng.vaeneu.Command.ICommandDispatcher;
import xyz.garyng.vaeneu.Factory.ViewModelsFactory;
import xyz.garyng.vaeneu.Model.Request;
import xyz.garyng.vaeneu.NavigationService;
import xyz.garyng.vaeneu.Query.GetAllRequest;
import xyz.garyng.vaeneu.Query.IQueryDispatcher;
import xyz.garyng.vaeneu.Service.AuthenticationService;
import xyz.garyng.vaeneu.ViewModelBase;

import java.util.stream.Collectors;

public class RequestListViewModel extends ViewModelBase
{

    @Getter
    private final ObservableList<RequestListItemViewModel> requestsProperty = FXCollections.observableArrayList();

    // SelectedRequestProperty
    private final ObjectProperty<RequestListItemViewModel> SelectedRequestProperty = new SimpleObjectProperty<>(this, "SelectedRequest");

    public final ObjectProperty<RequestListItemViewModel> SelectedRequestProperty()
    {
        return SelectedRequestProperty;
    }

    public final RequestListItemViewModel getSelectedRequest()
    {
        return SelectedRequestProperty.get();
    }

    public final void setSelectedRequest(RequestListItemViewModel value)
    {
        SelectedRequestProperty.set(value);
    }

    private final ViewModelsFactory _factory;

    @Inject
    public RequestListViewModel(NavigationService navigation, AuthenticationService authentication,
                                IQueryDispatcher queryDispatcher, ICommandDispatcher commandDispatcher,
                                ViewModelsFactory factory)
    {
        super(navigation, authentication, queryDispatcher, commandDispatcher);
        _factory = factory;

        GetAllRequests();
    }

    private void GetAllRequests()
    {
        _queryDispatcher.DispatchList(Request.class,
                new GetAllRequest())
                .map(venues ->
                        venues.stream()
                                .map(_factory::CreateRequestListItemViewModel)
                                .collect(Collectors.toList()))
                .ifPresent(requestsProperty::setAll);
    }
}