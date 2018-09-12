package xyz.garyng.vaeneu.Root;

import de.saxsys.mvvmfx.FxmlView;
import de.saxsys.mvvmfx.InjectViewModel;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

public class RootView implements FxmlView<RootViewModel>, Initializable
{

    public AnchorPane rootPane;
    @InjectViewModel
    private RootViewModel _viewModel;

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        _viewModel.ContentProperty().addListener((observable, oldValue, newValue) ->
        {
            AnchorPane.setBottomAnchor(newValue, 0.0);
            AnchorPane.setTopAnchor(newValue, 0.0);
            AnchorPane.setRightAnchor(newValue, 0.0);
            AnchorPane.setLeftAnchor(newValue, 0.0);
            rootPane.getChildren().setAll(newValue);
        });
    }
}