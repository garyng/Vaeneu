package xyz.garyng.vaeneu.Venue;

import de.saxsys.mvvmfx.ViewModel;
import xyz.garyng.vaeneu.Model.Venue;

public class VenueListItemViewModel implements ViewModel
{
    private Venue _venue;

    public VenueListItemViewModel(Venue venue)
    {
        _venue = venue;
    }
}