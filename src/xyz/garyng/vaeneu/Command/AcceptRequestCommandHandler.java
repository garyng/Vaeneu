package xyz.garyng.vaeneu.Command;

import com.google.inject.Inject;
import xyz.garyng.vaeneu.Model.Request;
import xyz.garyng.vaeneu.Model.RequestStatus;
import xyz.garyng.vaeneu.Storage.IStorage;

public class AcceptRequestCommandHandler implements ICommandHandler<AcceptRequest>
{

    private final IStorage<Request> _storage;

    @Inject
    public AcceptRequestCommandHandler(IStorage<Request> storage)
    {
        _storage = storage;
    }

    @Override
    public void Execute(AcceptRequest parameter)
    {
        _storage.Data()
                .stream()
                .filter(r -> r.id() == parameter.requestId())
                .findFirst()
                .ifPresent(r -> r.status(RequestStatus.Accepted));
        _storage.Save();
    }
}
