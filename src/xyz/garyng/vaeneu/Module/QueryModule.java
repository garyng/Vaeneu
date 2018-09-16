package xyz.garyng.vaeneu.Module;

import com.google.inject.AbstractModule;
import com.google.inject.TypeLiteral;
import xyz.garyng.vaeneu.Model.Request;
import xyz.garyng.vaeneu.Model.User;
import xyz.garyng.vaeneu.Model.Venue;
import xyz.garyng.vaeneu.Query.*;

import java.util.List;

public class QueryModule extends AbstractModule
{
    @Override
    protected void configure()
    {
        bind(IQueryDispatcher.class).to(QueryDispatcher.class);

        bind(new TypeLiteral<IQueryHandler<GetUserByUsername, User>>()
        {
        }).to(GetUserByUsernameQueryHandler.class);

        bind(new TypeLiteral<IQueryHandler<GetAllVenue, List<Venue>>>()
        {
        }).to(GetAllVenueQueryHandler.class);
        bind(new TypeLiteral<IQueryHandler<GetVenueById, Venue>>()
        {
        }).to(GetVenueByIdQueryHandler.class);
        bind(new TypeLiteral<IQueryHandler<GetAllRequest, List<Request>>>()
        {
        }).to(GetAllRequestQueryHandler.class);
        bind(new TypeLiteral<IQueryHandler<GetRequestsByVenueIdAndDate, List<Request>>>()
        {
        }).to(GetRequestsByVenueIdAndDateQueryHandler.class);

    }
}
