package devhelp.bot.events.helpListeners.Pages;

import java.util.HashMap;
import java.util.Map;

import devhelp.bot.config.util.Colors;
import devhelp.bot.services.EmbedBuilderService;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.events.interaction.component.StringSelectInteractionEvent;
import net.dv8tion.jda.api.interactions.components.ActionRow;
import net.dv8tion.jda.api.interactions.components.selections.StringSelectMenu;

public class HelpPages {
  private static final Map<String, MessageEmbed> pages = new HashMap<>();

  static {
    pages.put("menu", new EmbedBuilderService().embedInfo(null,
        """
              ## 📖 Central de Ajuda – Star Code
              Bem-vindo à central de ajuda do Star Code!
              Aqui você encontra todos os comandos disponíveis para explorar, aprender e se divertir na comunidade.

              **✨ Categorias de comandos:**

              **📚 Estudos**
              Comandos voltados para aprendizado e prática de programação.

              **🕹️ Diversão**
              Comandos interativos para relaxar e se conectar com a comunidade.

              **🧑‍💻 Iniciante**
              Recursos e atalhos para quem está começando na jornada dev.
            """, "Selecione uma categoria para ver os comandos disponíveis."));
    pages.put("Estudos", new EmbedBuilderService().embedInfo(null,
        """
            # 📚 Comandos de Estudos
            > Aqui estão os comandos para estudos:
            ``/exercise`` - Comando para gerar exercícios de programação!
            """, null));
    pages.put("Diversão", new EmbedBuilderService().embedInfo(null,
        """
            # 📚 Comandos de Diversão
            > Aqui estão os comandos para diversão:
            ``/memes`` - Comando para gerar memes aleatório de programação!
            ``/coinflip`` - Comando para girar a moeda!
            """, null));
    pages.put("Iniciante", new EmbedBuilderService().embedInfo(null,
        """
            # 📚 Comandos para Iniciante
            > Aqui estão os comandos para iniciante:
            ``/languages`` - Comando para exibir informações sobre linguagens de programação!
            ``/roadmap`` - Comando para exibir roadmaps de aprendizado!
            ``/git`` - Comando para exibir comandos git!
            """, null));
  }

  public static MessageEmbed getPage(String key) {
    return pages.getOrDefault(key, errorPage());
  }

  private static MessageEmbed errorPage() {
    return new EmbedBuilderService().embedError("❌ Erro", "Opção inválida!", null);
  }

  public static StringSelectMenu getSelectHelpMenu() {
    return StringSelectMenu.create("command-menu")
        .setPlaceholder("Escolha uma opção!")
        .addOption("Estudos 📚", "Estudos", "Acesse comandos voltados para aprendizado")
        .addOption("Diversão 🕹️", "Diversão", "Descubra comandos para se divertir")
        .addOption("Iniciante 👨‍💻", "Iniciante",
            "Obtenha orientações para começar na programação")
        .addOption("Menu 💾", "Menu", "Volte ao menu principal")
        .build();
  }

  public static void optiosHelp(String choice, StringSelectInteractionEvent event){
        switch (event.getValues().get(0)) {
      case "Estudos" -> event.editMessageEmbeds(
        getPage("Estudos")
      ).setComponents(
        ActionRow.of(
          getSelectHelpMenu()
        ),
        ActionRow.of(
          StudyPages.getSelectStudyMenu()
        )
      ).queue();
      case "Diversão" -> event.editMessageEmbeds(getPage("Diversão")).setComponents(
        ActionRow.of(
          getSelectHelpMenu()
        ),
        ActionRow.of(
          FunPages.getSelectFunMenu()
        )
      ).queue();
      case "Iniciante" -> event.editMessageEmbeds(getPage("Iniciante")).setComponents(
          ActionRow.of(
            getSelectHelpMenu()
          ),
          ActionRow.of(
            BeginnerPages.getSelectBeginnerMenu()
          )
      ).queue();
      // case "GitHub" -> event.editMessageEmbeds(templateEmbed(
      //     "GitHub", """
      //         Acesse o repositório oficial do bot no GitHub:
      //         [Clique aqui](https://github.com/filipedhunior/DevHelper)
      //         """)).queue();
      case "Menu" -> event.editMessageEmbeds(
          HelpPages.getPage("menu")
      ).setComponents(
        ActionRow.of(
          HelpPages.getSelectHelpMenu()
        )
      ).queue();
      default -> event.editMessageEmbeds(new EmbedBuilder()
          .setTitle("Erro")
          .setDescription("Opção inválida!")
          .setColor(Colors.getError())
          .build()).queue();
    }
  }
}
