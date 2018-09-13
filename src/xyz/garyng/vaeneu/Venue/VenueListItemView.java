package xyz.garyng.vaeneu.Venue;

import de.saxsys.mvvmfx.FxmlView;
import de.saxsys.mvvmfx.InjectViewModel;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

public class VenueListItemView implements FxmlView<VenueListItemViewModel>, Initializable
{

    @InjectViewModel
    private VenueListItemViewModel _viewModel;

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {

    }
}