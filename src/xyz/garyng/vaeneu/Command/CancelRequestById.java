package xyz.garyng.vaeneu.Command;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CancelRequestById implements ICommand
{
    private int id;
}
