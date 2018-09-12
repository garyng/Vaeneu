package xyz.garyng.vaeneu.Query;

import java.util.Optional;

public interface IQueryHandler<TQuery extends IQuery, TResult>
{
    Optional<TResult> Execute(TQuery parameter);
}
