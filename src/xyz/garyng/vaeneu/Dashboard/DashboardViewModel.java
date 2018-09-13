package xyz.garyng.vaeneu.Dashboard;

import com.google.inject.Inject;
import de.saxsys.mvvmfx.utils.commands.Action;
import de.saxsys.mvvmfx.utils.commands.Command;
import de.saxsys.mvvmfx.utils.commands.DelegateCommand;
import xyz.garyng.vaeneu.Command.ICommandDispatcher;
import xyz.garyng.vaeneu.NavigationService;
import xyz.garyng.vaeneu.Query.IQueryDispatcher;
import xyz.garyng.vaeneu.Service.AuthenticationService;
import xyz.garyng.vaeneu.Venue.VenueListViewModel;
import xyz.garyng.vaeneu.ViewModelBase;

public class DashboardViewModel extends ViewModelBase
{
    private Command GoToVenueListCommand;

    @Inject
    public DashboardViewModel(NavigationService navigation, AuthenticationService authentication, IQueryDispatcher queryDispatcher, ICommandDispatcher commandDispatcher)
    {
        super(navigation, authentication, queryDispatcher, commandDispatcher);
        GoToVenueListCommand = new DelegateCommand(() -> new Action()
        {

            @Override
            protected void action() throws Exception
            {
                _navigation.GoTo(VenueListViewModel.class);
            }
        });
    }

    public Command getGoToVenueListCommand()
    {
        return GoToVenueListCommand;
    }

}