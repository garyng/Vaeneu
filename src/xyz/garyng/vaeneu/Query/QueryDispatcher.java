package xyz.garyng.vaeneu.Query;

import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.inject.Key;
import com.google.inject.TypeLiteral;
import com.google.inject.util.Types;

import java.lang.reflect.ParameterizedType;
import java.util.Optional;

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
        ParameterizedType t = Types.newParameterizedType(IQueryHandler.class, parameter.getClass(), resultType);
        TypeLiteral<IQueryHandler<TQuery, TResult>> l = (TypeLiteral<IQueryHandler<TQuery, TResult>>) TypeLiteral.get(t);
        return _injector.getInstance(Key.get(l)).Execute(parameter);
    }
}