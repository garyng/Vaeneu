package xyz.garyng.vaeneu.Storage;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.slf4j.ILoggerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import xyz.garyng.vaeneu.Logger.InjectLogger;

import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public abstract class JsonStorage<T> implements IStorage<T>
{
    private final Logger _logger;

    protected JsonStorage(Logger logger)
    {
        _logger = logger;
    }

    private List<T> _data;

    private Path ResolvePath()
    {
        return new File("./data/" + GetFilename() + ".json").toPath();
    }

    abstract String GetFilename();

    @Override
    public void Load()
    {
        Path dataFilePath = ResolvePath();

        if (dataFilePath.toFile().isFile())
        {
            try (Reader reader = Files.newBufferedReader(dataFilePath, StandardCharsets.UTF_8))
            {
                Gson gson = new Gson();
                _data = gson.fromJson(reader, new TypeToken<List<T>>()
                {
                }.getType());
            } catch (IOException io)
            {
                _logger.error("Error occurred while loading data from disk, default data is used.", io);
                Reset();
            }

        } else
        {
            Reset();
            Save();
        }
    }

    @Override
    public void Save()
    {
        Path dataFilePath = ResolvePath();
        try
        {
            Files.createDirectories(dataFilePath.getParent());
        } catch (IOException io)
        {
            _logger.error("Error occurred while creating data directory.", io);
        }
        try (Writer writer = Files.newBufferedWriter(dataFilePath, StandardCharsets.UTF_8))
        {
            Gson gson = new Gson();
            gson.toJson(_data, new TypeToken<List<T>>()
            {
            }.getType(), writer);
        } catch (IOException io)
        {
            _logger.error("Error occurred while serializing json, data is not persisted.", io);
        }
        _logger.debug("Saved \"{}\" ({} items)", GetFilename(), _data.size());
    }

    @Override
    public void Reset()
    {
        _data = GetDefaultData();
        _logger.info("In-memory data of \"{}\" is reset to default.", GetFilename());
        Save();
    }

    public abstract List<T> GetDefaultData();

    @Override
    public void Clear()
    {
        _data.clear();
        _logger.info("In-memory data of \"{}\" is cleared.", GetFilename());
        // todo: save?
    }

    @Override
    public List<T> Data()
    {
        return _data;
    }
}
