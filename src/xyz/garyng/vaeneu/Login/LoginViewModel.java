package xyz.garyng.vaeneu.Login;

import com.google.inject.*;
import de.saxsys.mvvmfx.utils.commands.Action;
import de.saxsys.mvvmfx.utils.commands.Command;
import de.saxsys.mvvmfx.utils.commands.DelegateCommand;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import lombok.Getter;
import xyz.garyng.vaeneu.Command.ICommandDispatcher;
import xyz.garyng.vaeneu.Dashboard.DashboardViewModel;
import xyz.garyng.vaeneu.NavigationService;
import xyz.garyng.vaeneu.Query.IQueryDispatcher;
import xyz.garyng.vaeneu.Service.AuthenticationService;
import xyz.garyng.vaeneu.ViewModelBase;

public class LoginViewModel extends ViewModelBase
{
    // _usernameProperty
    private final StringProperty _usernameProperty = new SimpleStringProperty(this, "Username");

    public final StringProperty UsernameProperty()
    {
        return _usernameProperty;
    }

    public final String getUsername()
    {
        return _usernameProperty.get();
    }

    public final void setUsername(String value)
    {
        _usernameProperty.set(value);
    }

    // _passwordProperty
    private final StringProperty _passwordProperty = new SimpleStringProperty(this, "Password");

    public final StringProperty PasswordProperty()
    {
        return _passwordProperty;
    }

    public final String getPassword()
    {
        return _passwordProperty.get();
    }

    public final void setPassword(String value)
    {
        _passwordProperty.set(value);
    }

    // _loginErrorProperty
    private final BooleanProperty _loginErrorProperty = new SimpleBooleanProperty(this, "LoginError");

    public final BooleanProperty LoginErrorProperty()
    {
        return _loginErrorProperty;
    }

    public final boolean isLoginError()
    {
        return _loginErrorProperty.get();
    }

    public final void setLoginError(boolean value)
    {
        _loginErrorProperty.set(value);
    }

    @Getter
    private Command loginCommand;

    @Inject
    public LoginViewModel(NavigationService navigation, AuthenticationService authentication, IQueryDispatcher queryDispatcher, ICommandDispatcher commandDispatcher)
    {
        super(navigation, authentication, queryDispatcher, commandDispatcher);
        loginCommand = new DelegateCommand(this::onLogin, _usernameProperty.isNotEmpty().and(_passwordProperty.isNotEmpty()));
    }

    private Action onLogin()
    {
        return new Action()
        {
            @Override
            protected void action() throws Exception
            {
                boolean isAuthenticated = _authentication.Authenticate(getUsername(), getPassword());
                setLoginError(!isAuthenticated);
                if (isAuthenticated)
                {
                    _navigation.GoTo(DashboardViewModel.class);
                }
            }
        };
    }
}
