package xyz.garyng.vaeneu.Model;

import lombok.Data;

@Data
public class User
{
    private int id;
    private String userName;
    private String hashedPassword;

    public User(int id, String userName, String hashedPassword)
    {
        this.id = id;
        this.userName = userName;
        this.hashedPassword = hashedPassword;
    }
}
