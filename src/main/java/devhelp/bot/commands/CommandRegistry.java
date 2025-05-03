package devhelp.bot.commands;

import devhelp.bot.Events.HelpInteractionListener;
import devhelp.bot.Events.MemeListeners.ButtonLike;
import devhelp.bot.Events.MemeListeners.ButtonViews;
import devhelp.bot.commands.funCommands.CoinFlip.CoinFlipCommand;
import devhelp.bot.commands.funCommands.Meme.MemeCommand;
import devhelp.bot.commands.mainCommands.Ping.PingCommand;
import devhelp.bot.commands.mainCommands.Help.HelpCommand;
import devhelp.bot.commands.studyCommands.ExerciseCommand;
import devhelp.bot.config.BotConfig;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.interactions.commands.OptionType;

public class CommandRegistry {

    public static void registerCommands(JDA jda) {
        try {
            jda.awaitReady();

            String guildId = BotConfig.getGuild();
            if (guildId == null || guildId.isEmpty()) {
                System.err.println("❌ ID_GUILD não definido no .env!");
                return;
            }

            Guild guild = jda.getGuildById(guildId);
            if (guild != null) {
                PingCommand pingCommand = new PingCommand();
                HelpCommand helpCommand = new HelpCommand();
                CoinFlipCommand coinFlipCommand = new CoinFlipCommand();
                ExerciseCommand exerciseCommand = new ExerciseCommand();
                MemeCommand memeCommand = new MemeCommand();

                guild.upsertCommand(pingCommand.getName(), pingCommand.getDescription()).queue();
                guild.upsertCommand(helpCommand.getName(), helpCommand.getDescription()).queue();
                guild.upsertCommand(coinFlipCommand.getName(), coinFlipCommand.getDescription())
                        .addOption(OptionType.STRING, coinFlipCommand.getOption(), coinFlipCommand.getDescriptionOption())
                        .queue();
                guild.upsertCommand(exerciseCommand.getName(), exerciseCommand.getDescription())
                        .addOption(OptionType.STRING, exerciseCommand.getOptionLanguage(), exerciseCommand.getDescriptionOptionLanguage(), true)
                        .addOption(OptionType.STRING, exerciseCommand.getOptionDificulty(), exerciseCommand.getDescriptionOptionDificulty(), true)
                        .queue();
                guild.upsertCommand(memeCommand.getName(), memeCommand.getDescription()).queue();

//                guild.retrieveCommands().queue(commands -> {
//                    for (var command : commands) {
//                        command.delete().queue();
//                    }
//                });

                jda.addEventListener(new HelpInteractionListener());
                jda.addEventListener(new ButtonLike());
                jda.addEventListener(new ButtonViews());

                System.out.println("✅ Comandos registrados na guilda: " + guild.getName());
            } else {
                System.err.println("❌ Guilda não encontrada para o ID: " + guildId);
                System.out.println("📜 Guildas disponíveis:");
                jda.getGuilds().forEach(g -> System.out.println("- " + g.getName() + " (" + g.getId() + ")"));
            }

        } catch (InterruptedException e) {
            System.err.println("💥 Erro ao aguardar o JDA estar pronto: " + e.getMessage());
        }
    }
}
