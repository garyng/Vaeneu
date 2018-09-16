package xyz.garyng.vaeneu.Module;

import com.google.inject.AbstractModule;
import com.google.inject.TypeLiteral;
import xyz.garyng.vaeneu.Command.*;

public class CommandModule extends AbstractModule
{
    @Override
    protected void configure()
    {
        bind(ICommandDispatcher.class).to(CommandDispatcher.class);
        bind(new TypeLiteral<ICommandHandler<CancelRequestById>>()
        {
        }).to(CancelRequestByIdCommandHandler.class);
        bind(new TypeLiteral<ICommandHandler<AddRequest>>()
        {
        }).to(AddRequestCommandHandler.class);
    }
}
