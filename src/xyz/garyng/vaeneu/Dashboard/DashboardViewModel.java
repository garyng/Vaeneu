package xyz.garyng.vaeneu.Dashboard;

import com.google.inject.Inject;
import de.saxsys.mvvmfx.utils.commands.Action;
import de.saxsys.mvvmfx.utils.commands.Command;
import de.saxsys.mvvmfx.utils.commands.DelegateCommand;
import lombok.Getter;
import xyz.garyng.vaeneu.Command.ICommandDispatcher;
import xyz.garyng.vaeneu.NavigationService;
import xyz.garyng.vaeneu.Query.IQueryDispatcher;
import xyz.garyng.vaeneu.Request.RequestListViewModel;
import xyz.garyng.vaeneu.Service.AuthenticationService;
import xyz.garyng.vaeneu.Venue.VenueListViewModel;
import xyz.garyng.vaeneu.ViewModelBase;

public class DashboardViewModel extends ViewModelBase
{
    @Getter
    private Command goToVenueListCommand;
    @Getter
    private Command gotoRequestListComand;

    @Inject
    public DashboardViewModel(NavigationService navigation, AuthenticationService authentication, IQueryDispatcher queryDispatcher, ICommandDispatcher commandDispatcher)
    {
        super(navigation, authentication, queryDispatcher, commandDispatcher);
        goToVenueListCommand = new DelegateCommand(() -> new Action()
        {

            @Override
            protected void action()
            {
                _navigation.GoTo(VenueListViewModel.class);
            }
        });
        gotoRequestListComand = new DelegateCommand(() -> new Action()
        {
            @Override
            protected void action()
            {
                _navigation.GoTo(RequestListViewModel.class);
            }
        });
    }

}