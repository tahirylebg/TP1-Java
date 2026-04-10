package com.monbot.listener;

import com.monbot.BotConfig;
import com.monbot.command.CommandManager;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class CommandListener extends ListenerAdapter {
    private final CommandManager manager = new CommandManager();
    private static final DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        if (event.getAuthor().isBot()) return;

        String raw = event.getMessage().getContentRaw();
        if (raw.startsWith(BotConfig.PREFIX)) {
            System.out.println("[" + LocalDateTime.now().format(fmt) + "] "
                + event.getAuthor().getName() + " : " + raw);
        }

        manager.handle(event, BotConfig.PREFIX);
    }
}