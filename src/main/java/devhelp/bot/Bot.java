package devhelp.bot;

import devhelp.bot.commands.CommandRegistry;
import devhelp.bot.config.BotConfig;
import devhelp.bot.Events.SlashCommandListener;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;


import javax.security.auth.login.LoginException;

public class Bot {
    public static void main(String[] args) {
        try {
            startBot();
            System.out.println("Bot inicido com sucesso ✅");
        } catch (LoginException e) {
            System.err.println("Erro ao iniciar o Token do bot");
        }
    }

    public static void startBot() throws LoginException {
        JDA jda = JDABuilder
                .createDefault(BotConfig.getToken())
                .setActivity(Activity.playing("Digite /help"))
                .setStatus(OnlineStatus.IDLE)
                .build();

        CommandRegistry.registerCommands(jda);
        jda.addEventListener(new SlashCommandListener());
    }
}