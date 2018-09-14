package xyz.garyng.vaeneu.Request;

import com.jfoenix.controls.JFXPopup;
import com.jfoenix.controls.JFXRippler;
import de.saxsys.mvvmfx.FluentViewLoader;
import de.saxsys.mvvmfx.FxmlView;
import de.saxsys.mvvmfx.InjectViewModel;
import de.saxsys.mvvmfx.ViewTuple;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Region;
import xyz.garyng.vaeneu.Popup.UserPopupView;
import xyz.garyng.vaeneu.Popup.UserPopupViewModel;

import java.net.URL;
import java.util.ResourceBundle;

public class RequestListView implements FxmlView<RequestListViewModel>, Initializable
{

    public JFXRippler btnGoBack;
    public JFXRippler btnUser;
    private JFXPopup _popup;

    @InjectViewModel
    private RequestListViewModel _viewModel;

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        btnGoBack.visibleProperty().bind(_viewModel.CanGoBackProperty());
        btnUser.visibleProperty().bind(_viewModel.IsAuthenticatedProperty());
        ViewTuple<UserPopupView, UserPopupViewModel> popup = FluentViewLoader.fxmlView(UserPopupView.class).load();
        _popup = new JFXPopup((Region) popup.getView());
    }

    public void onUserPopupClicked(MouseEvent mouseEvent)
    {
        _popup.show(btnUser, JFXPopup.PopupVPosition.TOP, JFXPopup.PopupHPosition.RIGHT);
    }

    public void onGoBackButtonClicked(MouseEvent mouseEvent)
    {
        _viewModel.goBackCommand().execute();
    }
}