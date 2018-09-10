package xyz.garyng.vaeneu.Module;

import com.google.inject.AbstractModule;
import com.google.inject.TypeLiteral;
import xyz.garyng.vaeneu.Model.User;
import xyz.garyng.vaeneu.Storage.IStorage;
import xyz.garyng.vaeneu.Storage.UserStorage;

public class StorageModule extends AbstractModule
{
    @Override
    protected void configure()
    {
        bind(new TypeLiteral<IStorage<User>>()
        {
        }).to(UserStorage.class);
    }
}