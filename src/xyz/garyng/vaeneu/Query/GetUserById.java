package xyz.garyng.vaeneu.Query;

import lombok.Data;
import xyz.garyng.vaeneu.Query.IQuery;

@Data
public class GetUserById implements IQuery
{
    private int id;

    public GetUserById(int id)
    {
        this.id = id;
    }
}
