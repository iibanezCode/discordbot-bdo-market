package core.bus.impl;

import core.bus.Event;
import core.bus.EventBus;
import core.bus.Subscribable;
import core.bus.entities.EventData;

import java.util.List;
import java.util.Vector;

public class SyncEventBus implements EventBus {

    private List<Subscribable> subscribers;

    public SyncEventBus() {
        subscribers = new Vector<>();
    }

    @Override
    public void register(Subscribable subscribable) {
        subscribers.add(subscribable);
    }

    @Override
    public void dispatch(final Event<EventData> event) {
        subscribers.stream()
                .filter(subscriber -> subscriber.supports().contains(event.getClass()))
                .forEach(subscriber -> subscriber.handle(event));
    }

    @Override
    public List<Subscribable> getSubscribers() {
        return subscribers;
    }
}
