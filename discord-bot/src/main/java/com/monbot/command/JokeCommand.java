package com.monbot.command;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class JokeCommand implements ICommand {

    @Override
    public String getName() { return "joke"; }

    @Override
    public String getDescription() { return "Affiche une blague aléatoire"; }

    @Override
    public void execute(MessageReceivedEvent event, String[] args) {
        try {
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://official-joke-api.appspot.com/random_joke"))
                .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            JsonObject json = JsonParser.parseString(response.body()).getAsJsonObject();
            String setup = json.get("setup").getAsString();
            String punchline = json.get("punchline").getAsString();

            event.getChannel().sendMessage("**" + setup + "**\n||" + punchline + "||").queue();

        } catch (Exception e) {
            event.getChannel().sendMessage("Impossible de récupérer une blague pour le moment.").queue();
        }
    }
}