package xyz.garyng.vaeneu.Query;


import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor(access = AccessLevel.PUBLIC)
public class GetVenueById implements IQuery
{
    private int id;
}
