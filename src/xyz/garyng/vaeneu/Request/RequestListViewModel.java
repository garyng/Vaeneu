package xyz.garyng.vaeneu.Request;

import com.google.inject.Inject;
import xyz.garyng.vaeneu.Command.ICommandDispatcher;
import xyz.garyng.vaeneu.NavigationService;
import xyz.garyng.vaeneu.Query.IQueryDispatcher;
import xyz.garyng.vaeneu.Service.AuthenticationService;
import xyz.garyng.vaeneu.ViewModelBase;

public class RequestListViewModel extends ViewModelBase
{
    @Inject
    public RequestListViewModel(NavigationService navigation, AuthenticationService authentication, IQueryDispatcher queryDispatcher, ICommandDispatcher commandDispatcher)
    {
        super(navigation, authentication, queryDispatcher, commandDispatcher);
    }
}