package devhelp.bot.events.helpListeners.Pages;

import java.util.HashMap;
import java.util.Map;

import devhelp.bot.config.util.EmbedTemplate;
import net.dv8tion.jda.api.entities.MessageEmbed;

public class UtilityPages {
  private static Map<String, MessageEmbed> pages = new HashMap<>();

  static{

  }

  public static MessageEmbed getPages(String key){
    return pages.getOrDefault(key, erroPage());
  }

  private static MessageEmbed erroPage() {
    return new EmbedTemplate().embedError("❌ Erro", "Opção invalida!", null);
  }
}
