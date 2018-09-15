package xyz.garyng.vaeneu.Dialog;

import lombok.extern.slf4j.Slf4j;

/**
 * Act as a bridge between the consumer and the host
 * because {@code mvvmfx-guice} does not allow binding an instance of {@link xyz.garyng.vaeneu.Root.IRootViewModel} (which is obtained through {@linkplain de.saxsys.mvvmfx.FluentViewLoader})
 * inside the {@code startMvvmFx} method.
 */
@Slf4j
public class DialogService implements IDialogService
{
    private IDialogHost _host;

    @Override
    public void RegisterHost(IDialogHost host)
    {
        _host = host;
        _logger.debug("{} registered as dialog host", host.getClass().getName());
    }

    @Override
    public void ShowMessageDialog(DialogParameters parameters)
    {
        if (_host == null)
        {
            _logger.warn("Dialog host is null");
            return;
        }
        _host.ShowMessageDialog(parameters);
    }

    @Override
    public void ShowConfirmationDialog(DialogParameters parameters)
    {

        if (_host == null)
        {
            _logger.warn("Dialog host is null");
            return;
        }
        _host.ShowConfirmationDialog(parameters);
    }
}
