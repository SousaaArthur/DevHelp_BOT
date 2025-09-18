package devhelp.bot.commands;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;

public interface ICommand {

  void execute(SlashCommandInteractionEvent event, String[] args);

  String getName();

  String getDescription();

  String getUsage();

}
