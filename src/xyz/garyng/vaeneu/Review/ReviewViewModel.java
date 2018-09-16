package xyz.garyng.vaeneu.Review;

import com.google.inject.Inject;
import de.saxsys.mvvmfx.utils.commands.Action;
import de.saxsys.mvvmfx.utils.commands.Command;
import de.saxsys.mvvmfx.utils.commands.DelegateCommand;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import lombok.Getter;
import xyz.garyng.vaeneu.Command.AcceptRequest;
import xyz.garyng.vaeneu.Command.ICommandDispatcher;
import xyz.garyng.vaeneu.Command.RejectRequest;
import xyz.garyng.vaeneu.Dialog.DialogParameters;
import xyz.garyng.vaeneu.Dialog.IDialogService;
import xyz.garyng.vaeneu.Factory.ViewModelsFactory;
import xyz.garyng.vaeneu.Model.Request;
import xyz.garyng.vaeneu.NavigationService;
import xyz.garyng.vaeneu.Query.GetAllPendingRequests;
import xyz.garyng.vaeneu.Query.IQueryDispatcher;
import xyz.garyng.vaeneu.Request.RequestListItemViewModel;
import xyz.garyng.vaeneu.Service.AuthenticationService;
import xyz.garyng.vaeneu.ViewModelBase;

import java.util.stream.Collectors;

public class ReviewViewModel extends ViewModelBase
{
    private final ViewModelsFactory _factory;
    private final IDialogService _dialogService;

    @Getter
    private ListProperty<RequestListItemViewModel> requestsProperty = new SimpleListProperty<>(this, "RequestsProperty", FXCollections.observableArrayList());

    @Getter
    private ListProperty<RequestListItemViewModel> selectedRequestsProperty = new SimpleListProperty<>(this, "SelectedRequestsProperty", FXCollections.observableArrayList());

    @Getter
    private final Command AcceptRequestCommand;
    @Getter
    private final Command RejectRequestsCommand;


    @Inject
    public ReviewViewModel(NavigationService navigation, AuthenticationService authentication,
                           IQueryDispatcher queryDispatcher, ICommandDispatcher commandDispatcher,
                           ViewModelsFactory factory, IDialogService dialogService)
    {
        super(navigation, authentication, queryDispatcher, commandDispatcher);
        _factory = factory;
        _dialogService = dialogService;
        AcceptRequestCommand = new DelegateCommand(this::onAcceptRequests);
        RejectRequestsCommand = new DelegateCommand(this::onRejectRequests);

        GetAllRequests();
    }

    private Action onRejectRequests()
    {
        return new Action()
        {
            @Override
            protected void action()
            {
                _dialogService.ShowConfirmationDialog(
                        DialogParameters.builder()
                                .body("Reject all selected requests?")
                                .onAccepted(this::onRejected)
                                .build());
            }

            private void onRejected()
            {
                selectedRequestsProperty.get()
                        .forEach(request ->
                                _commandDispatcher.Dispatch(RejectRequest
                                        .builder()
                                        .requestId(request.getRequest().id())
                                        .reviewerId(_authentication.CurrentUser().get().id())
                                        .build()));
                GetAllRequests();

            }
        };
    }

    private void GetAllRequests()
    {
        _queryDispatcher.DispatchList(Request.class,
                new GetAllPendingRequests())
                .map(venues ->
                        venues.stream()
                                .map(_factory::CreateRequestListItemViewModel)
                                .collect(Collectors.toList()))
                .ifPresent(requestsProperty::setAll);
    }

    private Action onAcceptRequests()
    {
        return new Action()
        {
            private void onAccepted()
            {
                selectedRequestsProperty.get()
                        .forEach(request ->
                                _commandDispatcher.Dispatch(AcceptRequest
                                        .builder()
                                        .requestId(request.getRequest().id())
                                        .reviewerId(_authentication.CurrentUser().get().id())
                                        .build()));
                GetAllRequests();
            }

            @Override
            protected void action()
            {
                _dialogService.ShowConfirmationDialog(
                        DialogParameters.builder()
                                .body("Accept all selected requests?")
                                .onAccepted(this::onAccepted)
                                .build());
            }
        };
    }
}