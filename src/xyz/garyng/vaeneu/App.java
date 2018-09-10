package xyz.garyng.vaeneu;

import com.jfoenix.controls.JFXDecorator;
import de.saxsys.mvvmfx.FluentViewLoader;
import de.saxsys.mvvmfx.ViewTuple;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.stage.Stage;
import xyz.garyng.vaeneu.Main.LoginView;
import xyz.garyng.vaeneu.Main.LoginViewModel;

public class App extends Application
{

    @Override
    public void start(Stage primaryStage) throws Exception
    {
        primaryStage.setTitle("Vaeneu - University Venue Management");
        ViewTuple<LoginView, LoginViewModel> main = FluentViewLoader.fxmlView(LoginView.class).load();

        JFXDecorator decorator = new JFXDecorator(primaryStage, main.getView());


        Scene scene = new Scene(decorator, 600, 600);
        ObservableList<String> stylesheets = scene.getStylesheets();
        stylesheets.addAll(App.class.getResource("css/jfoenix-fonts.css").toExternalForm(),
                App.class.getResource("css/jfoenix-design.css").toExternalForm(),
                App.class.getResource("css/jfoenix-custom.css").toExternalForm());

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args)
    {
        Application.launch(args);
    }
}
