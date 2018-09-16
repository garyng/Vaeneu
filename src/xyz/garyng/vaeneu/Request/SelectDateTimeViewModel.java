package xyz.garyng.vaeneu.Request;

import com.google.inject.Inject;
import de.saxsys.mvvmfx.utils.commands.Action;
import de.saxsys.mvvmfx.utils.commands.Command;
import de.saxsys.mvvmfx.utils.commands.DelegateCommand;
import javafx.beans.property.ListProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lombok.Getter;
import xyz.garyng.vaeneu.Command.AddRequest;
import xyz.garyng.vaeneu.Command.ICommandDispatcher;
import xyz.garyng.vaeneu.Dialog.DialogParameters;
import xyz.garyng.vaeneu.Dialog.IDialogService;
import xyz.garyng.vaeneu.Model.Request;
import xyz.garyng.vaeneu.Model.Venue;
import xyz.garyng.vaeneu.NavigationService;
import xyz.garyng.vaeneu.Query.GetRequestsByVenueIdAndDate;
import xyz.garyng.vaeneu.Query.IQueryDispatcher;
import xyz.garyng.vaeneu.Service.AuthenticationService;
import xyz.garyng.vaeneu.ViewModelBase;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;


public class SelectDateTimeViewModel extends ViewModelBase
{
    // SelectedDateProperty
    private final ObjectProperty<LocalDate> _selectedDateProperty = new SimpleObjectProperty<>(this, "SelectedDate");

    public final ObjectProperty<LocalDate> SelectedDateProperty()
    {
        return _selectedDateProperty;
    }

    public final LocalDate getSelectedDate()
    {
        return _selectedDateProperty.get();
    }

    public final void setSelectedDate(LocalDate value)
    {
        _selectedDateProperty.set(value);
    }


    private Venue _selectedVenue;

    public Venue getSelectedVenue()
    {
        return _selectedVenue;
    }

    public void setSelectedVenue(Venue selectedVenue)
    {
        this._selectedVenue = selectedVenue;
        onSelectedDateChanged(getSelectedDate());
    }


    // AvailableTimeSlotsProperty
    @Getter
    private final ListProperty<TimePeriod> AvailableTimeSlotsProperty = new SimpleListProperty<>(this, "AvailableTimeSlots", FXCollections.observableArrayList());

    @Getter
    private ListProperty<TimePeriod> SelectedTimeSlotsProperty = new SimpleListProperty<>(this, "AvailableTimeSlots", FXCollections.observableArrayList());

    public final ObservableList<TimePeriod> getSelectedTimeSlots()
    {
        return SelectedTimeSlotsProperty.get();
    }

    @Getter
    private final Command confirmTimeSlotsCommand;

    private final IDialogService _dialogService;

    @Inject
    public SelectDateTimeViewModel(NavigationService navigation, AuthenticationService authentication,
                                   IQueryDispatcher queryDispatcher, ICommandDispatcher commandDispatcher,
                                   IDialogService dialogService)
    {
        super(navigation, authentication, queryDispatcher, commandDispatcher);
        _dialogService = dialogService;
        _selectedDateProperty.addListener((observable, oldValue, newValue) ->
                onSelectedDateChanged(newValue));
        confirmTimeSlotsCommand = new DelegateCommand(this::onConfirmTimeSlots);
    }

    void onSelectedDateChanged(LocalDate newDate)
    {
        if (_selectedVenue == null)
            return; // venue is set by the navigation service after the view/viewmodel has been initialized
        if (newDate.isBefore(LocalDate.now().plusDays(1)))
        {
            AvailableTimeSlotsProperty.clear();
            return;
        }

        GetRequestsByVenueIdAndDate request = GetRequestsByVenueIdAndDate
                .builder()
                .date(newDate)
                .venueId(_selectedVenue.id())
                .build();

        _queryDispatcher.DispatchList(Request.class, request)
                .map(requests ->
                        requests.stream()
                                .map(r -> TimePeriod.FromDateTime(r.startDateTime(), r.endDateTime()))
                                .collect(Collectors.toList()))
                .ifPresent(periods -> AvailableTimeSlotsProperty.setAll(
                        TimePeriod.FindGaps(periods)
                                .stream()
                                .flatMap(gap -> gap.Explode(30, ChronoUnit.MINUTES).stream())
                                .collect(Collectors.toList())));

    }


    private Action onConfirmTimeSlots()
    {
        return new Action()
        {
            @Override
            protected void action() throws Exception
            {
                List<TimePeriod> timePeriods = TimePeriod.AggregateAdjacent(getSelectedTimeSlots());
                String timeSlots = timePeriods.stream().map(TimePeriod::toString).collect(Collectors.joining("\n"));

                _dialogService.ShowConfirmationDialog(DialogParameters.builder()
                        .title(String.format("Confirm requesting the following time slots\nfor %s?", getSelectedVenue().name()))
                        .body(timeSlots)
                        .onAccepted(() -> onConfirmed(timePeriods))
                        .build());
            }
        };
    }

    private void onConfirmed(List<TimePeriod> timePeriods)
    {

        LocalDate selectedDate = getSelectedDate();

        timePeriods.forEach(timePeriod ->
                _commandDispatcher.Dispatch(AddRequest.builder()
                        .venueId(getSelectedVenue().id())
                        .requesterId(_authentication.CurrentUser().get().id())
                        .date(selectedDate)
                        .startTime(timePeriod.startTime())
                        .endTime(timePeriod.endTime())
                        .build()
                ));

        onSelectedDateChanged(selectedDate);
        _dialogService.ShowMessageDialog(DialogParameters.builder()
                .title("Done!")
                .body("Requested")
                .acceptedText("OK")
                .build());

    }
}