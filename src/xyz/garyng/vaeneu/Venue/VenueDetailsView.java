package xyz.garyng.vaeneu.Venue;

import com.jfoenix.controls.JFXRippler;
import de.saxsys.mvvmfx.FxmlView;
import de.saxsys.mvvmfx.InjectViewModel;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;
import org.fxmisc.easybind.EasyBind;
import xyz.garyng.vaeneu.Model.Venue;

import java.net.URL;
import java.util.ResourceBundle;

public class VenueDetailsView implements FxmlView<VenueDetailsViewModel>, Initializable
{

    public Label lblVenueName;
    public JFXRippler btnGoBack;
    public Label lblCapacity;
    public Label lblDescription;
    public ScrollPane scrollPane;
    @InjectViewModel
    private VenueDetailsViewModel _viewModel;

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        btnGoBack.visibleProperty().bind(_viewModel.CanGoBackProperty());
        lblVenueName.textProperty().bind(EasyBind.map(_viewModel.VenueProperty(), Venue::name));
        lblCapacity.textProperty().bind(EasyBind.map(_viewModel.VenueProperty(),
                v -> String.format("Capacity: %d", v.capacity())));
        lblDescription.textProperty().bind(EasyBind.map(_viewModel.VenueProperty(), Venue::description));
    }

    public void onGoBackButtonClicked(MouseEvent mouseEvent)
    {
        _viewModel.goBackCommand().execute();
    }
}