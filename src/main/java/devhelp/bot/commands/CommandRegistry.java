package devhelp.bot.commands;

import devhelp.bot.commands.funCommands.CoinFlipCommand;
import devhelp.bot.commands.funCommands.PingCommand;
import devhelp.bot.commands.mainCommands.HelpCommand;
import devhelp.bot.commands.studyCommands.ExerciseCommand;
import devhelp.bot.config.BotConfig;
import devhelp.bot.events.HelpInteractionListener;
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

                guild.upsertCommand(pingCommand.getName(), pingCommand.getDescription()).queue();
                guild.upsertCommand(helpCommand.getName(), helpCommand.getDescription()).queue();
                guild.upsertCommand(coinFlipCommand.getName(), coinFlipCommand.getDescription())
                        .addOption(OptionType.STRING, coinFlipCommand.getOption(), coinFlipCommand.getDescriptionOption())
                        .queue();
                guild.upsertCommand(exerciseCommand.getName(), exerciseCommand.getDescription())
                        .addOption(OptionType.STRING, exerciseCommand.getOptionLanguage(), exerciseCommand.getDescriptionOptionLanguage(), true)
                        .addOption(OptionType.STRING, exerciseCommand.getOptionDificulty(), exerciseCommand.getDescriptionOptionDificulty(), true)
                        .queue();


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
