package devhelp.bot.events;

import devhelp.bot.commands.fun.CoinFlip.CoinFlipCommand;
import devhelp.bot.commands.fun.Meme.MemeCommand;
import devhelp.bot.commands.main.Help.HelpCommand;
import devhelp.bot.commands.main.Ping.PingCommand;
import devhelp.bot.commands.study.Exercise.ExerciseCommand;
import devhelp.bot.commands.utility.Levels.Level;
import devhelp.bot.commands.utility.Profile.AddBio;
import devhelp.bot.commands.utility.Profile.AddGithub;
import devhelp.bot.commands.utility.Profile.SwitchColor;
import devhelp.bot.commands.utility.Profile.ViewCommand;
import devhelp.bot.commands.utility.Ranks.Levels;
import devhelp.bot.commands.utility.github.Streak;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

public class SlashCommandListener extends ListenerAdapter {
  @Override
  public void onSlashCommandInteraction(@NotNull SlashCommandInteractionEvent event) {
    String command = event.getName();

    if (command.equals("ping")) {
      new PingCommand().execute(event, null);
    }
    if (command.equals("help")) {
      new HelpCommand().execute(event, null);
    }
    if (command.equals("coinflip")) {
      new CoinFlipCommand().execute(event, null);
    }
    if (command.equals("exercise")) {
      new ExerciseCommand().execute(event, null);
    }
    if (command.equals("memes")) {
      new MemeCommand().execute(event, null);
    }
    if(command.equals("meme")){
      new MemeCommand().execute(event, null);
    }
    if(command.equals("level")){
      new Level().execute(event, null);
    }
    if(command.equals("levels")){
      new Level().execute(event, null);
    }
    if (command.equals("rank")){
      String subcomand = event.getSubcommandName();

      if (subcomand == null) return;
      
      switch (subcomand) {
        case "levels":
          new Levels().execute(event, null);
          break;
        default:
          break;
      }
    }
    if (command.equals("profile")) {
      String subcomand = event.getSubcommandName();

      if (subcomand == null) return;
      
      switch (subcomand) {
        case "view":
          new ViewCommand().execute(event, null);
          break;
        case "addgithub":
          new AddGithub().execute(event, null);
          break;
        case "switchcolor":
          new SwitchColor().execute(event, null);
          break;
        case "addbio":
          new AddBio().execute(event, null);
          break;
        default:
          break;
      }
    }
    if(command.equals("github")){
      String subcommand = event.getSubcommandName();

      if (subcommand == null) return;

      switch (subcommand) {
        case "streak":
          new Streak().execute(event, null);
          break;
        default:
          break;
      }
    }
  }
}
