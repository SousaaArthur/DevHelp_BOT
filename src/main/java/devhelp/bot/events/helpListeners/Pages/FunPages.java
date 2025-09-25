package devhelp.bot.events.helpListeners.Pages;

import java.util.HashMap;
import java.util.Map;

import devhelp.bot.config.util.EmbedTemplate;
import devhelp.bot.events.helpListeners.HelpInteractionListener;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.events.interaction.component.StringSelectInteractionEvent;
import net.dv8tion.jda.api.interactions.components.ActionRow;
import net.dv8tion.jda.api.interactions.components.selections.SelectOption;
import net.dv8tion.jda.api.interactions.components.selections.StringSelectMenu;

public class FunPages {
  private static final Map<String, MessageEmbed> pages = new HashMap<>();
  private static String choice = new HelpInteractionListener().getChoice();
  static{
    pages.put("memePages", new EmbedTemplate().embedInfo(null,
        """
            # Comando de Memes
            > Aqui estão os comandos para memes:
            ``/memes`` - **Comando para gerar memes aleatório de programação!**
            Caso queira um meme novo, basta usar o comando novamente. Alem disso, o meme possui dois botões: 
            > **Like**: para curtir o meme.
            > **Views**: para ver quantas vezes o meme foi visualizado.
            """, null));
  }

  public static MessageEmbed getPage(String key) {
      return pages.getOrDefault(key, errorPage());
  }

  private static MessageEmbed errorPage() {
      return new EmbedTemplate().embedError("❌ Erro", "Opção inválida!", null);
  }

  public static StringSelectMenu getSelectFunMenu(String selected){
    try{
      return StringSelectMenu.create("fun-menu")
      .setPlaceholder("Selecione um comando!")
      .addOptions(
        SelectOption.of("memes", "memes")
          .withDescription("Acesse comandos voltados para memes.")
          .withDefault("memes".equals(selected)),
        SelectOption.of("coinflip", "coinflip")
          .withDescription("Acesse comandos voltados para coinflip.")
          .withDefault("coinflip".equals(selected))
      )
      .build();
    } catch (Exception e){
      throw new RuntimeException(e);
    }
  }

  public static void optionsFun(StringSelectInteractionEvent event){
    switch (choice) {
      case "memes" -> createEmbedPage(event);
      default -> event.editMessageEmbeds(
        new EmbedTemplate().embedError("❌ Erro", "Opção inválida!", null)
      ).queue();
    }
  }

  public static void createEmbedPage(StringSelectInteractionEvent event){
    event.editMessageEmbeds(
        getPage(choice)
      ).setComponents(
        ActionRow.of(
          getSelectFunMenu(choice)
        )
      ).queue();
  }
}
