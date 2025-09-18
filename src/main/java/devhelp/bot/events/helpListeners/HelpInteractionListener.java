package devhelp.bot.events.helpListeners;

import java.awt.Color;

import devhelp.bot.config.util.Colors;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.events.interaction.component.StringSelectInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class HelpInteractionListener extends ListenerAdapter {
  private static final Color EMBED_COLOR = Colors.getPrimary();

  @Override
  public void onStringSelectInteraction(StringSelectInteractionEvent event) {
    String userId = "847867725048709212";
    if (!event.getComponentId().equals("command-menu"))
      return;

    switch (event.getValues().get(0)) {
      case "Estudos" -> event.editMessageEmbeds(templateEmbed(
          "Comandos de Estudos", """
              Aqui estão os comandos voltados para aprendizado:
              `/startcode` - Roadmap de estudos
              `/pomodoro` - Modo produtividade
              `/links` - Links úteis para programação
              """)).queue();
      case "Diversão" -> event.editMessageEmbeds(templateEmbed(
          "Comandos de Diversão", """
              Aqui estão os comandos para diversão:
              `/desafio` - Desafios de lógica
              `/curiosidade` - Curiosidades sobre programação
              `/meme` - Memes do mundo tech
              """)).queue();
      case "Iniciante" -> event.editMessageEmbeds(templateEmbed(
          "Comandos para Iniciantes", """
              Aqui estão os comandos para iniciantes:
              `/linguagens` - Aprenda sobre linguagens básicas
              `/exercicios` - Exercícios simples
              `/dicas` - Dicas práticas para começar
              """)).queue();
      case "GitHub" -> event.editMessageEmbeds(templateEmbed(
          "GitHub", """
              Acesse o repositório oficial do bot no GitHub:
              [Clique aqui](https://github.com/filipedhunior/DevHelper)
              """)).queue();
      case "Menu" -> event.getJDA().retrieveUserById(userId).queue(user -> {
        String avatarUrl = user.getEffectiveAvatarUrl();
        String userName = user.getName();
        event.editMessageEmbeds(HelpCategory.menu(userName, avatarUrl)).queue();
      });
      default -> event.editMessageEmbeds(new EmbedBuilder()
          .setTitle("Erro")
          .setDescription("Opção inválida!")
          .setColor(EMBED_COLOR)
          .build()).queue();
    }
  }

  public MessageEmbed templateEmbed(String title, String description) {
    return new EmbedBuilder()
        .setTitle(title)
        .setDescription(description)
        .setColor(EMBED_COLOR).build();
  }
}
