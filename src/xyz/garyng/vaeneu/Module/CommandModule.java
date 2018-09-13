package xyz.garyng.vaeneu.Module;

import com.google.inject.AbstractModule;
import xyz.garyng.vaeneu.Command.CommandDispatcher;
import xyz.garyng.vaeneu.Command.ICommandDispatcher;

public class CommandModule extends AbstractModule
{
    @Override
    protected void configure()
    {
        bind(ICommandDispatcher.class).to(CommandDispatcher.class);

    }
}
