package com.monbot.command;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class PollCommand implements ICommand {

    @Override
    public String getName() { return "poll"; }

    @Override
    public String getDescription() { return "Crée un sondage. Usage: !poll <question> | <option1> | <option2>"; }

    @Override
    public void execute(MessageReceivedEvent event, String[] args) {
        String raw = event.getMessage().getContentRaw();
        String content = raw.substring("!poll".length()).trim();

        if (!content.contains("|")) {
            event.getChannel().sendMessage("Usage : `!poll <question> | <option1> | <option2>`").queue();
            return;
        }

        String[] parts = content.split("\\|");
        if (parts.length < 3) {
            event.getChannel().sendMessage("Usage : `!poll <question> | <option1> | <option2>`").queue();
            return;
        }

        String question = parts[0].trim();
        String option1 = parts[1].trim();
        String option2 = parts[2].trim();

        String message = "**Sondage : " + question + "**\n " + option1 + "\n " + option2;

        event.getChannel().sendMessage(message).queue(msg -> {
            msg.addReaction(net.dv8tion.jda.api.entities.emoji.Emoji.fromUnicode("yes")).queue();
            msg.addReaction(net.dv8tion.jda.api.entities.emoji.Emoji.fromUnicode("no")).queue();
        });
    }
}