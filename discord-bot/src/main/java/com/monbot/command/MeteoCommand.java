package com.monbot.command;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.monbot.BotConfig;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import java.awt.Color;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class MeteoCommand implements ICommand {

    @Override
    public String getName() { return "meteo"; }

    @Override
    public String getDescription() { return "Affiche la meteo de ta ville . Usage: !meteo <ville>"; }

    @Override
    public void execute(MessageReceivedEvent event, String[] args) {
        if (args.length == 0) {
            event.getChannel().sendMessage("Usage : `!meteo <ville>`").queue();
            return;
        }

    
    String ville = String.join("+", args);

    try {
            String url = "https://api.openweathermap.org/data/2.5/weather?q=" 
                + ville 
                + "&appid=" + BotConfig.WEATHER_API_KEY 
                + "&units=metric&lang=fr";

            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == 404) {
                event.getChannel().sendMessage("Ville introuvable : `" + String.join(" ", args) + "`").queue();
                return;
            }

            JsonObject json = JsonParser.parseString(response.body()).getAsJsonObject();
            String nomVille = json.get("name").getAsString();
            String pays = json.getAsJsonObject("sys").get("country").getAsString();
            double temp = json.getAsJsonObject("main").get("temp").getAsDouble();
            double tempRessentie = json.getAsJsonObject("main").get("feels_like").getAsDouble();
            int humidite = json.getAsJsonObject("main").get("humidity").getAsInt();
            String description = json.getAsJsonArray("weather")
                .get(0).getAsJsonObject()
                .get("description").getAsString();
            double vent = json.getAsJsonObject("wind").get("speed").getAsDouble();

            EmbedBuilder embed = new EmbedBuilder();
            embed.setTitle("Météo à " + nomVille + " (" + pays + ")");
            embed.setColor(Color.CYAN);
            embed.addField("Conditions de " + nomVille, description, false);
            embed.addField("Température de : " + nomVille, Math.round(temp) + "°C (ressenti " + Math.round(tempRessentie) + "°C)", false);
            embed.addField("Humidité", humidite + "%", true);
            embed.addField("Vent", vent + " m/s", true);

            event.getChannel().sendMessageEmbeds(embed.build()).queue();

            } catch (Exception e) {
            event.getChannel().sendMessage("Erreur lors de la récupération de la météo.").queue();
        }
    }

}