package xyz.garyng.vaeneu.Request;

import com.google.inject.Inject;
import de.saxsys.mvvmfx.utils.commands.Action;
import de.saxsys.mvvmfx.utils.commands.Command;
import de.saxsys.mvvmfx.utils.commands.DelegateCommand;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lombok.Getter;
import xyz.garyng.vaeneu.Command.CancelRequestById;
import xyz.garyng.vaeneu.Command.ICommandDispatcher;
import xyz.garyng.vaeneu.Dialog.DialogParameters;
import xyz.garyng.vaeneu.Dialog.IDialogService;
import xyz.garyng.vaeneu.Factory.ViewModelsFactory;
import xyz.garyng.vaeneu.Model.Request;
import xyz.garyng.vaeneu.Model.Venue;
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

    private final IDialogService _dialogService;
    private final ViewModelsFactory _factory;

    @Getter
    private final Command cancelRequestCommand;
    @Getter
    private final Command addRequestCommand;

    @Inject
    public RequestListViewModel(NavigationService navigation, AuthenticationService authentication,
                                IQueryDispatcher queryDispatcher, ICommandDispatcher commandDispatcher,
                                IDialogService dialogService, ViewModelsFactory factory)
    {
        super(navigation, authentication, queryDispatcher, commandDispatcher);
        _dialogService = dialogService;
        _factory = factory;
        cancelRequestCommand = new DelegateCommand(this::onCancelRequest);
        addRequestCommand = new DelegateCommand(this::onAddRequest);
        GetAllRequests();
    }

    private Action onAddRequest()
    {
        return new Action()
        {
            @Override
            protected void action()
            {
                _navigation.GoTo(SelectVenueViewModel.class);
            }
        };
    }

    private void GetAllRequests()
    {
        _queryDispatcher.DispatchList(Request.class,
                GetAllRequest.builder().userId(_authentication.CurrentUser().get().id()).build())
                .map(venues ->
                        venues.stream()
                                .map(_factory::CreateRequestListItemViewModel)
                                .collect(Collectors.toList()))
                .ifPresent(requestsProperty::setAll);
    }

    private Action onCancelRequest()
    {
        return new Action()
        {
            @Override
            protected void action()
            {
                Request r = getSelectedRequest().getRequest();
                Venue v = getSelectedRequest().getVenue();
                _dialogService.ShowConfirmationDialog(DialogParameters.builder()
                        .body(String.format("Cancel your request for %s (#%d)?", v.name(), r.id()))
                        .onAccepted(() ->
                        {
                            _commandDispatcher.Dispatch(CancelRequestById.builder().id(r.id()).build());
                            GetAllRequests();
                        })
                        .build());
            }
        };
    }

}