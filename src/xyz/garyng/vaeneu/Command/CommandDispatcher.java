package xyz.garyng.vaeneu.Command;

import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.inject.Key;
import com.google.inject.TypeLiteral;
import com.google.inject.util.Types;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.ParameterizedType;

@Slf4j
public class CommandDispatcher implements ICommandDispatcher
{
    private Injector _injector;

    @Inject
    public CommandDispatcher(Injector injector)
    {
        _injector = injector;
    }

    @Override
    @SuppressWarnings("unchecked")
    public <TCommand extends ICommand> void Dispatch(TCommand parameter)
    {
        ParameterizedType handlerType = Types.newParameterizedType(ICommandHandler.class, parameter.getClass());
        TypeLiteral<ICommandHandler<TCommand>> l = (TypeLiteral<ICommandHandler<TCommand>>) TypeLiteral.get(handlerType);
        _logger.debug("[{}] {}", l, parameter);
        _injector.getInstance(Key.get(l)).Execute(parameter);
    }
}
