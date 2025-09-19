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
      throw new UserNotFoundException("Você ainda não possui um perfil. Use o comando ``/profile view`` para criar seu perfil.");
    }

    if(bioOption.length() > 160){
      event.replyEmbeds(
        new EmbedBuilderService().embedWarning("⚠️ Bio muito longa", "Sua bio deve ter no máximo 160 caracteres.", null)
      ).setEphemeral(true).queue();
      return;
    }

    if(bioOption.contains("discord.gg")){
      event.replyEmbeds(
        new EmbedBuilderService().embedWarning("⚠️ Link inválido", "Sua bio não pode conter links de convite do Discord.", null)
      ).setEphemeral(true).queue();
      return;
    }
    
    user.setBio(bioOption);
    userRepo.updateUser(user);
    
    event.replyEmbeds(
      new EmbedBuilderService().embedSucess("✅ Bio atualizada com sucesso!", String.format("Sua nova bio é:\n\n> %s", bioOption), null, null)
    ).setEphemeral(false).queue();
    } catch (UserNotFoundException e){
      event.replyEmbeds(
        new EmbedBuilderService().embedWarning("⚠️ Usuário não encontrado", e.getMessage(), "Em caso de dúvidas, contate um administrador.")
      ).setEphemeral(true)
      .queue();
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
