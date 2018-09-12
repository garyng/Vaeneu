package xyz.garyng.vaeneu.Dashboard;

import com.jfoenix.controls.JFXRippler;
import de.saxsys.mvvmfx.FxmlView;
import de.saxsys.mvvmfx.InjectViewModel;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

public class DashboardView implements FxmlView<DashboardViewModel>, Initializable
{

    public JFXRippler btnGoBack;
    public JFXRippler btnUser;

    @InjectViewModel
    private DashboardViewModel _viewModel;

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        btnGoBack.visibleProperty().bind(_viewModel.CanGoBackProperty());
        btnUser.visibleProperty().bind(_viewModel.IsAuthenticatedProperty());
    }
}