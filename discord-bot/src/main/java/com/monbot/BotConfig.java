package com.monbot;

import io.github.cdimascio.dotenv.Dotenv;

public class BotConfig {
    private static final Dotenv dotenv = Dotenv.load();
    public static final String TOKEN = dotenv.get("DISCORD_TOKEN");
    public static final String PREFIX = "!";
    public static final String WEATHER_API_KEY = dotenv.get("OPENWEATHER_API_KEY");
}