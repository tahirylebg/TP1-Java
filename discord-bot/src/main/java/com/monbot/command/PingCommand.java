package com.monbot.command;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class PingCommand implements ICommand {

    @Override
    public String getName() { return "ping"; } // Nom de la commande

    @Override
    public String getDescription() { return "Répond Pong !"; } // Description de la commande

    @Override
    public void execute(MessageReceivedEvent event, String[] args) {
        event.getChannel().sendMessage("Pong !").queue();
    }
}