package devhelp.bot;

import devhelp.bot.commands.CommandRegistry;
import devhelp.bot.config.BotConfig;
import devhelp.bot.events.SlashCommandListener;
import devhelp.bot.events.voiceListeners.VoiceUpdateListener;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.requests.GatewayIntent;

import javax.security.auth.login.LoginException;

public class Bot {
  public static void main(String[] args) {
    try {
      startBot();
    } catch (LoginException e) {
      System.err.println("Erro ao iniciar o Token do bot");
    }
  }

  public static void startBot() throws LoginException {
    JDA jda = JDABuilder
        .createDefault(BotConfig.getToken(),
        GatewayIntent.GUILD_MEMBERS,
        GatewayIntent.GUILD_MESSAGES,
        GatewayIntent.MESSAGE_CONTENT,
        GatewayIntent.GUILD_VOICE_STATES
        )
        .setActivity(Activity.playing("Digite /help"))
        .setStatus(OnlineStatus.ONLINE)
        .addEventListeners(new SlashCommandListener(), new VoiceUpdateListener())
        .build();
    CommandRegistry.registerCommands(jda);
  }
}