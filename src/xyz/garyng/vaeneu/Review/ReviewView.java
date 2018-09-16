package xyz.garyng.vaeneu.Review;

import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXNodesList;
import com.jfoenix.controls.JFXPopup;
import com.jfoenix.controls.JFXRippler;
import de.saxsys.mvvmfx.FluentViewLoader;
import de.saxsys.mvvmfx.FxmlView;
import de.saxsys.mvvmfx.InjectViewModel;
import de.saxsys.mvvmfx.ViewTuple;
import de.saxsys.mvvmfx.utils.viewlist.CachedViewModelCellFactory;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.SelectionMode;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Region;
import xyz.garyng.vaeneu.Popup.UserPopupView;
import xyz.garyng.vaeneu.Popup.UserPopupViewModel;
import xyz.garyng.vaeneu.Request.RequestListItemView;

import java.net.URL;
import java.util.ResourceBundle;

public class ReviewView implements FxmlView<ReviewViewModel>, Initializable
{

    public JFXRippler btnGoBack;
    public JFXRippler btnUser;
    public JFXListView lvRequests;
    public JFXNodesList nlAccept;
    public JFXNodesList nlReject;
    public JFXNodesList nlDetails;
    @InjectViewModel
    private ReviewViewModel _viewModel;
    private JFXPopup _popup;

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        lvRequests.setCellFactory(CachedViewModelCellFactory.createForFxmlView(RequestListItemView.class));
        lvRequests.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        lvRequests.itemsProperty().bind(_viewModel.requestsProperty());
        _viewModel.selectedRequestsProperty().set(lvRequests.getSelectionModel().getSelectedItems());
        _viewModel.SelectedRequestProperty().bind(lvRequests.getSelectionModel().selectedItemProperty());

        nlAccept.visibleProperty().bind(lvRequests.getSelectionModel().selectedItemProperty().isNotNull());
        nlReject.visibleProperty().bind(lvRequests.getSelectionModel().selectedItemProperty().isNotNull());
        nlDetails.visibleProperty().bind(lvRequests.getSelectionModel().selectedItemProperty().isNotNull());

        btnGoBack.visibleProperty().bind(_viewModel.CanGoBackProperty());
        btnUser.visibleProperty().bind(_viewModel.IsAuthenticatedProperty());
        ViewTuple<UserPopupView, UserPopupViewModel> popup = FluentViewLoader.fxmlView(UserPopupView.class).load();
        _popup = new JFXPopup((Region) popup.getView());
    }

    public void onGoBackButtonClicked(MouseEvent mouseEvent)
    {
        _viewModel.goBackCommand().execute();
    }

    public void onUserPopupClicked(MouseEvent mouseEvent)
    {
        _popup.show(btnUser, JFXPopup.PopupVPosition.TOP, JFXPopup.PopupHPosition.RIGHT);
    }

    public void onAcceptButtonClicked(ActionEvent actionEvent)
    {
        _viewModel.AcceptRequestCommand().execute();
    }
    public void onRejectButtonClicked(ActionEvent actionEvent)
    {
        _viewModel.RejectRequestsCommand().execute();
    }

    public void onDetailsButtonClicked(ActionEvent actionEvent)
    {
        _viewModel.GoToRequestDetailsCommand().execute();
    }
}