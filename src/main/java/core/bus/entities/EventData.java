package core.bus.entities;

import org.javacord.api.entity.channel.TextChannel;

import java.util.HashMap;

public class EventData {

    private TextChannel channel;
    private String command;
    private HashMap<?, String> parameters;

    public EventData() {
    }

    public EventData(TextChannel channel, String command) {
        this.channel = channel;
        this.command = command;
    }

    public EventData(TextChannel channel, String command, HashMap<?, String> parameters) {
        this.channel = channel;
        this.command = command;
        this.parameters = parameters;
    }

    public TextChannel getChannel() {
        return channel;
    }

    public String getCommand() {
        return command;
    }

    public HashMap<?, String> getParameters() {
        return parameters;
    }
}
