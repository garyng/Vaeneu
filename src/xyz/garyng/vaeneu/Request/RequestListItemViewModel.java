package xyz.garyng.vaeneu.Request;

import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import xyz.garyng.vaeneu.Command.ICommandDispatcher;
import xyz.garyng.vaeneu.Model.Request;
import xyz.garyng.vaeneu.NavigationService;
import xyz.garyng.vaeneu.Query.IQueryDispatcher;
import xyz.garyng.vaeneu.Service.AuthenticationService;
import xyz.garyng.vaeneu.ViewModelBase;

public class RequestListItemViewModel extends ViewModelBase
{
    // RequestProperty
    private final ObjectProperty<Request> RequestProperty = new SimpleObjectProperty<>(this, "Request");

    public final ObjectProperty<Request> RequestProperty()
    {
        return RequestProperty;
    }

    public final Request getRequest()
    {
        return RequestProperty.get();
    }

    public final void setRequest(Request value)
    {
        RequestProperty.set(value);
    }


    @Inject
    public RequestListItemViewModel(NavigationService navigation, AuthenticationService authentication,
                                    IQueryDispatcher queryDispatcher, ICommandDispatcher commandDispatcher,
                                    @Assisted Request request)
    {
        super(navigation, authentication, queryDispatcher, commandDispatcher);
        setRequest(request);
    }
}