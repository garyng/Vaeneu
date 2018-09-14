package xyz.garyng.vaeneu.Storage;

import com.google.inject.TypeLiteral;
import org.apache.commons.codec.digest.DigestUtils;
import xyz.garyng.vaeneu.Model.User;

import java.lang.reflect.Type;
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
    Type getCollectionType()
    {
        return new TypeLiteral<ArrayList<User>>()
        {
        }.getType();
    }

    @Override
    public List<User> GetDefaultData()
    {
        return new ArrayList<>()
        {
            {
                add(new User(1, "admin", DigestUtils.sha1Hex("admin"), true));
                add(new User(2, "user", DigestUtils.sha1Hex("user"), false));
            }
        };
    }
}
