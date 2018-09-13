package xyz.garyng.vaeneu.Service;

import com.google.inject.Inject;
import org.apache.commons.codec.digest.DigestUtils;
import xyz.garyng.vaeneu.Model.User;
import xyz.garyng.vaeneu.Query.GetUserByUsername;
import xyz.garyng.vaeneu.Query.IQueryDispatcher;

import java.util.Optional;

public class AuthenticationService implements IAuthenticationService
{
    private IQueryDispatcher _queryDispatcher;
    private Optional<User> _currentUser = Optional.empty();

    @Inject
    public AuthenticationService(IQueryDispatcher queryDispatcher)
    {
        _queryDispatcher = queryDispatcher;
    }

    @Override
    public boolean Authenticate(String username, String password)
    {
        Optional<User> user = _queryDispatcher.Dispatch(User.class, new GetUserByUsername(username));
        _currentUser = user;
        return user.map(u -> u.hashedPassword().equals(DigestUtils.sha1Hex(password))).orElse(false);
    }

    @Override
    public void DeAuthenticate()
    {
        _currentUser = Optional.empty();
    }

    @Override
    public boolean IsAuthenticated()
    {
        return _currentUser.isPresent();
    }


    @Override
    public Optional<User> CurrentUser()
    {
        return _currentUser;
    }
}
