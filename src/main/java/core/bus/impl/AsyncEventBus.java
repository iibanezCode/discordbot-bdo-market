package core.bus.impl;

import core.bus.Event;
import core.bus.EventBus;
import core.bus.Subscribable;
import core.bus.entities.EventData;

import java.util.List;
import java.util.Vector;

public class AsyncEventBus implements EventBus {

    private List<Subscribable> subscribers;

    public AsyncEventBus() {
        subscribers = new Vector<>();
    }

    @Override
    public void register(Subscribable subscribable) {
    }

    @Override
    public void dispatch(Event<EventData> event) {

    }

    @Override
    public List<Subscribable> getSubscribers() {
        return null;
    }
}
