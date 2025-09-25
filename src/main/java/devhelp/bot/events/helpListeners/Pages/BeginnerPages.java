package devhelp.bot.events.helpListeners.Pages;

import java.util.HashMap;
import java.util.Map;

import devhelp.bot.config.util.EmbedTemplate;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.interactions.components.selections.StringSelectMenu;

public class BeginnerPages {
  private static final Map<String, MessageEmbed> pages = new HashMap<>();

  static{
    
  }

  public static MessageEmbed getPage(String key) {
      return pages.getOrDefault(key, errorPage());
  }

  private static MessageEmbed errorPage() {
      return new EmbedTemplate().embedError("❌ Erro", "Opção inválida!", null);
  }

  public static StringSelectMenu getSelectBeginnerMenu(){
    return StringSelectMenu.create("beginner_menu")
    .setPlaceholder("Selecione um comando!")
    .addOption("languages", "languages", "Acesse comandos voltados para linguagens de programação.")
    .addOption("roadmap", "roadmap", "Acesse comandos sobre o roadmap de aprendizado de programação.")
    .addOption("git", "git", "Acesse comandos sobre o git.")
    .build();
  }
}
