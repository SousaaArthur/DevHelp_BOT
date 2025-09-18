package devhelp.bot.config;

import io.github.cdimascio.dotenv.Dotenv;

public class BotConfig {

  private static final Dotenv dotenv = Dotenv.load();

  public static String getToken() {
    return dotenv.get("TOKEN_BOT");
  }

  public static String getGuild() {
    return dotenv.get("ID_GUILD");
  }

  public static String getUrlDatabase() {
    return dotenv.get("URL_DATABASE");
  }

  public static String getGuildChannelLog() {
    return dotenv.get("ID_CHANNEL_LOGS");
  }
}
