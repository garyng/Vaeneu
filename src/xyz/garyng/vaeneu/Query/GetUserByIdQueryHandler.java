package xyz.garyng.vaeneu.Query;

import xyz.garyng.vaeneu.Model.User;
import xyz.garyng.vaeneu.Query.GetUserById;
import xyz.garyng.vaeneu.Query.IQueryHandler;

import java.util.Optional;

public class GetUserByIdQueryHandler implements IQueryHandler<GetUserById, User>
{
    @Override
    public Optional<User> Execute(GetUserById parameter)
    {
        return Optional.of(new User(1, "username", "hash"));
    }
}
