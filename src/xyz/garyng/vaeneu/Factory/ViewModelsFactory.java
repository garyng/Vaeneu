package xyz.garyng.vaeneu.Factory;

import xyz.garyng.vaeneu.Model.Request;
import xyz.garyng.vaeneu.Model.Venue;
import xyz.garyng.vaeneu.Request.RequestListItemViewModel;
import xyz.garyng.vaeneu.Venue.VenueListItemViewModel;

public interface ViewModelsFactory
{
    VenueListItemViewModel CreateVenueListItemViewModel(Venue venue);
    RequestListItemViewModel CreateRequestListItemViewModel(Request request);
}
