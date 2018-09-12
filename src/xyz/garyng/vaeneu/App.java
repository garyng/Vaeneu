package xyz.garyng.vaeneu;

import com.google.inject.Inject;
import com.google.inject.Module;
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
import org.slf4j.impl.SimpleLogger;
import xyz.garyng.vaeneu.Error.ErrorView;
import xyz.garyng.vaeneu.Login.LoginView;
import xyz.garyng.vaeneu.Login.LoginViewModel;
import xyz.garyng.vaeneu.Model.User;
import xyz.garyng.vaeneu.Module.StorageModule;
import xyz.garyng.vaeneu.Storage.IStorage;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class App extends MvvmfxGuiceApplication
{
    // todo: is this needed?

    @Inject
    private IStorage<User> _userStorage;

    public static void main(String[] args)
    {
        System.setProperty(SimpleLogger.DEFAULT_LOG_LEVEL_KEY, "TRACE");
        Application.launch(args);
    }

    @Override
    public void startMvvmfx(Stage primaryStage)
    {
        _userStorage.Load();

        Thread.currentThread().setUncaughtExceptionHandler((thread, throwable) ->
        {
            _logger.error("Uncaught exception", throwable);
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
                _logger.error("Error occurred while showing error", e);
            }
        });

        primaryStage.setTitle("Vaeneu - University Venue Management");
        ViewTuple<LoginView, LoginViewModel> main = FluentViewLoader.fxmlView(LoginView.class).load();

        JFXDecorator decorator = new JFXDecorator(primaryStage, main.getView());

        Scene scene = new Scene(decorator, 600, 600);
        ObservableList<String> stylesheets = scene.getStylesheets();
        stylesheets.addAll(App.class.getResource("resource/css/jfoenix-fonts.css").toExternalForm(),
                App.class.getResource("resource/css/jfoenix-design.css").toExternalForm(),
                App.class.getResource("resource/css/jfoenix-custom.css").toExternalForm());

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    @Override
    public void initGuiceModules(List<Module> modules) throws Exception
    {
        modules.add(new StorageModule());
    }
}
