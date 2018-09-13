package xyz.garyng.vaeneu.Model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor(access = AccessLevel.PUBLIC)
public class User
{
    private int id;
    private String userName;
    private String hashedPassword;
}
