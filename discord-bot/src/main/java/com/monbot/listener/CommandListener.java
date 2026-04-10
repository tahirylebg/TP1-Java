package com.monbot.listener;

import com.monbot.BotConfig;
import com.monbot.command.CommandManager;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class CommandListener extends ListenerAdapter {
    private final CommandManager manager = new CommandManager();

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        if (event.getAuthor().isBot()) return;
        manager.handle(event, BotConfig.PREFIX);
    }
}