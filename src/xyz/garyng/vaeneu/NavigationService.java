package xyz.garyng.vaeneu;

import com.google.inject.Inject;
import com.jfoenix.controls.JFXDecorator;
import de.saxsys.mvvmfx.FluentViewLoader;
import de.saxsys.mvvmfx.FxmlView;
import de.saxsys.mvvmfx.ViewModel;
import de.saxsys.mvvmfx.ViewTuple;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.stage.Stage;
import lombok.extern.slf4j.Slf4j;
import xyz.garyng.vaeneu.Root.IRootViewModel;

import java.util.Optional;
import java.util.Stack;
import java.util.function.Consumer;

@Slf4j
public class NavigationService
{
    private ViewTuple<? extends FxmlView<? extends ViewModel>, ? extends ViewModel> _currentViewTuple;
    private Stack<ViewTuple<? extends FxmlView<? extends ViewModel>, ? extends ViewModel>> _history = new Stack<>();

    private final ViewResolver _viewResolver;
    private IRootViewModel _rootViewModel;

    @Inject
    NavigationService(ViewResolver viewResolver)
    {
        _viewResolver = viewResolver;
    }

    public void RegisterRoot(IRootViewModel rootViewModel)
    {

        _rootViewModel = rootViewModel;
    }

    public <T extends ViewModel> void GoTo(Class<T> targetViewModelType)
    {
        GoTo(targetViewModelType, vm ->
        {
        }, true);
    }

    public <T extends ViewModel> void GoTo(Class<T> targetViewModelType, Consumer<T> callback)
    {
        GoTo(targetViewModelType, callback, true);
    }

    @SuppressWarnings("unchecked")
    public <T extends ViewModel> void GoTo(Class<T> targetViewModelType, Consumer<T> callback, boolean pushCurrentToHistory)
    {
        Optional<? extends Class<? extends FxmlView<T>>> viewClass = _viewResolver.Resolve(targetViewModelType);
        if (!viewClass.isPresent())
        {
            _logger.warn("Unable to navigate to \"{}\"", targetViewModelType.getName());
            return;
        }
        ViewTuple<? extends FxmlView<T>, T> newViewTuple = FluentViewLoader.fxmlView(viewClass.get()).load();
        _logger.debug("Loaded view tuple for {}", targetViewModelType.getName());

        callback.accept(newViewTuple.getViewModel());
        if (pushCurrentToHistory && _currentViewTuple != null)
        {
            _logger.debug("Pushed {} to history stack", _currentViewTuple.getViewModel().getClass().getName());
            _history.push(_currentViewTuple);
        }
        _currentViewTuple = newViewTuple;

        _rootViewModel.setContent(newViewTuple.getView());
        _logger.debug("Navigated to {}", targetViewModelType.getName());
    }

    @SuppressWarnings("unchecked")
    public void GoBack()
    {
        if (_history.empty())
        {
            _logger.debug("History stack is empty");
            return;
        }
        _currentViewTuple = _history.pop();
        _logger.debug("Navigated back to {}.", _currentViewTuple.getViewModel().getClass().getName());
    }

    public Boolean CanGoBack()
    {
        return !_history.empty();
    }

}
