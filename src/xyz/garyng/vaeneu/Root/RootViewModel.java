package xyz.garyng.vaeneu.Root;

import de.saxsys.mvvmfx.ViewModel;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.Parent;

public class RootViewModel implements ViewModel, IRootViewModel
{

    // ContentProperty
    private final ObjectProperty<Parent> ContentProperty = new SimpleObjectProperty<>(this, "Content");

    public final ObjectProperty<Parent> ContentProperty()
    {
        return ContentProperty;
    }

    public final void setContent(Parent value)
    {
        ContentProperty.set(value);
    }

}