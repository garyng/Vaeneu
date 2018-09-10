package xyz.garyng.vaeneu.Logger;

import com.google.inject.TypeLiteral;
import com.google.inject.spi.TypeEncounter;
import com.google.inject.spi.TypeListener;
import org.slf4j.Logger;

import java.lang.reflect.Field;

public class Sl4jTypeListener implements TypeListener
{
    @Override
    public <I> void hear(TypeLiteral<I> typeLiteral, TypeEncounter<I> typeEncounter)
    {
        for (Field field : typeLiteral.getRawType().getDeclaredFields())
        {
            if (field.getType() == Logger.class && field.isAnnotationPresent(InjectLogger.class))
            {
                typeEncounter.register(new Sl4jMembersInjector<>(field));
            }
        }
    }
}
