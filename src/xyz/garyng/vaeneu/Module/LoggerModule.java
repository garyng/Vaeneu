package xyz.garyng.vaeneu.Module;

import com.google.inject.AbstractModule;
import com.google.inject.matcher.Matchers;
import xyz.garyng.vaeneu.Logger.Sl4jTypeListener;

public class LoggerModule extends AbstractModule
{

    @Override
    protected void configure()
    {
        bindListener(Matchers.any(), new Sl4jTypeListener());
    }
}