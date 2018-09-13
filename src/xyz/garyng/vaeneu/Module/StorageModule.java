package xyz.garyng.vaeneu.Module;

import com.google.inject.AbstractModule;
import com.google.inject.Singleton;
import com.google.inject.TypeLiteral;
import xyz.garyng.vaeneu.Model.User;
import xyz.garyng.vaeneu.Model.Venue;
import xyz.garyng.vaeneu.Storage.IStorage;
import xyz.garyng.vaeneu.Storage.UserStorage;
import xyz.garyng.vaeneu.Storage.VenueStorage;

public class StorageModule extends AbstractModule
{
    @Override
    protected void configure()
    {
        bind(new TypeLiteral<IStorage<User>>()
        {
        }).to(UserStorage.class).in(Singleton.class);
        bind(new TypeLiteral<IStorage<Venue>>()
        {
        }).to(VenueStorage.class).in(Singleton.class);
    }
}