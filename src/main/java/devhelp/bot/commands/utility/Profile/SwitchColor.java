package devhelp.bot.commands.utility.Profile;

import devhelp.bot.commands.ICommand;
import devhelp.bot.config.util.EmbedTemplate;
import devhelp.bot.database.usersDB.User;
import devhelp.bot.database.usersDB.UserRepository;
import devhelp.bot.exception.UserNotFoundException;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;

public class SwitchColor implements ICommand{

  @Override
  public void execute(SlashCommandInteractionEvent event, String[] args) {
    try {
      var colorOption = event.getOption(getOptionName()).getAsString();
  
      if(!colorOption.contains("#")){
        event.replyEmbeds(
          new EmbedTemplate().embedError("❌ Formato inválido", "A cor deve ser em hexadecimal, começando com #. Exemplo: #FF5733", null)
        ).setEphemeral(true).queue();
      }
      if(colorOption.length() != 7){
        event.replyEmbeds(
          new EmbedTemplate().embedError("❌ Formato inválido", "A cor deve conter 7 caracteres. Exemplo: #FF5733", null)
        ).setEphemeral(true).queue();
      }
      String userId = event.getUser().getId();
      UserRepository userRepo = new UserRepository();
      User user = userRepo.getUser(userId);
      if(user != null){
        user.setThemeColor(colorOption);
        userRepo.updateUser(user);
      } else if(user == null) {
        throw new UserNotFoundException("Você ainda não possui um perfil. Use o comando ``/profile view`` para criar seu perfil.");
      }
  
      event.replyEmbeds(
        new EmbedTemplate().embedSucess("✅ Cor alterada com sucesso!", String.format("A cor do seu perfil foi alterada para `%s`", colorOption), null, null)
      ).setEphemeral(false).queue();
    } catch (UserNotFoundException e){
      event.replyEmbeds(
        new EmbedTemplate().embedWarning("⚠️ Usuário não encontrado", e.getMessage(), "Em caso de dúvidas, contate um administrador.")
      ).setEphemeral(true)
      .queue();
    } catch (Exception e) {
      event.replyEmbeds(
        new EmbedTemplate().embedError("❌ Ocorreu um erro ao alterar a cor", "Verifique se você inseriu a cor corretamente.", null)
      ).setEphemeral(true).queue();
      e.printStackTrace();
    }
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
