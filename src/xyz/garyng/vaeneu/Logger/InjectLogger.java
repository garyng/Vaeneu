package xyz.garyng.vaeneu.Logger;


import javax.inject.Scope;
import java.lang.annotation.*;

@Scope
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface InjectLogger
{
}
