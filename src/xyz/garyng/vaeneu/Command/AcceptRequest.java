package xyz.garyng.vaeneu.Command;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AcceptRequest implements ICommand
{
    private int requestId;
    private int reviewerId;
}
