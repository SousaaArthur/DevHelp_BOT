package devhelp.bot.commands.utility.Profile;

import devhelp.bot.commands.ICommand;
import devhelp.bot.config.util.Colors;
import devhelp.bot.database.usersDB.User;
import devhelp.bot.database.usersDB.UserRepository;
import devhelp.bot.services.EmbedBuilderService;
import devhelp.bot.services.GithubService;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;

public class AddGithub implements ICommand{

  @Override
  public void execute(SlashCommandInteractionEvent event, String[] args) {
    try{      
      UserRepository userRepo = new UserRepository();
      User user = userRepo.getUser(event.getUser().getId());
  
      var getUserName = event.getOption(getOptionName()).getAsString();
      user.setGithubUser(getUserName);

      MessageEmbed embed = new EmbedBuilder()
        .setTitle("🐙 Github vinculado com sucesso!")
        .setDescription(String.format(
          """
          ✅ **Usuário vinculado:** `%s`
          🌐 [Acesse seu Github](https://github.com/%s)
          -# Use /profile view para visualizar seu perfil completo
          """, user.getGithubUser(), user.getGithubUser()))
          .setThumbnail(new GithubService().getAvatarGithub(getUserName))
          .setColor(Colors.getPrimary())
        .build();
      event.replyEmbeds(embed).setEphemeral(false).queue();
      userRepo.updateUser(user);
    } catch (Exception e) {
      event.replyEmbeds(
        new EmbedBuilderService().embedError("❌ Ocorreu um erro ao vincular o Github", "Verifique se você inseriu o username corretamente.", null)
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
