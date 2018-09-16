package xyz.garyng.vaeneu.Command;

import com.google.inject.Inject;
import xyz.garyng.vaeneu.Model.Request;
import xyz.garyng.vaeneu.Model.RequestStatus;
import xyz.garyng.vaeneu.Storage.IStorage;

public class RejectRequestCommandHandler implements ICommandHandler<RejectRequest>
{

    private final IStorage<Request> _storage;

    @Inject
    public RejectRequestCommandHandler(IStorage<Request> storage)
    {
        _storage = storage;
    }


    @Override
    public void Execute(RejectRequest parameter)
    {
        _storage.Data()
                .stream()
                .filter(r -> r.id() == parameter.requestId())
                .findFirst()
                .ifPresent(r -> r.status(RequestStatus.Rejected));
        _storage.Save();
    }
}
