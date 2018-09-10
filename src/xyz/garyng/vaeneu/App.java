package xyz.garyng.vaeneu;

import com.jfoenix.controls.JFXDecorator;
import de.saxsys.mvvmfx.FluentViewLoader;
import de.saxsys.mvvmfx.ViewTuple;
import de.saxsys.mvvmfx.guice.MvvmfxGuiceApplication;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import xyz.garyng.vaeneu.Error.ErrorView;
import xyz.garyng.vaeneu.Main.LoginView;
import xyz.garyng.vaeneu.Main.LoginViewModel;

import java.io.IOException;
import java.util.Arrays;
import java.util.stream.Collectors;

public class App extends MvvmfxGuiceApplication
{
    public static void main(String[] args)
    {
        Application.launch(args);
    }

    @Override
    public void startMvvmfx(Stage primaryStage) throws Exception
    {
        Thread.currentThread().setUncaughtExceptionHandler((thread, throwable) ->
        {
            try
            {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("Error/ErrorView.fxml"));
                Parent root = loader.load();
                ErrorView controller = loader.getController();
                String message = Arrays.stream(throwable.getStackTrace()).map(Object::toString).collect(Collectors.joining("\n"));
                controller.taMessage.setText(message);

                Stage stage = new Stage();
                stage.setTitle("Something went wrong...");
                stage.setScene(new Scene(root, 600, 500));
                stage.showAndWait();
                Platform.exit();
            } catch (IOException e)
            {
            }
        });


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
}
