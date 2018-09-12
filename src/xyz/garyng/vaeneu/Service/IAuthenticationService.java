package xyz.garyng.vaeneu.Service;

import xyz.garyng.vaeneu.Model.User;

import java.util.Optional;

public interface IAuthenticationService
{
    boolean Authenticate(String username, String password);

    void DeAuthenticate();

    boolean IsAuthenticated();

    Optional<User> CurrentUser();

}
