package devhelp.bot.commands.main.Help;

import devhelp.bot.commands.ICommand;
import devhelp.bot.events.helpListeners.Pages.HelpPages;
import devhelp.bot.services.EmbedBuilderService;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;

public class HelpCommand implements ICommand {
  String userId = "847867725048709212";
  @Override
  public void execute(SlashCommandInteractionEvent event, String[] args) {
    event.getJDA().retrieveUserById(userId).queue(user -> {
      event.replyEmbeds(HelpPages.getPage("menu")).addActionRow(HelpPages.getSelectHelpMenu()).setEphemeral(true).queue(embeds -> {
        String embedId = embeds.getId();
        new EmbedBuilderService().setEmbedId(embedId);
      });
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
