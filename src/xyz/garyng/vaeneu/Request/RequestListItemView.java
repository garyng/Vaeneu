package xyz.garyng.vaeneu.Request;

import de.saxsys.mvvmfx.FxmlView;
import de.saxsys.mvvmfx.InjectViewModel;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import org.fxmisc.easybind.EasyBind;

import java.net.URL;
import java.util.ResourceBundle;

public class RequestListItemView implements FxmlView<RequestListItemViewModel>, Initializable
{

    public Label lblStatus;
    @InjectViewModel
    private RequestListItemViewModel _viewModel;

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        // id
        lblStatus.textProperty().bind(EasyBind.map(_viewModel.RequestProperty(), r -> r.status().toString()));
    }
}