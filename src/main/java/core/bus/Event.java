package core.bus;

public interface Event<EventData> {

    /**
     * @returns the stored data associated with the event
     */
    EventData getData();
}
