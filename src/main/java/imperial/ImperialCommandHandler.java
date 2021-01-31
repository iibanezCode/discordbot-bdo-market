package imperial;

import core.bus.EventBus;
import core.bus.EventBusFactory;
import imperial.bus.ImperialRequestHandler;
import imperial.bus.entities.ImperialRequestEventBuilder;
import org.javacord.api.entity.channel.Channel;
import org.javacord.api.entity.message.MessageSet;
import org.javacord.api.event.message.MessageCreateEvent;
import org.javacord.api.listener.message.MessageCreateListener;

import java.util.Collection;
import java.util.concurrent.ExecutionException;

public class ImperialCommandHandler implements MessageCreateListener {

    EventBus eventBus;
    Collection<Channel> channels;

    public ImperialCommandHandler(Collection<Channel> channelsByName) {
        eventBus = EventBusFactory.createSingletonSyncEventBus();
        channels = channelsByName;
        initializeEventBus();
    }

    private void initializeEventBus() {
        eventBus.register(ImperialRequestHandler.getInstance());
    }

    @Override
    public void onMessageCreate(MessageCreateEvent event) {

        if (event.getMessage().getContent().contains("_clear")){
            try {
                event.getChannel().getMessages(50).get().deleteAll();
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }

        if (event.getMessage().getContent().contains("_imperial")) {

            if (!channels.contains(event.getChannel())){
                event.getChannel().sendMessage("Ask again on #market-bot channel");
                return;
            }

            String[] args = event.getMessage().getContent().split(" ");

            ImperialRequestEventBuilder imperialRequestEventBuilder =
                    new ImperialRequestEventBuilder(event.getChannel())
                            .forCategory(args[1])
                            .forLevel(args[2]);

            for (String arg : args) {
                if (arg.contains("cp.")) {
                    imperialRequestEventBuilder = imperialRequestEventBuilder.maximumCPIs(arg.replace("cp.", "").trim());
                }
                if (arg.contains("top.")) {
                    imperialRequestEventBuilder = imperialRequestEventBuilder.numberOfResults(arg.replace("top.", "").trim());
                }
            }
            eventBus.dispatch(imperialRequestEventBuilder.build());
        }
    }
}
