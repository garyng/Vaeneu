package xyz.garyng.vaeneu.Request;

import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXRippler;
import de.saxsys.mvvmfx.FxmlView;
import de.saxsys.mvvmfx.InjectViewModel;
import de.saxsys.mvvmfx.utils.viewlist.CachedViewModelCellFactory;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import xyz.garyng.vaeneu.Venue.VenueListItemView;

import java.net.URL;
import java.util.ResourceBundle;

public class SelectVenueView implements FxmlView<SelectVenueViewModel>, Initializable
{

    public JFXRippler btnGoBack;
    public JFXListView lvVenues;
    @InjectViewModel
    private SelectVenueViewModel _viewModel;

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        lvVenues.setCellFactory(CachedViewModelCellFactory.createForFxmlView(VenueListItemView.class));
        lvVenues.setItems(_viewModel.venuesProperty());
        _viewModel.SelectedVenueProperty().bind(lvVenues.getSelectionModel().selectedItemProperty());

        btnGoBack.visibleProperty().bind(_viewModel.CanGoBackProperty());
    }

    public void onGoBackButtonClicked(MouseEvent mouseEvent)
    {
        _viewModel.goBackCommand().execute();
    }
}