package xyz.garyng.vaeneu.Venue;

import de.saxsys.mvvmfx.FxmlView;
import de.saxsys.mvvmfx.InjectViewModel;
import javafx.beans.binding.Bindings;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.ResourceBundle;

public class VenueListItemView implements FxmlView<VenueListItemViewModel>, Initializable
{

    public Label lblName;
    public Label lblCapacity;

    @InjectViewModel
    private VenueListItemViewModel _viewModel;

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        lblName.textProperty().bind(_viewModel.NameProperty());
        lblCapacity.textProperty().bind(Bindings.createStringBinding(() -> String.format("Capacity: %d",
                _viewModel.CapacityProperty().get()), _viewModel.CapacityProperty()));
    }

    public void onClicked(MouseEvent mouseEvent)
    {
        // goto details
    }
}