package xyz.garyng.vaeneu.Storage;

import xyz.garyng.vaeneu.Model.User;

import java.util.ArrayList;
import java.util.List;

public class UserStorage extends JsonStorage<User>
{
    @Override
    String GetFilename()
    {
        return UserStorage.class.getName();
    }

    @Override
    public List<User> GetDefaultData()
    {
        return new ArrayList<>()
        {
            {
                add(new User(1, "admin", "admin"));
                add(new User(2, "admin1", "admin"));
                add(new User(3, "admin2", "admin"));
                add(new User(4, "admin3", "admin"));
                add(new User(5, "admin4", "admin"));
                add(new User(6, "admin5", "admin"));
            }
        };
    }
}
