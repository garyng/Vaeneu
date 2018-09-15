package xyz.garyng.vaeneu.Command;

public interface ICommandHandler<TCommand extends ICommand>
{
    void Execute(TCommand parameter);
}
