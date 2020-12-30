package imperial.bus.entities;

import core.bus.Event;
import core.bus.entities.EventData;

public class ImperialRequestEvent implements Event<EventData> {

    private EventData data;

    @Override
    public EventData getData() {
        return data;
    }

    public void setData(EventData data) {
        this.data = data;
    }
}
