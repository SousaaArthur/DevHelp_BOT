package devhelp.bot.events.helpListeners;

import java.awt.Color;

import devhelp.bot.config.util.Colors;
import devhelp.bot.events.helpListeners.Pages.HelpPages;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.events.interaction.component.StringSelectInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class HelpInteractionListener extends ListenerAdapter {
  private static final Color EMBED_COLOR = Colors.getPrimary();

  @Override
  public void onStringSelectInteraction(StringSelectInteractionEvent event) {
    try{
          // String userId = "847867725048709212";
    if (!event.getComponentId().equals("command-menu"))
      return;

    String choice = event.getValues().get(0);
    HelpPages.optiosHelp(choice, event);
    } catch(Exception e){
      System.out.println(e.getMessage());
    }
  }

  public MessageEmbed templateEmbed(String title, String description) {
    return new EmbedBuilder()
        .setTitle(title)
        .setDescription(description)
        .setColor(EMBED_COLOR).build();
  }
}
