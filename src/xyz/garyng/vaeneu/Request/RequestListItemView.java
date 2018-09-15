package xyz.garyng.vaeneu.Request;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import de.saxsys.mvvmfx.FxmlView;
import de.saxsys.mvvmfx.InjectViewModel;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import org.fxmisc.easybind.EasyBind;
import xyz.garyng.vaeneu.Model.Request;
import xyz.garyng.vaeneu.Model.Venue;

import java.net.URL;
import java.util.ResourceBundle;

public class RequestListItemView implements FxmlView<RequestListItemViewModel>, Initializable
{

    public Label lblStatus;
    public Label lblVenueName;
    public FontAwesomeIconView iconStatus;
    public Label lblRequestId;
    @InjectViewModel
    private RequestListItemViewModel _viewModel;

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        // id
        iconStatus.glyphNameProperty().bind(EasyBind.map(_viewModel.RequestProperty(), this::mapStatusToIcon));
        lblStatus.textProperty().bind(EasyBind.map(_viewModel.RequestProperty(), r -> r.status().toString()));
        lblVenueName.textProperty().bind(EasyBind.map(_viewModel.VenueProperty(), Venue::name));
        lblRequestId.textProperty().bind(EasyBind.map(_viewModel.RequestProperty(), r -> String.format("Request #%d", r.id())));
    }

    private String mapStatusToIcon(Request request)
    {
        switch (request.status())
        {
            case Pending:
                return "QUESTION";
            case Accepted:
                return "CHECK";
            case Rejected:
                return "CLOSE";
            case Cancelled:
                return "ROTATE_LEFT";
            default:
                return "QUESTION";
        }
    }
}