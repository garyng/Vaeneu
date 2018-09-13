package xyz.garyng.vaeneu.Module;

import com.google.inject.AbstractModule;
import com.google.inject.assistedinject.FactoryModuleBuilder;
import xyz.garyng.vaeneu.Factory.ViewModelsFactory;

public class ViewModelsFactoryModule extends AbstractModule
{
    @Override
    protected void configure()
    {
        install(new FactoryModuleBuilder().build(ViewModelsFactory.class));
    }
}