package xyz.garyng.vaeneu.Query;

import java.util.Optional;

public interface IQueryDispatcher
{
    <TQuery extends IQuery, TResult> Optional<TResult> Dispatch(Class<TResult> resultType, TQuery parameter);
}
