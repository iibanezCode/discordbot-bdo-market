package core.bus;

import core.bus.entities.EventData;

import java.util.List;

public interface EventBus {

    /**
     * registers a new subscribable to this EventBus instance
     */
    void register(Subscribable subscribable);

    /**
     * send the given event in this EventBus implementation to be consumed by interested subscribers
     */
    void dispatch(Event<EventData> event);

    /**
     * get the list of all the subscribers associated with this EventBus instance
     */
    List<Subscribable> getSubscribers();
}
