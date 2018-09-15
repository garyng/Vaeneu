package xyz.garyng.vaeneu.Module;

import com.google.inject.AbstractModule;
import com.google.inject.Singleton;
import xyz.garyng.vaeneu.Dialog.DialogService;
import xyz.garyng.vaeneu.Dialog.IDialogService;
import xyz.garyng.vaeneu.Service.AuthenticationService;

public class ServiceModule extends AbstractModule
{
    @Override
    protected void configure()
    {
        bind(AuthenticationService.class).in(Singleton.class);
        bind(IDialogService.class).to(DialogService.class).in(Singleton.class);
    }
}