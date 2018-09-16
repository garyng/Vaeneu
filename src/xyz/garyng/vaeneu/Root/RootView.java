package xyz.garyng.vaeneu.Root;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import de.saxsys.mvvmfx.FxmlView;
import de.saxsys.mvvmfx.InjectViewModel;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;

import java.net.URL;
import java.util.ResourceBundle;

public class RootView implements FxmlView<RootViewModel>, Initializable
{

    public AnchorPane contentPane;

    public Label lblTitle;
    public Label lblBody;
    public JFXButton btnAccept;
    public JFXButton btnReject;
    public StackPane root;
    public JFXDialogLayout rootDialogLayout;
    @InjectViewModel
    private RootViewModel _viewModel;

    private JFXDialog _currentDialog;

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        _viewModel.ContentProperty().addListener((observable, oldValue, newValue) ->
        {
            AnchorPane.setBottomAnchor(newValue, 0.0);
            AnchorPane.setTopAnchor(newValue, 0.0);
            AnchorPane.setRightAnchor(newValue, 0.0);
            AnchorPane.setLeftAnchor(newValue, 0.0);
            contentPane.getChildren().setAll(newValue);
        });
        _viewModel.RegisterOnShowDialog(() ->
        {
            _currentDialog = new JFXDialog();
            _currentDialog.setContent(rootDialogLayout);
            _currentDialog.show(root);
        });
        lblTitle.textProperty().bind(_viewModel.DialogTitleProperty());
        lblBody.textProperty().bind(_viewModel.DialogBodyProperty());
        btnAccept.textProperty().bind(_viewModel.DialogAcceptedTextProperty());
        btnReject.textProperty().bind(_viewModel.DialogRejectedTextProperty());
        btnReject.visibleProperty().bind(_viewModel.IsConfirmationDialogProperty());
        btnReject.managedProperty().bind(_viewModel.IsConfirmationDialogProperty());
    }

    public void onAcceptButtonClicked(ActionEvent actionEvent)
    {
        _currentDialog.close();
        _viewModel.onAcceptedCommand().execute();
    }

    public void onRejectButtonClicked(ActionEvent actionEvent)
    {
        _currentDialog.close();
        _viewModel.onRejectedCommand().execute();
    }
}