package devhelp.bot.commands.utility.Levels;

import devhelp.bot.commands.ICommand;
import devhelp.bot.config.util.Colors;
import devhelp.bot.database.usersDB.User;
import devhelp.bot.database.usersDB.UserRepository;
import devhelp.bot.exception.UserNotFoundException;
import devhelp.bot.services.EmbedBuilderService;
import devhelp.bot.services.LevelService;
import devhelp.bot.services.ProgressBar;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;

public class Level implements ICommand {

  @Override
  public void execute(SlashCommandInteractionEvent event, String[] args) {
    try{
      var userOption = event.getOption("user");
      String userId;
      if(userOption != null){
        userId = userOption.getAsUser().getId();
      } else {
        userId = event.getUser().getId();
      }

      UserRepository userRepo = new UserRepository();
      User user = userRepo.getUser(userId);

      if(user == null){
        throw new UserNotFoundException("O usuário ainda não possui um perfil. Use o comando ``/profile view`` para criar um perfil.");
      }

      LevelService levelService = new LevelService();
      int xpForNextLevel = levelService.xpToNextLevel(user.getLevel());

      int position = userRepo.getUserRank(userId);

      MessageEmbed embed = new EmbedBuilder()
        .setTitle("🏆 Nível de " + user.getUserName())
        .setDescription(String.format("""
            **📊 Nível:** %s
            **🎯 Nível: %d ┃ ⭐ XP: %d/%d**
            **🏆 Posição: #%d**
            """, new ProgressBar().generateProgressBar(user.getXp(), xpForNextLevel), user.getLevel(),user.getXp(), xpForNextLevel, position))
        .setThumbnail(user.getAvatarUrl())
        .setColor(Colors.getPrimary())
        .build();
      event.replyEmbeds(embed).setEphemeral(false).queue();
    } catch(UserNotFoundException e){
      e.printStackTrace();
      event.replyEmbeds(
        new EmbedBuilderService().embedWarning("⚠️ Aviso", e.getMessage(), null)
      ).setEphemeral(true).queue();
    } catch(Exception e){
      event.reply("Ocorreu um erro ao executar o comando.").setEphemeral(true).queue();
      e.printStackTrace();
    }
  }

  @Override
  public String getName() {
    return "level";
  }

  @Override
  public String getDescription() {
    return "Mostra o nível atual do usuário!";
  }

  @Override
  public String getUsage() {
    return "/level";
  }
  
  public String getOptionName() {
    return "user";
  }

  public String getOptionDescription() {
    return "Mencione um usuário para ver o nível dele!";
  }

}
