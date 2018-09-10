package xyz.garyng.vaeneu.Module;

import com.google.inject.AbstractModule;
import org.slf4j.ILoggerFactory;
import org.slf4j.LoggerFactory;

public class LoggerModule extends AbstractModule
{

    @Override
    protected void configure()
    {
        // bindListener(Matchers.any(), new Sl4jTypeListener());
        bind(ILoggerFactory.class).toInstance(LoggerFactory.getILoggerFactory());
    }
}