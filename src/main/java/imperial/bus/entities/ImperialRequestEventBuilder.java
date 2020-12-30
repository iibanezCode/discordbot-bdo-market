package imperial.bus.entities;

import core.bus.Event;
import core.bus.entities.EventData;
import imperial.domain.ImperialParams;
import org.javacord.api.entity.channel.TextChannel;

import java.util.HashMap;

public class ImperialRequestEventBuilder {

    private final TextChannel replyOnChannel;
    private String category;
    private String level;
    private String cp = null;
    private String limit = null;

    public ImperialRequestEventBuilder(TextChannel replyOnChannel) {
        this.replyOnChannel = replyOnChannel;
    }

    public ImperialRequestEventBuilder forCategory(String category) {
        this.category = category;
        return this;
    }

    public ImperialRequestEventBuilder forLevel(String level) {
        this.level = level;
        return this;
    }

    public ImperialRequestEventBuilder maximumCPIs(String cp) {
        this.cp = cp;
        return this;
    }

    public ImperialRequestEventBuilder numberOfResults(String limit) {
        this.limit = limit;
        return this;
    }

    public Event build() {
        ImperialRequestEvent event = new ImperialRequestEvent();

        HashMap<ImperialParams, String> paramMap = new HashMap<>();
        paramMap.put(ImperialParams.CATEGORY, category);
        paramMap.put(ImperialParams.LEVEL, level);

        if (cp != null) paramMap.put(ImperialParams.CP, cp);

        if (limit != null) paramMap.put(ImperialParams.LIMIT, limit);

        event.setData(new EventData(replyOnChannel, "imperial", paramMap));
        return event;
    }
}
