package xyz.garyng.vaeneu.Root;

import de.saxsys.mvvmfx.ViewModel;
import de.saxsys.mvvmfx.utils.commands.Action;
import de.saxsys.mvvmfx.utils.commands.Command;
import de.saxsys.mvvmfx.utils.commands.DelegateCommand;
import javafx.beans.property.*;
import javafx.scene.Parent;
import lombok.Getter;
import xyz.garyng.vaeneu.Dialog.DialogParameters;
import xyz.garyng.vaeneu.Dialog.IDialogHost;


public class RootViewModel implements ViewModel, IRootViewModel, IDialogHost
{

    // ContentProperty
    private final ObjectProperty<Parent> ContentProperty = new SimpleObjectProperty<>(this, "Content");

    private Runnable _showDialog;

    public final ObjectProperty<Parent> ContentProperty()
    {
        return ContentProperty;
    }

    public final void setContent(Parent value)
    {
        ContentProperty.set(value);
    }


    // DialogTitleProperty
    private final StringProperty DialogTitleProperty = new SimpleStringProperty(this, "DialogTitle");

    public final StringProperty DialogTitleProperty()
    {
        return DialogTitleProperty;
    }

    public final String getDialogTitle()
    {
        return DialogTitleProperty.get();
    }

    public final void setDialogTitle(String value)
    {
        DialogTitleProperty.set(value);
    }


    // DialogBodyProperty
    private final StringProperty DialogBodyProperty = new SimpleStringProperty(this, "DialogBody");

    public final StringProperty DialogBodyProperty()
    {
        return DialogBodyProperty;
    }

    public final String getDialogBody()
    {
        return DialogBodyProperty.get();
    }

    public final void setDialogBody(String value)
    {
        DialogBodyProperty.set(value);
    }

    // DialogAcceptedTextProperty
    private final StringProperty DialogAcceptedTextProperty = new SimpleStringProperty(this, "DialogAcceptedText");

    public final StringProperty DialogAcceptedTextProperty()
    {
        return DialogAcceptedTextProperty;
    }

    public final String getDialogAcceptedText()
    {
        return DialogAcceptedTextProperty.get();
    }

    public final void setDialogAcceptedText(String value)
    {
        DialogAcceptedTextProperty.set(value);
    }

    // DialogRejectedTextProperty
    private final StringProperty DialogRejectedTextProperty = new SimpleStringProperty(this, "DialogRejectedText");

    public final StringProperty DialogRejectedTextProperty()
    {
        return DialogRejectedTextProperty;
    }

    public final String getDialogRejectedText()
    {
        return DialogRejectedTextProperty.get();
    }

    public final void setDialogRejectedText(String value)
    {
        DialogRejectedTextProperty.set(value);
    }

    private Runnable _onAccepted = () ->
    {
    };
    private Runnable _onRejected = () ->
    {
    };

    // IsConfirmationDialogProperty
    private final BooleanProperty IsConfirmationDialogProperty = new SimpleBooleanProperty(this, "IsConfirmationDialog");

    public final BooleanProperty IsConfirmationDialogProperty()
    {
        return IsConfirmationDialogProperty;
    }

    public final boolean isConfirmationDialog()
    {
        return IsConfirmationDialogProperty.get();
    }

    public final void setIsConfirmationDialog(boolean value)
    {
        IsConfirmationDialogProperty.set(value);
    }


    @Getter
    private final Command onAcceptedCommand;
    @Getter
    private final Command onRejectedCommand;

    public RootViewModel()
    {
        onAcceptedCommand = new DelegateCommand(() -> new Action()
        {

            @Override
            protected void action()
            {
                _onAccepted.run();
            }
        });

        onRejectedCommand = new DelegateCommand(() -> new Action()
        {

            @Override
            protected void action()
            {
                _onRejected.run();
            }
        });
    }

    public void RegisterOnShowDialog(Runnable showDialog)
    {
        _showDialog = showDialog;
    }

    @Override
    public void ShowMessageDialog(DialogParameters parameters)
    {
        MapDialogParameters(parameters);
        setIsConfirmationDialog(false);
        _showDialog.run();
    }

    @Override
    public void ShowConfirmationDialog(DialogParameters parameters)
    {
        MapDialogParameters(parameters);
        setIsConfirmationDialog(true);
        _showDialog.run();
    }

    private void MapDialogParameters(DialogParameters parameters)
    {
        setDialogTitle(parameters.title());
        setDialogBody(parameters.body());
        setDialogAcceptedText(parameters.acceptedText());
        setDialogRejectedText(parameters.rejectedText());
        _onAccepted = parameters.onAccepted();
        _onRejected = parameters.onRejected();
    }
}