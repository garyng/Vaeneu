package xyz.garyng.vaeneu.Module;

import com.google.inject.AbstractModule;
import com.google.inject.Singleton;
import xyz.garyng.vaeneu.NavigationService;
import xyz.garyng.vaeneu.ViewResolver;

public class NavigationModule extends AbstractModule
{

    @Override
    protected void configure()
    {
        bind(ViewResolver.class).in(Singleton.class);
        bind(NavigationService.class).in(Singleton.class);
    }
}