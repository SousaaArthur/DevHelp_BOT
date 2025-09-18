package devhelp.bot.commands.fun.CoinFlip;

import java.util.Random;

import devhelp.bot.commands.ICommand;
import devhelp.bot.config.util.Colors;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;

public class CoinFlipCommand implements ICommand {
  @Override
  public void execute(SlashCommandInteractionEvent event, String[] args) {
    String userId = event.getUser().getId();
    MessageEmbed embed = new EmbedBuilder()
        .setTitle(":skull_crossbones: O destino foi selado...")
        .setDescription(
            "O <@" + userId + "> jogou a moeda no abismo da Matrix... \n " +
                "**:dart: E ela caiu: " + getRandomCoin() + "**")
        .setColor(Colors.getPrimary())
        .build();
    event.replyEmbeds(embed).queue();
  }

  @Override
  public String getName() {
    return "coinflip";
  }

  @Override
  public String getDescription() {
    return "Comando para jogar a moeda! A moeda pode cair em cara ou coroa.";
  }

  @Override
  public String getUsage() {
    return "/coinflip";
  }

  public String getOption() {
    return "lado";
  }

  public String getDescriptionOption() {
    return "Escolha o seu lado da moeda";
  }

  public String getRandomCoin() {
    String[] coinSide = { "Cara", "Coroa" };
    return coinSide[new Random().nextInt(coinSide.length)];
  }
}
