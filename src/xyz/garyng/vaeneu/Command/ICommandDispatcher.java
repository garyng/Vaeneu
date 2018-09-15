package xyz.garyng.vaeneu.Command;

public interface ICommandDispatcher
{
    <TCommand extends ICommand> void Dispatch(TCommand parameter);
}
