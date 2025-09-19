package devhelp.bot.commands;

import devhelp.bot.commands.fun.CoinFlip.CoinFlipCommand;
import devhelp.bot.commands.fun.Meme.MemeCommand;
import devhelp.bot.commands.main.Help.HelpCommand;
import devhelp.bot.commands.main.Ping.PingCommand;
import devhelp.bot.commands.study.Exercise.ExerciseCommand;
import devhelp.bot.commands.utility.Embeds.CreateEmbeds;
import devhelp.bot.commands.utility.Levels.Level;
import devhelp.bot.commands.utility.Profile.AddBio;
import devhelp.bot.commands.utility.Profile.AddGithub;
import devhelp.bot.commands.utility.Profile.SwitchColor;
import devhelp.bot.commands.utility.Profile.ViewCommand;
import devhelp.bot.commands.utility.Ranks.Levels;
import devhelp.bot.commands.utility.github.Streak;
import devhelp.bot.config.BotConfig;
import devhelp.bot.events.MessageListener;
import devhelp.bot.events.MemeListeners.ButtonLike;
import devhelp.bot.events.MemeListeners.ButtonViews;
import devhelp.bot.events.helpListeners.HelpInteractionListener;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.SubcommandData;

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
        CreateEmbeds createEmbeds = new CreateEmbeds();
        ViewCommand viewCommand = new ViewCommand();
        AddGithub addGithubCommand = new AddGithub();
        SwitchColor switchColorCommand = new SwitchColor();
        AddBio addBioCommand = new AddBio();
        Level levelCommand = new Level();
        Levels levelsCommand = new Levels();
        Streak streakCommand = new Streak();

        guild.upsertCommand(pingCommand.getName(), pingCommand.getDescription()).queue();
        guild.upsertCommand(helpCommand.getName(), helpCommand.getDescription()).queue();
        guild.upsertCommand(memeCommand.getName(), memeCommand.getDescription()).queue();
        // guild.upsertCommand(levelsCommand.getName(), levelsCommand.getDescription())
        //     .addOption(OptionType.USER, levelsCommand.getOptionName(), levelsCommand.getOptionDescription(), false)
        //     .queue();
        guild.upsertCommand(levelCommand.getName(), levelCommand.getDescription())
              .addOption(OptionType.USER, levelCommand.getOptionName(), levelCommand.getOptionDescription(), false)
              .queue();
        guild.upsertCommand(coinFlipCommand.getName(), coinFlipCommand.getDescription())
            .addOption(OptionType.STRING, coinFlipCommand.getOption(), coinFlipCommand.getDescriptionOption())
            .queue();
        guild.upsertCommand(exerciseCommand.getName(), exerciseCommand.getDescription())
            .addOption(OptionType.STRING, exerciseCommand.getOptionLanguage(),
                exerciseCommand.getDescriptionOptionLanguage(), true)
            .addOption(OptionType.STRING, exerciseCommand.getOptionDificulty(),
                exerciseCommand.getDescriptionOptionDificulty(), true)
            .queue();
        guild.upsertCommand(createEmbeds.getName(), createEmbeds.getDescription())
            .addOption(OptionType.STRING, createEmbeds.getOptionTitleEmbed(), createEmbeds.getDescriptionTitleEmbed(),
                false)
            .addOption(OptionType.STRING, createEmbeds.getOptionDescriptionEmbed(),
                createEmbeds.getDescriptionDescriptionEmbed(), false)
            .addOption(OptionType.STRING, createEmbeds.getOptionColorEmbed(), createEmbeds.getDescriptionColorEmbed(),
                false)
            .addOption(OptionType.STRING, createEmbeds.getOptionImageEmbed(), createEmbeds.getDescriptionImageEmbed(),
                false)
            .addOption(OptionType.STRING, createEmbeds.getOptionThumbnail(), createEmbeds.getDescriptionThumbnail(),
                false)
            .queue();
          guild.upsertCommand("profile", "Comandos relacionados ao perfil do usuário")
          .addSubcommands(
              new SubcommandData(viewCommand.getName(), viewCommand.getDescription())
                  .addOption(OptionType.USER, viewCommand.getOption(), viewCommand.getOptionDescription(), false),
              new SubcommandData(addGithubCommand.getName(), addGithubCommand.getDescription())
                  .addOption(OptionType.STRING, addGithubCommand.getOptionName(), addGithubCommand.getOptionDescription(), true),
              new SubcommandData(switchColorCommand.getName(), switchColorCommand.getDescription())
                  .addOption(OptionType.STRING, switchColorCommand.getOptionName(), switchColorCommand.getOptionDescription(), true),
              new SubcommandData(addBioCommand.getName(), addBioCommand.getDescription())
                  .addOption(OptionType.STRING, addBioCommand.getOptionName(), addBioCommand.getOptionDescription(), true)
          ).queue();
        guild.upsertCommand("rank", "Comando para mostrar o top ranks do servidor.")
        .addSubcommands(
          new SubcommandData(levelsCommand.getName(), levelsCommand.getDescription())
              .addOption(OptionType.USER, levelsCommand.getOptionName(), levelsCommand.getOptionDescription(), false)
        )
        .queue();
        guild.upsertCommand("github", "Comando para mostra informações do usuário no Github")
          .addSubcommands(
            new SubcommandData(streakCommand.getName(), streakCommand.getDescription())
              .addOption(OptionType.USER, streakCommand.getOptionName(), streakCommand.getOptionDescription())
          )
          .queue();
        // jda.retrieveCommands().queue(commands -> {
        //   for (var command : commands) {
        //     command.delete().queue();
        //     System.out.println("🗑️ Comando global deletado: " + command.getName());
        //   }
        // });
        jda.addEventListener(
          new HelpInteractionListener(),
          new MessageListener(),
          new ButtonLike(),
          new ButtonViews()
        );
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
