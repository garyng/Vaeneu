package xyz.garyng.vaeneu;

import de.saxsys.mvvmfx.ViewModel;
import de.saxsys.mvvmfx.utils.commands.Action;
import de.saxsys.mvvmfx.utils.commands.Command;
import de.saxsys.mvvmfx.utils.commands.DelegateCommand;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import xyz.garyng.vaeneu.Command.ICommandDispatcher;
import xyz.garyng.vaeneu.Model.User;
import xyz.garyng.vaeneu.Query.IQueryDispatcher;
import xyz.garyng.vaeneu.Service.AuthenticationService;

public class ViewModelBase implements ViewModel
{
    protected final NavigationService _navigation;
    protected final AuthenticationService _authentication;
    protected final IQueryDispatcher _queryDispatcher;
    protected final ICommandDispatcher _commandDispatcher;

    // CanGoBackProperty
    protected final BooleanProperty CanGoBackProperty = new SimpleBooleanProperty(this, "CanGoBack");

    public final BooleanProperty CanGoBackProperty()
    {
        return CanGoBackProperty;
    }

    public final boolean canGoBack()
    {
        return CanGoBackProperty.get();
    }

    public final void setCanGoBack(boolean value)
    {
        CanGoBackProperty.set(value);
    }


    // IsAuthenticatedProperty
    protected final BooleanProperty IsAuthenticatedProperty = new SimpleBooleanProperty(this, "IsAuthenticated");

    public final BooleanProperty IsAuthenticatedProperty()
    {
        return IsAuthenticatedProperty;
    }

    public final boolean isAuthenticated()
    {
        return IsAuthenticatedProperty.get();
    }

    public final void setIsAuthenticated(boolean value)
    {
        IsAuthenticatedProperty.set(value);
    }


    // CurrentUserProperty
    private final ObjectProperty<User> CurrentUserProperty = new SimpleObjectProperty<>(this, "CurrentUser");

    public final ObjectProperty<User> CurrentUserProperty()
    {
        return CurrentUserProperty;
    }

    public final User getCurrentUser()
    {
        return CurrentUserProperty.get();
    }

    public final void setCurrentUser(User value)
    {
        CurrentUserProperty.set(value);
    }

    private final Command GoBackCommand;

    public Command getGoBackCommand()
    {
        return GoBackCommand;
    }

    public ViewModelBase(NavigationService navigation, AuthenticationService authentication,
                         IQueryDispatcher queryDispatcher, ICommandDispatcher commandDispatcher)
    {
        _navigation = navigation;
        _authentication = authentication;
        _queryDispatcher = queryDispatcher;
        _commandDispatcher = commandDispatcher;
        CanGoBackProperty.bind(_navigation.CanGoBackProperty());
        setIsAuthenticated(_authentication.IsAuthenticated());
        _authentication.CurrentUser().ifPresent(this::setCurrentUser);
        GoBackCommand = new DelegateCommand(() -> new Action()
        {

            @Override
            protected void action() throws Exception
            {
                _navigation.GoBack();
            }
        });
    }
}
