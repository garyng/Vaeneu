package xyz.garyng.vaeneu.Module;

import com.google.inject.AbstractModule;
import com.google.inject.TypeLiteral;
import xyz.garyng.vaeneu.Model.User;
import xyz.garyng.vaeneu.Query.*;

public class QueryModule extends AbstractModule
{
    @Override
    protected void configure()
    {
        bind(IQueryDispatcher.class).to(QueryDispatcher.class);
        bind(new TypeLiteral<IQueryHandler<GetUserByUsername, User>>()
        {
        }).to(GetUserByUsernameQueryHandler.class);
    }
}
