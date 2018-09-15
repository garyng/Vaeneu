package xyz.garyng.vaeneu.Query;

import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.inject.Key;
import com.google.inject.TypeLiteral;
import com.google.inject.util.Types;
import lombok.extern.slf4j.Slf4j;
import net.jodah.typetools.TypeResolver;

import javax.management.openmbean.TabularData;
import java.lang.reflect.Parameter;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.Optional;

@Slf4j
public class QueryDispatcher implements IQueryDispatcher
{
    private Injector _injector;

    @Inject
    public QueryDispatcher(Injector injector)
    {
        _injector = injector;
    }

    @Override
    @SuppressWarnings("unchecked")
    public <TQuery extends IQuery, TResult> Optional<TResult> Dispatch(Class<TResult> resultType, TQuery parameter)
    {
        ParameterizedType handlerType = Types.newParameterizedType(IQueryHandler.class, parameter.getClass(), resultType);
        TypeLiteral<IQueryHandler<TQuery, TResult>> l = (TypeLiteral<IQueryHandler<TQuery, TResult>>) TypeLiteral.get(handlerType);
        _logger.debug("[{}] {}", l, parameter);
        return _injector.getInstance(Key.get(l)).Execute(parameter);
    }

    @Override
    @SuppressWarnings("unchecked")
    public <TQuery extends IQuery, TData> Optional<List<TData>> DispatchList(Class<TData> innerDataType, TQuery parameter)
    {
        ParameterizedType listType = Types.newParameterizedType(List.class, innerDataType);
        ParameterizedType handlerType = Types.newParameterizedType(IQueryHandler.class, parameter.getClass(), listType);
        TypeLiteral<IQueryHandler<TQuery, List<TData>>> l = (TypeLiteral<IQueryHandler<TQuery, List<TData>>>) TypeLiteral.get(handlerType);
        _logger.debug("[{}] {}", l, parameter);
        return _injector.getInstance(Key.get(l)).Execute(parameter);
    }
}