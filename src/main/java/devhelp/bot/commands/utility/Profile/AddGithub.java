package devhelp.bot.commands.utility.Profile;

import devhelp.bot.commands.ICommand;
import devhelp.bot.config.util.EmbedTemplate;
import devhelp.bot.database.usersDB.User;
import devhelp.bot.database.usersDB.UserRepository;
import devhelp.bot.exception.UserGithubNotFoundException;
import devhelp.bot.exception.UserNotFoundException;
import devhelp.bot.services.GithubService;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;

public class AddGithub implements ICommand{

  @Override
  public void execute(SlashCommandInteractionEvent event, String[] args) {
    try{      
      UserRepository userRepo = new UserRepository();
      User user = userRepo.getUser(event.getUser().getId());      
      if(user == null) {
        throw new UserNotFoundException("Você ainda não possui um perfil. Use o comando ``/profile view`` para criar seu perfil.");
      }
      var getUserName = event.getOption(getOptionName()).getAsString();
      GithubService gService = new GithubService();
      if(!gService.isUserExist(getUserName)){
        throw new UserGithubNotFoundException("O usuário do Github inserido não existe. Verifique se você digitou corretamente.");
      }
      user.setGithubUser(getUserName);
      event.replyEmbeds(
        new EmbedTemplate().embedSucess(
          "🐙 Github vinculado com sucesso!", 
          """
          ✅ **Usuário vinculado:** `%s`
          🌐 [Acesse seu Github](https://github.com/%s)
          -# Use /profile view para visualizar seu perfil completo
          """.formatted(user.getGithubUser(), user.getGithubUser()),
          new GithubService().getAvatarGithub(getUserName),
          null)
      ).setEphemeral(false).queue();
      userRepo.updateUser(user);
    } catch(UserNotFoundException e){
      event.replyEmbeds(
        new EmbedTemplate().embedWarning("⚠️ Usuário não encontrado", e.getMessage(), "Em caso de dúvidas, contate um administrador.")
      ).setEphemeral(true)
      .queue();
    } catch(UserGithubNotFoundException e) {
        event.replyEmbeds(
          new EmbedTemplate().embedWarning("⚠️ Usuário do Github não encontrado", e.getMessage(), null)
        ).setEphemeral(true).queue();
    } catch (Exception e) {
      event.replyEmbeds(
        new EmbedTemplate().embedError("❌ Ocorreu um erro ao vincular o Github", "Verifique se você inseriu o username corretamente.", null)
      ).setEphemeral(true).queue();
    }
  }

  @Override
  public String getName() {
    return "addgithub"; 
  }

  @Override
  public String getDescription() {
    return "Adiciona link pro Github no perfil no Bot";
  }

  @Override
  public String getUsage() {
    return "/addGithub";
  }
  
  public String getOptionName(){
    return "user";
  }

  public String getOptionDescription(){
    return "Insira o seu username do Github exemplo: SousaaArthur";
  }
}
