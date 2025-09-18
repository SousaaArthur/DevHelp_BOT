package devhelp.bot.commands.main.Ping;

import devhelp.bot.commands.ICommand;
import devhelp.bot.config.util.Colors;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.CommandInteraction;

public class PingCommand implements ICommand {

  @Override
  public void execute(SlashCommandInteractionEvent event, String[] args) {
    CommandInteraction interaction = (CommandInteraction) event.getInteraction();
    long latency = event.getJDA().getGatewayPing();
    MessageEmbed embed = new EmbedBuilder()
        .setDescription("📡 Conexão estabelecida.\n" +
            "💻 Latência com a Matrix: **" + latency + "ms**\n" +
            "⌛ Tempo de resposta dentro dos parâmetros operacionais.\n" +
            "✅ Status: ONLINE e rodando em root mode.")
        .setColor(Colors.getPrimary())
        .setImage("https://gifdb.com/images/high/scrolling-up-green-system-coding-nxt2vg8bl6e4wbo1.gif")
        .build();
    interaction.replyEmbeds(embed).queue();
  }

  @Override
  public String getName() {
    return "ping";
  }

  @Override
  public String getDescription() {
    return "Mostra a latência do bot!";
  }

  @Override
  public String getUsage() {
    return "/ping";
  }
}
