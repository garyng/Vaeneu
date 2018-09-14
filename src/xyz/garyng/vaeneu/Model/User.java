package xyz.garyng.vaeneu.Model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@Builder
public class User
{
    private int id;
    private String userName;
    private String hashedPassword;
    private boolean isAdmin;
}
