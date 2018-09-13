package xyz.garyng.vaeneu.Popup;

import com.google.inject.Inject;
import de.saxsys.mvvmfx.utils.commands.Action;
import de.saxsys.mvvmfx.utils.commands.Command;
import de.saxsys.mvvmfx.utils.commands.DelegateCommand;
import xyz.garyng.vaeneu.Command.ICommandDispatcher;
import xyz.garyng.vaeneu.Login.LoginViewModel;
import xyz.garyng.vaeneu.NavigationService;
import xyz.garyng.vaeneu.Query.IQueryDispatcher;
import xyz.garyng.vaeneu.Service.AuthenticationService;
import xyz.garyng.vaeneu.ViewModelBase;

public class UserPopupViewModel extends ViewModelBase
{
    private Command _logoutCommand;

    public Command getLogoutCommand()
    {
        return _logoutCommand;
    }

    @Inject
    public UserPopupViewModel(NavigationService navigation, AuthenticationService authentication, IQueryDispatcher queryDispatcher, ICommandDispatcher commandDispatcher)
    {
        super(navigation, authentication, queryDispatcher, commandDispatcher);
        _logoutCommand = new DelegateCommand(this::onLogout, this.isAuthenticated());
    }

    private Action onLogout()
    {
        return new Action()
        {

            @Override
            protected void action()
            {
                _authentication.DeAuthenticate();
                _navigation.Clear();
                _navigation.GoTo(LoginViewModel.class, vm ->
                {
                }, false);
            }
        };
    }
}