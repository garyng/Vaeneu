package xyz.garyng.vaeneu.Model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor(access = AccessLevel.PUBLIC)
public class Venue
{
    private int id;
    private String name;
    private String description;
    private int capacity;
}
