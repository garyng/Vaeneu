package xyz.garyng.vaeneu.Dashboard;

import com.google.inject.Inject;
import de.saxsys.mvvmfx.utils.commands.Action;
import de.saxsys.mvvmfx.utils.commands.Command;
import de.saxsys.mvvmfx.utils.commands.DelegateCommand;
import xyz.garyng.vaeneu.NavigationService;
import xyz.garyng.vaeneu.Service.AuthenticationService;
import xyz.garyng.vaeneu.Venue.VenueListViewModel;
import xyz.garyng.vaeneu.ViewModelBase;

public class DashboardViewModel extends ViewModelBase
{
    private Command GoToVenuesCommand;

    @Inject
    public DashboardViewModel(NavigationService navigation, AuthenticationService authentication)
    {
        super(navigation, authentication);
        GoToVenuesCommand = new DelegateCommand(() -> new Action()
        {

            @Override
            protected void action() throws Exception
            {
                _navigation.GoTo(VenueListViewModel.class);
            }
        });
    }

    public Command getGoToVenuesCommand()
    {
        return GoToVenuesCommand;
    }

}