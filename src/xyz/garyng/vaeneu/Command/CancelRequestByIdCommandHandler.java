package xyz.garyng.vaeneu.Command;

import com.google.inject.Inject;
import xyz.garyng.vaeneu.Model.Request;
import xyz.garyng.vaeneu.Model.RequestStatus;
import xyz.garyng.vaeneu.Storage.IStorage;

public class CancelRequestByIdCommandHandler implements ICommandHandler<CancelRequestById>
{
    private final IStorage<Request> _storage;

    @Inject
    public CancelRequestByIdCommandHandler(IStorage<Request> storage)
    {
        _storage = storage;
    }

    @Override
    public void Execute(CancelRequestById parameter)
    {
        _storage.Data()
                .stream()
                .filter(r -> r.id() == parameter.id() && r.status().equals(RequestStatus.Pending))
                .findFirst()
                .ifPresent(r ->
                {
                    r.status(RequestStatus.Cancelled);
                    _storage.Save();
                });
    }
}
