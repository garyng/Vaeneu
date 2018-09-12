package xyz.garyng.vaeneu.Dashboard;

import com.google.inject.Inject;
import xyz.garyng.vaeneu.NavigationService;
import xyz.garyng.vaeneu.Service.AuthenticationService;
import xyz.garyng.vaeneu.ViewModelBase;

public class DashboardViewModel extends ViewModelBase
{
    @Inject
    public DashboardViewModel(NavigationService navigation, AuthenticationService authentication)
    {
        super(navigation, authentication);
    }

}