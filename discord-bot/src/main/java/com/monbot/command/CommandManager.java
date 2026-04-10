package com.monbot.command;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import java.util.HashMap;
import java.util.Map;

public class CommandManager {
    private final Map<String, ICommand> commands = new HashMap<>(); // Stocke les commandes par nom

    public CommandManager() {
        register(new PingCommand());
    }

    // Permet d'ajouter une commande à la liste
    public void register(ICommand command) {
        commands.put(command.getName(), command);
    }

    public Map<String, ICommand> getCommands() {
        return commands;
    }

    // Traite les messages reçus et exécute les commandes si le préfixe est correct
    public void handle(MessageReceivedEvent event, String prefix) {
        String raw = event.getMessage().getContentRaw();
        if (!raw.startsWith(prefix)) return;

        String[] parts = raw.substring(prefix.length()).trim().split("\\s+", 2);
        String name = parts[0].toLowerCase();
        String[] args = parts.length > 1 ? parts[1].split("\\s+") : new String[0];

        ICommand command = commands.get(name);
        if (command == null) {
            event.getChannel().sendMessage("Commande inconnue. Tape `!help` pour la liste.").queue();
            return;
        }
        command.execute(event, args);
    }
}