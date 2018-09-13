package xyz.garyng.vaeneu.Popup;

import de.saxsys.mvvmfx.FxmlView;
import de.saxsys.mvvmfx.InjectViewModel;
import javafx.beans.binding.Bindings;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.ResourceBundle;

public class UserPopupView implements FxmlView<UserPopupViewModel>, Initializable
{

    public Label lblUsername;
    @InjectViewModel
    private UserPopupViewModel _viewModel;

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        lblUsername.textProperty().bind(Bindings.createStringBinding(() -> _viewModel.CurrentUserProperty().get().userName(), _viewModel.CurrentUserProperty()));
    }

    public void onLogoutClicked(MouseEvent mouseEvent)
    {
        _viewModel.getLogoutCommand().execute();
    }
}