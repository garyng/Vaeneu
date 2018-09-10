package xyz.garyng.vaeneu.Logger;

import com.google.inject.MembersInjector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;

public class Sl4jMembersInjector<T> implements MembersInjector<T>
{
    private final Field _field;
    private final Logger _logger;

    public Sl4jMembersInjector(Field field)
    {
        _field = field;
        _logger = LoggerFactory.getLogger(field.getDeclaringClass());
        _field.setAccessible(true);
    }

    @Override
    public void injectMembers(T t)
    {
        try
        {
            _field.set(t, _logger);
        } catch (IllegalAccessException e)
        {
            throw new RuntimeException();
        }
    }
}
