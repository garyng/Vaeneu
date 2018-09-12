package xyz.garyng.vaeneu.Query;

import com.google.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import xyz.garyng.vaeneu.Model.User;
import xyz.garyng.vaeneu.Storage.IStorage;

import java.util.Optional;


@Slf4j
public class GetUserByUsernameQueryHandler implements IQueryHandler<GetUserByUsername, User>
{
    private IStorage<User> _storage;

    @Inject
    public GetUserByUsernameQueryHandler(IStorage<User> storage)
    {
        _storage = storage;
    }

    @Override
    public Optional<User> Execute(GetUserByUsername parameter)
    {
        Optional<User> result = _storage.Data()
                .stream()
                .filter(user -> user.userName().equals(parameter.username()))
                .findFirst();
        return result;
    }
}
