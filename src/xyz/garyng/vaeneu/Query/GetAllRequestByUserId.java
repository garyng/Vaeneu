package xyz.garyng.vaeneu.Query;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GetAllRequestByUserId implements IQuery
{
    private int userId;
}

