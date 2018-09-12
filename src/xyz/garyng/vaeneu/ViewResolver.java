package xyz.garyng.vaeneu;

import de.saxsys.mvvmfx.FxmlView;
import de.saxsys.mvvmfx.ViewModel;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.util.Optional;

@Slf4j
public class ViewResolver
{
    @SuppressWarnings("unchecked")
    public <T extends ViewModel> Optional<? extends Class<? extends FxmlView<T>>> Resolve(Class<T> viewModelType)
    {
        String viewModelName = viewModelType.getName();
        String viewName = StringUtils.removeEnd(viewModelName, "Model");
        try
        {
            Class<? extends FxmlView<T>> viewType = (Class<? extends FxmlView<T>>) Class.forName(viewName);
            _logger.debug("Resolved {} to {}", viewModelName, viewName);
            return Optional.of(viewType);
        } catch (ClassNotFoundException e)
        {
            _logger.error("Unable to resolve \"" + viewName + "\"", e);
            return Optional.empty();
        }
    }
}
