package devhelp.bot.commands.utility.Profile;

import devhelp.bot.commands.ICommand;
import devhelp.bot.database.usersDB.User;
import devhelp.bot.database.usersDB.UserRepository;
import devhelp.bot.services.EmbedBuilderService;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;

public class SwitchColor implements ICommand{

  @Override
  public void execute(SlashCommandInteractionEvent event, String[] args) {
    var colorOption = event.getOption(getOptionName()).getAsString();
    System.out.println("Color option: " + colorOption);

    if(!colorOption.contains("#")){
      event.replyEmbeds(
        new EmbedBuilderService().embedError("❌ Formato inválido", "A cor deve ser em hexadecimal, começando com #. Exemplo: #FF5733", null)
      ).setEphemeral(true).queue();
    }
    if(colorOption.length() != 7){
      event.replyEmbeds(
        new EmbedBuilderService().embedError("❌ Formato inválido", "A cor deve conter 7 caracteres. Exemplo: #FF5733", null)
      ).setEphemeral(true).queue();
    }

    String userId = event.getUser().getId();
    UserRepository userRepo = new UserRepository();
    User user = userRepo.getUser(userId);

    user.setThemeColor(colorOption);
    event.replyEmbeds(
      new EmbedBuilderService().embedSucess("✅ Cor alterada com sucesso!", String.format("A cor do seu perfil foi alterada para `%s`", colorOption), null)
    ).setEphemeral(false).queue();
  }

  @Override
  public String getName() {
    return "switchcolor";
  }

  @Override
  public String getDescription() {
    return "Muda a cor do embed do seu perfil";
  }

  @Override
  public String getUsage() {
    return "/switchcolor";
  }

  public String getOptionName() {
    return "color";
  }

  public String getOptionDescription() {
    return "Cor em hexadecimal (Exemplo: #FF5733)";
  }
  
}
