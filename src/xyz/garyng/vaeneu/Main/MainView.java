package xyz.garyng.vaeneu.Main;

import de.saxsys.mvvmfx.FxmlView;
import de.saxsys.mvvmfx.InjectViewModel;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

public class MainView implements FxmlView<MainViewModel>, Initializable
{
    @InjectViewModel
    private MainViewModel _viewModel;

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
    }
}
