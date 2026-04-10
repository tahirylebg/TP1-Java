package com.monbot;

import io.github.cdimascio.dotenv.Dotenv;

public class BotConfig {
    private static final Dotenv dotenv = Dotenv.load();
    public static final String TOKEN = dotenv.get("DISCORD_TOKEN");
    public static final String PREFIX = "!";
}