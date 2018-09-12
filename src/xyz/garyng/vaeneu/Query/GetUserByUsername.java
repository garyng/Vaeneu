package xyz.garyng.vaeneu.Query;

import lombok.Data;

@Data
public class GetUserByUsername implements IQuery
{
    private String username;

    public GetUserByUsername(String username)
    {
        this.username = username;
    }
}
