package devhelp.bot.commands.main.Help;

import devhelp.bot.commands.ICommand;
import devhelp.bot.events.helpListeners.Pages.HelpPages;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;

public class HelpCommand implements ICommand {
  @Override
  public void execute(SlashCommandInteractionEvent event, String[] args) {
      event.replyEmbeds(HelpPages.getPage("menu")).addActionRow(HelpPages.getSelectHelpMenu("Menu")).setEphemeral(true).queue();
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
