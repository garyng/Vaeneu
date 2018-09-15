package xyz.garyng.vaeneu;

import com.google.inject.Inject;
import com.google.inject.Module;
import com.jfoenix.controls.JFXDecorator;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
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
import lombok.extern.slf4j.Slf4j;
import org.slf4j.impl.SimpleLogger;
import xyz.garyng.vaeneu.Dashboard.DashboardViewModel;
import xyz.garyng.vaeneu.Dialog.IDialogService;
import xyz.garyng.vaeneu.Error.ErrorView;
import xyz.garyng.vaeneu.Login.LoginViewModel;
import xyz.garyng.vaeneu.Module.*;
import xyz.garyng.vaeneu.Request.RequestListViewModel;
import xyz.garyng.vaeneu.Root.RootView;
import xyz.garyng.vaeneu.Root.RootViewModel;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
public class App extends MvvmfxGuiceApplication
{
    @Inject
    private NavigationService _navigation;
    @Inject
    private IDialogService _dialogService;

    public static void main(String[] args)
    {
        System.setProperty(SimpleLogger.DEFAULT_LOG_LEVEL_KEY, "TRACE");
        Application.launch(args);
    }

    @Override
    public void startMvvmfx(Stage primaryStage)
    {
        Thread.currentThread().setUncaughtExceptionHandler(this::onUncaughtException);

        primaryStage.setTitle("Vaeneu - University Venue Management");

        ViewTuple<RootView, RootViewModel> root = FluentViewLoader.fxmlView(RootView.class).load();

        JFXDecorator decorator = new JFXDecorator(primaryStage, root.getView());
        FontAwesomeIconView icon = new FontAwesomeIconView(FontAwesomeIcon.LOCATION_ARROW);
        icon.setStyleClass("icon-white");
        decorator.setGraphic(icon);

        Scene scene = new Scene(decorator, 600, 600);
        ObservableList<String> stylesheets = scene.getStylesheets();
        stylesheets.addAll(App.class.getResource("resource/css/jfoenix-fonts.css").toExternalForm(),
                App.class.getResource("resource/css/jfoenix-design.css").toExternalForm(),
                App.class.getResource("resource/css/jfoenix-custom.css").toExternalForm());

        _navigation.RegisterRoot(root.getViewModel());
        _dialogService.RegisterHost(root.getViewModel());

        primaryStage.setScene(scene);

        // automate ui
        _navigation.GoTo(LoginViewModel.class, vm ->
        {
            vm.setUsername("admin");
            vm.setPassword("admin");
            vm.loginCommand().execute();
        }, false);

        _navigation.GoTo(DashboardViewModel.class, vm ->
        {
        }, false);

        _navigation.GoTo(RequestListViewModel.class);

        primaryStage.show();
    }

    @Override
    public void initGuiceModules(List<Module> modules) throws Exception
    {
        modules.add(new StorageModule());
        modules.add(new QueryModule());
        modules.add(new CommandModule());
        modules.add(new ServiceModule());
        modules.add(new NavigationModule());
        modules.add(new ViewModelsFactoryModule());
    }

    private void onUncaughtException(Thread thread, Throwable throwable)
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
            _logger.error("Errors occurred while showing error", e);
        }
    }
}