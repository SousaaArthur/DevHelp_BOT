package devhelp.bot.commands.utility.Profile;

import devhelp.bot.commands.ICommand;
import devhelp.bot.database.usersDB.User;
import devhelp.bot.database.usersDB.UserRepository;
import devhelp.bot.exception.UserNotFoundException;
import devhelp.bot.services.EmbedBuilderService;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;

public class AddBio implements ICommand {

  @Override
  public void execute(SlashCommandInteractionEvent event, String[] args) {
    try {
    var bioOption = event.getOption(getOptionName()).getAsString().replace("\\n", "\n >");
    String userId = event.getUser().getId();
    UserRepository userRepo = new UserRepository();
    User user = userRepo.getUser(userId);
    
    if(user == null){
      event.replyEmbeds(
        new EmbedBuilderService().embedWarning("⚠️ Aviso", "Você ainda não possui um perfil. Use o comando ``/profile view`` para criar seu perfil.", null)
      ).setEphemeral(true).queue();
      return;
    }
    
    user.setBio(bioOption);
    userRepo.updateUser(user);
    
    event.replyEmbeds(
      new EmbedBuilderService().embedSucess("✅ Bio atualizada com sucesso!", String.format("Sua nova bio é:\n\n> %s", bioOption), null)
    ).setEphemeral(false).queue();
    } catch (UserNotFoundException e){

    }
    catch (Exception e) {
      event.reply("Ocorreu um erro ao executar o comando.").setEphemeral(true).queue();
      e.printStackTrace();
    }
  }

  @Override
  public String getName() {
    return "addbio";
  }

  @Override
  public String getDescription() {
    return "Adiciona uma bio ao seu perfil";
  }

  @Override
  public String getUsage() {
    return "/addbio";
  }

  public String getOptionName() {
    return "bio";
  }

  public String getOptionDescription() {
    return "Sua bio (máximo 160 caracteres)";
  }
  
}
