package devhelp.bot.Events;

import devhelp.bot.commands.funCommands.CoinFlip.CoinFlipCommand;
import devhelp.bot.commands.funCommands.Meme.MemeCommand;
import devhelp.bot.commands.mainCommands.Ping.PingCommand;
import devhelp.bot.commands.studyCommands.Exercise.ExerciseCommand;
import devhelp.bot.commands.mainCommands.Help.HelpCommand;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

public class SlashCommandListener extends ListenerAdapter {
    @Override
    public void onSlashCommandInteraction(@NotNull SlashCommandInteractionEvent event) {
        String command = event.getName();

        if (command.equals("ping")){
            new PingCommand().execute(event, null);
        }
        if (command.equals("help")){
            new HelpCommand().execute(event, null);
        }
        if (command.equals("coinflip")){
            new CoinFlipCommand().execute(event, null);
        }
        if (command.equals("exercise")){
            new ExerciseCommand().execute(event, null);
        }
        if (command.equals("memes")){
            new MemeCommand().execute(event, null);
        }

    }
}
