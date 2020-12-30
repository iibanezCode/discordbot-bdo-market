package core.bus;

import core.bus.entities.EventData;

import java.util.Set;

public interface Subscribable {

    /**
     * Consume the events dispatched by the bus, events passed as parameter are can only be of type
     * declared by the supports() Set
     */
    void handle(Event<EventData> event);

    /**
     * describes the set of classes the subscribable object intends to handle
     */
    Set<Class<?>> supports();
}
