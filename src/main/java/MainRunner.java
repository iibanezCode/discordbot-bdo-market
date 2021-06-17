import imperial.ImperialCommandHandler;
import org.javacord.api.DiscordApi;
import org.javacord.api.DiscordApiBuilder;

public class MainRunner {

    public static void main(String[] args) {
        // Log the bot in
        DiscordApi api = new DiscordApiBuilder()
                .setToken(args[0])
                .login().join();

        api.addMessageCreateListener(new ImperialCommandHandler(api.getChannelsByName("market-bot")));
    }

}
