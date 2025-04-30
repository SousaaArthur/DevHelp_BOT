package devhelp.bot.events;

import devhelp.bot.commands.funCommands.CoinFlipCommand;
import devhelp.bot.commands.funCommands.PingCommand;
import devhelp.bot.commands.mainCommands.HelpCommand;
import devhelp.bot.commands.studyCommands.ExerciseCommand;
import net.dv8tion.jda.api.entities.MessageEmbed;
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

    }
}
