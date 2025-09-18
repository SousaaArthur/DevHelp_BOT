package devhelp.bot.commands.main.Help;

import devhelp.bot.commands.ICommand;
import devhelp.bot.events.helpListeners.HelpCategory;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.components.selections.StringSelectMenu;

public class HelpCommand implements ICommand {
  String userId = "847867725048709212";

  @Override
  public void execute(SlashCommandInteractionEvent event, String[] args) {
    event.getJDA().retrieveUserById(userId).queue(user -> {
      String avatarUrl = user.getEffectiveAvatarUrl();
      String userName = user.getName();
      StringSelectMenu menu = StringSelectMenu.create("command-menu")
        .setPlaceholder("Escolha uma opção!")
        .addOption("Estudos 📚", "Estudos", "Acesse comandos voltados para aprendizado")
        .addOption("Diversão 🕹️", "Diversão", "Descubra comandos para se divertir")
        .addOption("Iniciante 👨‍💻", "Iniciante",
            "Obtenha orientações para começar na programação")
        .addOption("GitHub 🐙", "GitHub", "Confira o repositório oficial no GitHub")
        .addOption("Menu 💾", "Menu", "Volte ao menu principal")
        .build();

      event.replyEmbeds(HelpCategory.menu(userName, avatarUrl)).addActionRow(menu).setEphemeral(true).queue();
    });
  }

  @Override
  public String getName() {
    return "help";
  }

  @Override
  public String getDescription() {
    return "Exibe catálogo de instruções do bot operacional. 🤖";
  }

  @Override
  public String getUsage() {
    return "/help";
  }
}
