package test.view.backing;

import java.io.Serializable;

import java.util.Date;
import java.util.Map;
import java.util.Set;
import java.util.TimeZone;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArraySet;

import oracle.adf.view.rich.model.CalendarActivity;
import oracle.adf.view.rich.model.CalendarActivity.Recurring;
import oracle.adf.view.rich.model.CalendarActivity.Reminder;
import oracle.adf.view.rich.model.CalendarActivity.TimeType;
import oracle.adf.view.rich.model.CalendarProvider;

public class Actividad extends CalendarActivity implements Serializable {
    public static final String STATUS = "status";
    public static final String PRIORITY = "priority";
    public static final String ACCESS = "access";


    /**
     * Possible priority of calendar activity.
     */
    public enum Priority {
        LOW,
        MEDIUM,
        HIGH
    }

    /**
     * Possible status of calendar activity
     */
    public enum Status {
        CANCELLED,
        TENTATIVE,
        CONFIRMED
    }

    /**
     * Access or class privilege information of calendar activity.
     */
    public enum Access {
        PRIVATE,
        CONFIDENTIAL,
        PUBLIC
    }


    public Actividad(CalendarProvider provider, String id, Date startDate, Date endDate, TimeZone tz) {
        _startDay = new Day(startDate, tz);
        _endDay = new Day(endDate, tz);
        _startDate = startDate;
        _endDate = endDate;
        _timeType = TimeType.TIME;
        _init(provider, id);
    }

    public Actividad(CalendarProvider provider, String id, Day startDay, Day endDay, TimeZone tz) {
        _startDay = startDay;
        _endDay = endDay;
        _startDate = startDay.createDate(tz);
        _endDate = endDay.createDate(tz);
        _timeType = TimeType.ALLDAY;
        _init(provider, id);
    }

    @Override
    public void setTags(Set<String> tags) {
        _tags = tags;
    }


    @Override
    public boolean isReadOnly() {
        return false;
    }

    @Override
    public Map<String, Object> getCustomAttributes() {
        return _customAttrs;
    }


    @Override
    public Set<String> getTags() {
        return _tags;
    }

    @Override
    public String getId() {
        return _id;
    }

    @Override
    public Date getEndDate(TimeZone tz) {
        if (isAllDay()) {
            // the doc states this should return a new date instance
            return _endDay.createDate(tz);
        }

        // the doc states this should return a new date instance
        return (Date) _endDate.clone();
    }

    @Override
    public Date getStartDate(TimeZone tz) {
        if (isAllDay()) {
            // the doc states this should return a new date instance
            return _startDay.createDate(tz);
        }

        // the doc states this should return a new date instance
        return (Date) _startDate.clone();
    }


    @Override
    public void setStartDate(Date startDate, TimeZone tz) {
        // TODO - setting 2 dates every time.
        _startDay = new Day(startDate, tz);
        _startDate = new Date(startDate.getTime());
    }

    @Override
    public void setEndDate(Date endDate, TimeZone tz) {
        // TODO - setting 2 dates every time.
        _endDay = new Day(endDate, tz);
        _endDate = new Date(endDate.getTime());
    }


    @Override
    public void setTitle(String title) {
        _title = title;
    }

    @Override
    public String getTitle() {
        return _title;
    }

    @Override
    public void setTimeType(TimeType timeType) {
        _timeType = timeType;
    }

    @Override
    public TimeType getTimeType() {
        return _timeType;
    }

    @Override
    public CalendarProvider getProvider() {
        return _provider;
    }

    public void setProvider(CalendarProvider p) {
        _tags.remove(_provider.getId());
        _provider = p;
        _tags.add(p.getId());
    }

    @Override
    public void setLocation(String location) {
        _location = location;
    }

    @Override
    public String getLocation() {
        return _location;
    }


    @Override
    public void setRecurring(Recurring recurring) {
        _recurring = recurring;
    }

    @Override
    public Recurring getRecurring() {
        return _recurring;
    }

    @Override
    public void setReminder(CalendarActivity.Reminder reminder) {
        _reminder = reminder;
    }

    @Override
    public CalendarActivity.Reminder getReminder() {
        return _reminder;
    }


    public void setStart(Date startDate) {
        // TODO - should we remove this and only allow setStartDate? Same for all setStartDay, setEnd, etc
        _startDate = startDate;
    }

    public Date getStart() {
        return _startDate;
    }

    public void setEnd(Date endDate) {
        _endDate = endDate;
    }

    public Date getEnd() {
        return _endDate;
    }

    public void setStartDay(Day startDay) {
        _startDay = startDay;
    }

    public Day getStartDay() {
        return _startDay;
    }

    public void setEndDay(Day endDay) {
        if ("oneday".equals(getTitle())) {
            System.out.println("DemoCalendarActivity setEndDay " + endDay.getDayOfMonth());
        }
        _endDay = endDay;
    }

    public Day getEndDay() {
        return _endDay;
    }

    public boolean isAllDay() {
        return TimeType.ALLDAY.equals(getTimeType());
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("\n     Title: ").append(getTitle());
        builder.append("\n        Id: ").append(getId());
        builder.append("\nProviderId: ").append(getProvider().getId());

        return builder.toString();
    }

    private void _init(CalendarProvider provider, String id) {

        _id = id;
        _provider = provider;
        _tags = new CopyOnWriteArraySet<String>();
        _tags.add(provider.getId());

        _customAttrs.put(STATUS, Status.TENTATIVE);
        _customAttrs.put(PRIORITY, Priority.MEDIUM);
        _customAttrs.put(ACCESS, Access.CONFIDENTIAL);

    }

    private Date _startDate;
    private Date _endDate;
    private Day _startDay;
    private Day _endDay;
    private TimeType _timeType;
    private String _id;
    private CalendarProvider _provider;
    private String _location;
    private String _title;
    private Set<String> _tags;
    private CalendarActivity.Recurring _recurring = Recurring.SINGLE;
    private CalendarActivity.Reminder _reminder = Reminder.OFF;
    private Map<String, Object> _customAttrs = new ConcurrentHashMap<String, Object>();

}
