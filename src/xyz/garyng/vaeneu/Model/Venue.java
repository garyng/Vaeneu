package xyz.garyng.vaeneu.Model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@Builder
public class Venue
{
    private int id;
    private String name;
    private String description;
    private int capacity;
}
