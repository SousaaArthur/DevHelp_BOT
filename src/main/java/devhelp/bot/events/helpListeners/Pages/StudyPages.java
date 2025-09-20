package devhelp.bot.events.helpListeners.Pages;

import java.util.HashMap;
import java.util.Map;

import devhelp.bot.services.EmbedBuilderService;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.interactions.components.selections.StringSelectMenu;

public abstract class StudyPages {
  private static final Map<String, MessageEmbed> pages = new HashMap<>();

  static {

  }

  public static StringSelectMenu getStudy() {
    return StringSelectMenu.create("study-menu")
        .setPlaceholder("Escolha uma opção!")
        .addOption("Exercícios 📚", "Exercícios", "Acesse comandos voltados para exercícios de programação")
        .build();
  }

  public static MessageEmbed getPage(String key) {
    return pages.getOrDefault(key, errorPage());
  }

  private static MessageEmbed errorPage() {
    return new EmbedBuilderService().embedError("❌ Erro", "Opção inválida!", null);
  }

  public static StringSelectMenu getSelectStudyMenu() {
    return StringSelectMenu.create("estudos")
        .addOption("Exercise", "exercise", "Acesse comandos voltados para exercícios de programação.")
        .build();
  }
}
