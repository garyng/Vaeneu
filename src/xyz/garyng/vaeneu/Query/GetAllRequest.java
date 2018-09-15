package xyz.garyng.vaeneu.Query;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GetAllRequest implements IQuery
{
    private int userId;
}
