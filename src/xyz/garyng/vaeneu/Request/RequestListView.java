package xyz.garyng.vaeneu.Request;

import com.jfoenix.controls.*;
import de.saxsys.mvvmfx.FluentViewLoader;
import de.saxsys.mvvmfx.FxmlView;
import de.saxsys.mvvmfx.InjectViewModel;
import de.saxsys.mvvmfx.ViewTuple;
import de.saxsys.mvvmfx.utils.viewlist.CachedViewModelCellFactory;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.BooleanBinding;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import org.fxmisc.easybind.EasyBind;
import xyz.garyng.vaeneu.Model.RequestStatus;
import xyz.garyng.vaeneu.Popup.UserPopupView;
import xyz.garyng.vaeneu.Popup.UserPopupViewModel;

import javax.swing.text.html.ListView;
import java.net.URL;
import java.util.ResourceBundle;

public class RequestListView implements FxmlView<RequestListViewModel>, Initializable
{

    public JFXRippler btnGoBack;
    public JFXRippler btnUser;
    public JFXListView lvRequests;
    public JFXNodesList nlDetails;
    public JFXNodesList nlAdd;
    public JFXButton btnCancelRequest;
    private JFXPopup _popup;

    @InjectViewModel
    private RequestListViewModel _viewModel;

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        lvRequests.setCellFactory(CachedViewModelCellFactory.createForFxmlView(RequestListItemView.class));
        lvRequests.setItems(_viewModel.requestsProperty());

        _viewModel.SelectedRequestProperty().bind(lvRequests.getSelectionModel().selectedItemProperty());


        nlDetails.visibleProperty().bind(lvRequests.getSelectionModel().selectedItemProperty().isNotNull());
        nlAdd.visibleProperty().bind(lvRequests.getSelectionModel().selectedItemProperty().isNull());

        btnCancelRequest.disableProperty().bind(EasyBind.map(_viewModel.SelectedRequestProperty(), r -> !r.getRequest().status().equals(RequestStatus.Pending)));

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

    public void onAddRequestButtonClicked(ActionEvent actionEvent)
    {
        closeNodeList();
        _viewModel.addRequestCommand().execute();
    }

    public void onCancelRequestButtonClicked(ActionEvent actionEvent)
    {
        closeNodeList();
        _viewModel.cancelRequestCommand().execute();
    }

    public void onShowDetailsButtonClicked(ActionEvent actionEvent)
    {
        closeNodeList();
    }

    private void closeNodeList()
    {
        nlAdd.animateList(false);
        nlDetails.animateList(false);
    }
}