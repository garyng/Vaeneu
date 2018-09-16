package xyz.garyng.vaeneu.Request;

import com.jfoenix.controls.JFXRippler;
import de.saxsys.mvvmfx.FxmlView;
import de.saxsys.mvvmfx.InjectViewModel;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import org.fxmisc.easybind.EasyBind;
import xyz.garyng.vaeneu.Model.Venue;

import java.net.URL;
import java.util.ResourceBundle;

public class RequestDetailsView implements FxmlView<RequestDetailsViewModel>, Initializable
{

    public JFXRippler btnGoBack;
    public Label lblRequestId;
    public Label lblVenueName;
    public Label lblRequesterId;
    public Label lblDate;
    public Label lblTime;
    @InjectViewModel
    private RequestDetailsViewModel _viewModel;

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        lblRequestId.textProperty().bind(EasyBind.map(_viewModel.RequestProperty(), r -> String.format("Request #%d", r.id())));
        lblVenueName.textProperty().bind(EasyBind.map(_viewModel.VenueProperty(), Venue::name));
        lblRequesterId.textProperty().bind(EasyBind.map(_viewModel.RequestProperty(), r -> String.valueOf(r.requesterId())));
        lblDate.textProperty().bind(EasyBind.map(_viewModel.RequestProperty(), r -> String.format("%1$tY/%1$tm/%1$td (%1$ta)", r.startDateTime())));
        lblTime.textProperty().bind(EasyBind.map(_viewModel.RequestProperty(), r -> String.format("%1$tl:%1$tM %1$Tp - %2$tl:%2$tM %2$Tp", r.startDateTime(), r.endDateTime())));


        btnGoBack.visibleProperty().bind(_viewModel.CanGoBackProperty());
    }

    public void onGoBackButtonClicked(MouseEvent mouseEvent)
    {
        _viewModel.goBackCommand().execute();
    }

    public void onVenueClicked(MouseEvent mouseEvent)
    {
        _viewModel.GoToVenueDetailsCommand().execute();
    }
}