package devhelp.bot.events.helpListeners;
import devhelp.bot.events.helpListeners.Pages.FunPages;
import devhelp.bot.events.helpListeners.Pages.HelpPages;
import net.dv8tion.jda.api.events.interaction.component.StringSelectInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class HelpInteractionListener extends ListenerAdapter {
  private String choice;
  @Override
  public void onStringSelectInteraction(StringSelectInteractionEvent event) {
    try{
    String componentId = event.getComponentId();
    this.choice = event.getValues().get(0);
    
    switch (componentId) {
      case "command-menu" -> HelpPages.optionsHelp(event);
      case "fun-menu" -> FunPages.optionsFun(event);
      default -> {}
    }
    } catch(Exception e){
      System.out.println(e.getMessage());
    }
  }
  public String getChoice() {
    return choice;
  }
}
