package test.view.backing;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;
import java.util.concurrent.ConcurrentHashMap;
import oracle.adf.view.rich.component.rich.data.RichCalendar;
import oracle.adf.view.rich.model.CalendarActivity;
import oracle.adf.view.rich.model.CalendarProvider;

public class NeelHOliday {
    private NeelmaniCalender holidays = new NeelmaniCalender();
    private RichCalendar calFeriado;
    private int _activityIdCounter = 1; // This should be monotonically increasing
    private List<CalendarProvider> _providers = new ArrayList<CalendarProvider>();
    private Map<String, Modelo> _models = new ConcurrentHashMap<String, Modelo>();
    private Wrapper _modelWrapper;

    public NeelHOliday () {
        CalendarProvider provider0 = new DemoCalendarProvider("me", "My Cal");
        /*CalendarProvider provider1 = new DemoCalendarProvider("Larry", "Larry");
        CalendarProvider provider2 = new DemoCalendarProvider("Ted", "Ted");
        */
        List<CalendarProvider> providers = new ArrayList<CalendarProvider>();
        providers.add(provider0);
        /*providers.add(provider1);
        providers.add(provider2);
        */
        Modelo model = new Modelo(provider0);
        _modelWrapper = new Wrapper();
        _modelWrapper.addCalendarModel(model);

        // Add regular CalendarActivities
        _addBasicActivities(model);
    }

    /**
     * Add another CalendarModel to be included.
     * Get the Time and AllDay activities and store them.
     * @param model
     */
    public void addCalendarModel(Modelo model) {
        CalendarProvider provider = model.getProvider();
        _providers.add(provider);
        _models.put(provider.getId(), model);
    }

    private void _addBasicActivities(Modelo model) {
        // Create CalendarActivity for current day at 9 with an hour duration.
        _addTimeActivity(model, null,"Reunion por hangout", 0, 9, 0, 0,2, 0);

        // Create a 2 hour overlapped activity from 9 am today activity
        //_addTimeActivity(model, null, null, 0, 9, 40, 0, 2, 0);

        // Almost all day activity
        //_addTimeActivity(model, null, null, 2, 0, 0, 0, 24, 0);

        // Add several time activities
        //_addNumActivities(model, 8, 3);

        // activity that ends at midnight
      //  _addTimeActivity(model, null, "ends at midnight", 2, 23, 0, 0, 1, 0, CalendarActivity.Recurring.SINGLE,
         //                CalendarActivity.Reminder.OFF);

        // activity less than 24h but go across midnight
       // _addTimeActivity(model, null, " spans across midnight", -7, 18, 0, 0, 14, 0);

        // activity that spans 7 days
      //  _addTimeActivity(model, null, " spans full week", -7, 0, 0, 14, 0, 0);

        // activity that starts on previous month to test bug 12804556
        Calendar cal = Calendar.getInstance();
        int currdate = cal.get(Calendar.DATE);
     //   _addTimeActivity(model, null, " spans end of previous month", -1 * (currdate + 1), 23, 0, 0, 4, 0);
    }

    private void _addTimeActivity(Modelo model, String location, String titleAddition, int startAddDays, int startHour,
                                  int startMin, int endAddDays, int endAddHours, int endAddMins) {
        _addTimeActivity(model, location, titleAddition, startAddDays, startHour, startMin, endAddDays, endAddHours,
                         endAddMins, CalendarActivity.Recurring.SINGLE, CalendarActivity.Reminder.OFF);
    }

    private void _addTimeActivity(Modelo model, String location, String titleAddition, int startAddDays, int startHour,
                                  int startMin, int endAddDays, int endAddHours, int endAddMins,
                                  CalendarActivity.Recurring recurring, CalendarActivity.Reminder reminder) {
        CalendarProvider provider = model.getProvider();
        Calendar startCalendar = _createCalendarFromToday(startAddDays, startHour, startMin);
        Calendar endCalendar = _createCalendar(startCalendar, endAddDays, endAddHours, endAddMins);
        Actividad ca = new Actividad(provider, String.valueOf(_activityIdCounter), startCalendar.getTime(), endCalendar.getTime(),
                          TimeZone.getDefault());
        ca.setLocation(location);
        String title = getTimedActivityTitle(titleAddition, startCalendar, endCalendar);
        ca.setTitle(title);
        ca.setRecurring(recurring);
        ca.setReminder(reminder);
        model.addTimeCalendarActivity(ca);
        _activityIdCounter++;
    }

    // creates a calendar relative to today
    // for example if I want to create a calendar 3 days from today for 2:30pm, I'd call
    // _createCalendar(3, 14, 30);
    // that's 3 for 3 days, 14 for 2pm, and 30 for 30 minutes.
    private Calendar _createCalendarFromToday(int addDays, int hourOfDay, int minutes) {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DAY_OF_YEAR, addDays);
        cal.set(Calendar.HOUR_OF_DAY, hourOfDay);
        cal.set(Calendar.MINUTE, minutes);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);

        return cal;
    }

    // creates a calendar relative to the start calendar
    // for example if I want to create a calendar for 1 hour 15 minutes past the start, I'd call
    // _createCalendar(starCalendar, 0, 1, 15);
    private Calendar _createCalendar(Calendar startCalendar, int addDays, int addHours, int addMinutes) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(startCalendar.getTime());
        cal.add(Calendar.DAY_OF_YEAR, addDays);
        cal.add(Calendar.HOUR_OF_DAY, addHours);
        cal.add(Calendar.MINUTE, addMinutes);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);

        return cal;
    }

    protected String getTimedActivityTitle(String titleAddition, Calendar startCalendar, Calendar endCalendar) {
        String title = "time " + getActivityIdCounter();


        int c1DayOfYear = startCalendar.get(Calendar.DAY_OF_YEAR);
        int c1Year = startCalendar.get(Calendar.YEAR);
        int c2DayOfYear = endCalendar.get(Calendar.DAY_OF_YEAR);
        int c2Year = endCalendar.get(Calendar.YEAR);

        if (c1DayOfYear != c2DayOfYear || c1Year != c2Year) {
            DateFormat formatter = DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.SHORT);
            title =
                title + " ends at " + formatter.format(endCalendar.getTime()) + " " +
                endCalendar.getTimeZone().getDisplayName(true, TimeZone.SHORT);
        }

        if (titleAddition != null) {
            title = title + " " + titleAddition;
        }
        return title;
    }

    // add a whole bunch of time activities
    private void _addNumActivities(Modelo model, int startHour, int frequency) {
        // loop to create a lot of one hour activities starting the day before today start at 8 am
        for (int i = 1; i <= 30; i++) {
            int days = (i - 2) * frequency;
            _addTimeActivity(model, null, null, days, startHour, 0, 0, 1, 0, CalendarActivity.Recurring.SINGLE,
                             CalendarActivity.Reminder.ON);
        }
    }

    public void setActivityIdCounter(int _activityIdCounter) {
        this._activityIdCounter = _activityIdCounter;
    }

    public int getActivityIdCounter() {
        return _activityIdCounter;
    }
    
    public Wrapper getCalendarModel(){
      return _modelWrapper;
    }

    public void setHolidays(NeelmaniCalender holidays) {
        this.holidays = holidays;
    }

    public NeelmaniCalender getHolidays() {
        return holidays;
    }

    public void setCalFeriado(RichCalendar calFeriado) {
        this.calFeriado = calFeriado;
    }

    public RichCalendar getCalFeriado() {
        return calFeriado;
    }
}
