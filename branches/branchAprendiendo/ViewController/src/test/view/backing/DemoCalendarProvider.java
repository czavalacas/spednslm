package test.view.backing;

import java.io.Serializable;

import oracle.adf.view.rich.model.CalendarProvider;

public class DemoCalendarProvider extends CalendarProvider implements Serializable {

    public DemoCalendarProvider(String id, String displayName) {
        _id = id;
        _displayName = displayName;
    }

    @Override
    public String getDisplayName() {
        return _displayName;
    }

    @Override
    public String getId() {
        return _id;
    }


    @Override
    public CalendarProvider.Enabled getEnabled() {
        return _enabled; 
    }

    @Override
    public void setEnabled(CalendarProvider.Enabled enabled) {
        _enabled = enabled;
    }

    private String _id = null;
    private String _displayName = null;
    private CalendarProvider.Enabled _enabled = CalendarProvider.Enabled.ENABLED;
}
