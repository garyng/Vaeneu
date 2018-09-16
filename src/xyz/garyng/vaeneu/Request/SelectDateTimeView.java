package xyz.garyng.vaeneu.Request;

import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXNodesList;
import com.jfoenix.controls.JFXRippler;
import de.saxsys.mvvmfx.FxmlView;
import de.saxsys.mvvmfx.InjectViewModel;
import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.input.MouseEvent;
import javafx.util.StringConverter;

import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class SelectDateTimeView implements FxmlView<SelectDateTimeViewModel>, Initializable
{

    public JFXRippler btnGoBack;
    public ListView lvTimeSlots;
    public JFXDatePicker datePicker;
    public JFXNodesList nlRequest;
    @InjectViewModel
    private SelectDateTimeViewModel _viewModel;

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        btnGoBack.visibleProperty().bind(_viewModel.CanGoBackProperty());

        lvTimeSlots.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        _viewModel.SelectedTimeSlotsProperty().set(lvTimeSlots.getSelectionModel().getSelectedItems());
        lvTimeSlots.itemsProperty().bind(_viewModel.AvailableTimeSlotsProperty());

        nlRequest.visibleProperty().bind(Bindings.createBooleanBinding(() -> _viewModel.SelectedTimeSlotsProperty().size() > 0, _viewModel.SelectedTimeSlotsProperty()));

        _viewModel.SelectedDateProperty().bind(datePicker.valueProperty());
        datePicker.setValue(LocalDate.now().plusDays(1));
        datePicker.setConverter(new StringConverter<>()
        {
            DateTimeFormatter _formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");

            @Override
            public String toString(LocalDate date)
            {
                if (date != null)
                {
                    return _formatter.format(date);
                }
                return null;
            }

            @Override
            public LocalDate fromString(String string)
            {
                if (string != null && !string.isEmpty())
                {
                    return LocalDate.parse(string, _formatter);
                }
                return null;
            }
        });
    }

    public void onGoBackButtonClicked(MouseEvent mouseEvent)
    {
        _viewModel.goBackCommand().execute();
    }

    public void onConfirmButtonClicked(ActionEvent actionEvent)
    {
        _viewModel.confirmTimeSlotsCommand().execute();
    }
}