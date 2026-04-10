package com.monbot.command;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import java.awt.Color;
import java.util.Map;

public class HelpCommand implements ICommand {

    private final CommandManager manager;

    public HelpCommand(CommandManager manager) {
        this.manager = manager;
    }

    @Override
    public String getName() { return "help"; }

    @Override
    public String getDescription() { return "Liste toutes les commandes disponibles"; }// Description de la commande

    @Override
    public void execute(MessageReceivedEvent event, String[] args) {
        EmbedBuilder embed = new EmbedBuilder();
        embed.setTitle("Commandes disponibles");
        embed.setColor(Color.BLUE);

        for (Map.Entry<String, ICommand> entry : manager.getCommands().entrySet()) {
            embed.addField("!" + entry.getKey(), entry.getValue().getDescription(), false);
        }

        event.getChannel().sendMessageEmbeds(embed.build()).queue(); // Envoie l'embed avec la liste des commandes
    }
}