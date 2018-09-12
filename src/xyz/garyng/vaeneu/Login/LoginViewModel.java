package xyz.garyng.vaeneu.Login;

import com.google.inject.Inject;
import de.saxsys.mvvmfx.ViewModel;
import de.saxsys.mvvmfx.utils.commands.Action;
import de.saxsys.mvvmfx.utils.commands.Command;
import de.saxsys.mvvmfx.utils.commands.DelegateCommand;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import xyz.garyng.vaeneu.Model.User;
import xyz.garyng.vaeneu.Service.AuthenticationService;
import xyz.garyng.vaeneu.Storage.IStorage;

public class LoginViewModel implements ViewModel
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

    private Command _loginCommand;

    public Command getLoginCommand()
    {
        return _loginCommand;
    }

    private final AuthenticationService _auth;

    @Inject
    public LoginViewModel(AuthenticationService auth)
    {
        _auth = auth;
        _loginCommand = new DelegateCommand(this::onLogin, _usernameProperty.isNotEmpty().and(_passwordProperty.isNotEmpty()));
    }

    private Action onLogin()
    {
        return new Action()
        {
            @Override
            protected void action() throws Exception
            {
                boolean isAuthenticated = _auth.Authenticate(getUsername(), getPassword());
                setLoginError(!isAuthenticated);
            }
        };
    }
}
