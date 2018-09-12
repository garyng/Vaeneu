package xyz.garyng.vaeneu.Module;

import com.google.inject.AbstractModule;
import com.google.inject.Singleton;
import xyz.garyng.vaeneu.Service.AuthenticationService;

public class ServiceModule extends AbstractModule
{
    @Override
    protected void configure()
    {
        bind(AuthenticationService.class).in(Singleton.class);
    }
}