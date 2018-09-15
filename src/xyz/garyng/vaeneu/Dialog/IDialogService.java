package xyz.garyng.vaeneu.Dialog;


public interface IDialogService
{
    void RegisterHost(IDialogHost host);

    void ShowMessageDialog(DialogParameters parameters);

    void ShowConfirmationDialog(DialogParameters parameters);
}