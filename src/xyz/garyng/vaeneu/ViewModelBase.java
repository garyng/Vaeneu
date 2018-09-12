package xyz.garyng.vaeneu;

import de.saxsys.mvvmfx.ViewModel;
import xyz.garyng.vaeneu.NavigationService;

public class ViewModelBase implements ViewModel
{
    protected final NavigationService _navigation;

    public ViewModelBase(NavigationService navigation)
    {
        _navigation = navigation;
    }
}
