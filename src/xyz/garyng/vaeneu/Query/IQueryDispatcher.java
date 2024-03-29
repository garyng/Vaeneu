package xyz.garyng.vaeneu.Query;

import java.util.List;
import java.util.Optional;

public interface IQueryDispatcher
{
    <TQuery extends IQuery, TResult> Optional<TResult> Dispatch(Class<TResult> resultType, TQuery parameter);

    /**
     * Dispatch a query that returns a list of {@link TData}.
     */
    <TQuery extends IQuery, TData> Optional<List<TData>> DispatchList(Class<TData> innerDataType, TQuery parameter);
}
