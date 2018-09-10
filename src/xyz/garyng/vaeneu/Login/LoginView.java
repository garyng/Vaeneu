package xyz.garyng.vaeneu.Login;

import de.saxsys.mvvmfx.FxmlView;
import de.saxsys.mvvmfx.InjectViewModel;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

public class LoginView implements FxmlView<LoginViewModel>, Initializable
{

    @InjectViewModel
    private LoginViewModel _viewModel;

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {

    }
}