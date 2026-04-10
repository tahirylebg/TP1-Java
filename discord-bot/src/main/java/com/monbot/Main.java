package com.monbot;

import com.monbot.listener.CommandListener;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.requests.GatewayIntent;

public class Main {
    public static void main(String[] args) throws Exception {
        JDABuilder.createDefault(BotConfig.TOKEN)
            .enableIntents(GatewayIntent.MESSAGE_CONTENT)
            .addEventListeners(new CommandListener())
            .build();

        System.out.println("Bot démarré !");
    }
}