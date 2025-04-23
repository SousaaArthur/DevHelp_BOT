package devhelp.bot.commands.registry;

import devhelp.bot.commands.funCommands.PingCommand;
import devhelp.bot.commands.mainCommands.HelpCommand;
import devhelp.bot.config.BotConfig;
import devhelp.bot.events.HelpInteractionListener;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.Guild;

public class CommandRegistry {

    public static void registerCommands(JDA jda) {
        try {
            jda.awaitReady();

            String guildId = BotConfig.getGuild();
            if (guildId == null || guildId.isEmpty()){
                System.err.println("❌ ID_GUILD não definido no .env!");
                return;
            }

            Guild guild = jda.getGuildById(guildId);
            if (guild != null) {
                PingCommand pingCommand = new PingCommand();
                HelpCommand helpCommand = new HelpCommand();

                guild.upsertCommand(pingCommand.getName(), pingCommand.getDescription()).queue();
                guild.upsertCommand(helpCommand.getName(), helpCommand.getDescription()).queue();

                jda.addEventListener(new HelpInteractionListener());

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
