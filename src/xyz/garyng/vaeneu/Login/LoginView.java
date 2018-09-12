package xyz.garyng.vaeneu.Login;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import de.saxsys.mvvmfx.FxmlView;
import de.saxsys.mvvmfx.InjectViewModel;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class LoginView implements FxmlView<LoginViewModel>, Initializable
{
    public JFXTextField tfUsername;
    public JFXPasswordField pfPassword;
    public JFXButton btnLogin;
    public Label lblLoginError;

    @InjectViewModel
    private LoginViewModel _viewModel;

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        tfUsername.textProperty().bindBidirectional(_viewModel.UsernameProperty());
        pfPassword.textProperty().bindBidirectional(_viewModel.PasswordProperty());
        btnLogin.disableProperty().bind(_viewModel.getLoginCommand().executableProperty().not());
        lblLoginError.visibleProperty().bind(_viewModel.LoginErrorProperty());
        // https://stackoverflow.com/questions/49050623/hide-some-elements-without-occupying-space-in-javafx
        lblLoginError.managedProperty().bind(_viewModel.LoginErrorProperty());
    }

    public void onLoginClicked(ActionEvent actionEvent)
    {
        _viewModel.getLoginCommand().execute();
    }
}
