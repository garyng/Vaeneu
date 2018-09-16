package xyz.garyng.vaeneu.Command;

import com.google.inject.Inject;
import lombok.Builder;
import lombok.Data;
import xyz.garyng.vaeneu.Model.Request;
import xyz.garyng.vaeneu.Model.RequestStatus;
import xyz.garyng.vaeneu.Query.IQuery;
import xyz.garyng.vaeneu.Query.IQueryHandler;
import xyz.garyng.vaeneu.Storage.IStorage;

import java.time.LocalDateTime;
import java.util.Optional;

public class AddRequestCommandHandler implements ICommandHandler<AddRequest>
{
    private final IStorage<Request> _storage;

    @Inject
    public AddRequestCommandHandler(IStorage<Request> storage)
    {
        _storage = storage;
    }

    @Override
    public void Execute(AddRequest parameter)
    {
        int newId = _storage.Data()
                .stream()
                .mapToInt(Request::id)
                .max()
                .orElse(0) + 1;

        Request newRequest = Request.builder()
                .id(newId)
                .venueId(parameter.venueId())
                .requesterId(parameter.requesterId())
                .startDateTime(LocalDateTime.of(parameter.date(), parameter.startTime()))
                .endDateTime(LocalDateTime.of(parameter.date(), parameter.endTime()))
                .build();

        _storage.Data().add(newRequest);
        _storage.Save();

    }
}