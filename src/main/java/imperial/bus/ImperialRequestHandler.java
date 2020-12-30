package imperial.bus;

import core.bus.Event;
import core.bus.Subscribable;
import core.bus.entities.EventData;
import imperial.bus.entities.ImperialRequestEvent;
import imperial.domain.ImperialBox;
import imperial.domain.ImperialBoxCategory;
import imperial.response.ImperialResponseBuilder;
import org.javacord.api.DiscordApi;
import org.javacord.api.entity.channel.TextChannel;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ImperialRequestHandler implements Subscribable {

    private static ImperialRequestHandler _instance = null;

    public static ImperialRequestHandler getInstance() {
        if (_instance == null) {
            _instance = new ImperialRequestHandler();
            return _instance;
        }
        return _instance;
    }


    @Override
    public void handle(Event<EventData> event) {

        EventData eventData = event.getData();


        if (eventData.getCommand().contains("imperial")) {
            List<ImperialBoxCategory> ibcs = ImperialResponseBuilder.getInstance().buildImperialResponse(eventData.getParameters());
            ibcs = ImperialResponseBuilder.getInstance().filterResultsFromParams(ibcs, eventData.getParameters());
            String message = sendImperialResearch(ibcs.get(0));
            eventData.getChannel().sendMessage(message);
        }
    }

    @Override
    public Set<Class<?>> supports() {
        Set<Class<?>> set = new HashSet<>();
        set.add(ImperialRequestEvent.class);
        return set;
    }

    public String sendImperialResearch(ImperialBoxCategory ibc) {
        String message = "";
        for (ImperialBox ib : ibc.getImperialBoxes()) {

            if (ib.getCurrentMarketCostPerBox() < ib.getPricePerBox()) {
                message += ":green_heart: ";
            } else {
                message += ":broken_heart: ";
            }

            message += "**" + ib.getItem().getName() + "** " + ib.getAmountPerBox() + "/box\n";
            message += "Item Cost: " + ib.getItem().getPricePerOne() +
                    " - Profit per Box:" + (ib.getPricePerBox() - ib.getCurrentMarketCostPerBox()) +
                    " (" + (ib.getPricePerBox() * 100) / ib.getCurrentMarketCostPerBox() + "%) - " +
                    "Amount on Market: " + ib.getItem().getCount() + " \n\n";
        }
        return message;
    }
}
