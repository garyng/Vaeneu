package xyz.garyng.vaeneu.Storage;

import java.util.List;

public interface IStorage<T>
{
    void Load();
    void Save();
    void Reset();
    void Clear();
    List<T> Data();
}
