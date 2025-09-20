package devhelp.bot.events.helpListeners.Pages;

import java.util.HashMap;
import java.util.Map;

import devhelp.bot.services.EmbedBuilderService;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.interactions.components.selections.StringSelectMenu;

public class FunPages {
  private static final Map<String, MessageEmbed> pages = new HashMap<>();

  static{

  }

  public static MessageEmbed getPage(String key) {
      return pages.getOrDefault(key, errorPage());
  }

  private static MessageEmbed errorPage() {
      return new EmbedBuilderService().embedError("❌ Erro", "Opção inválida!", null);
  }

  public static StringSelectMenu getSelectFunMenu(){
    return StringSelectMenu.create("fun_menu")
    .addOption("memes", "memes", "Acesse comandos voltados para memes.")
    .addOption("coinflip", "coinflip", "Acesse comandos voltados para coinflip.")
    .build();
  }
}
