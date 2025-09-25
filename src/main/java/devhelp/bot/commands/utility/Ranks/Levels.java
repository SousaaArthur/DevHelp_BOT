package devhelp.bot.commands.utility.Ranks;

import devhelp.bot.commands.ICommand;
import devhelp.bot.config.util.Colors;
import devhelp.bot.config.util.EmbedTemplate;
import devhelp.bot.database.usersDB.User;
import devhelp.bot.database.usersDB.UserRepository;
import devhelp.bot.exception.UserNotFoundException;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;

public class Levels implements ICommand {

  @Override
  public void execute(SlashCommandInteractionEvent event, String[] args) {
    try{
      var userOption = event.getOption(getOptionName());
      UserRepository userRepo = new UserRepository();
      if (userOption != null ) {
        String userId = userOption.getAsMember().getId();
        User user = userRepo.getUser(userId);
        if(user == null){
          throw new UserNotFoundException("O usuário <@" + userId + "> não foi encontrado no banco de dados.");
        }
        MessageEmbed embed = new EmbedBuilder()
        .setDescription(userRepo.getRankAroundUser(userId, 5))
        .setColor(Colors.getPrimary())
        .setFooter("Use ``/rank levels`` para ver o top 10 do servidor.")
        .build();
        event.replyEmbeds(embed).queue();
      } else {
        MessageEmbed embed = new EmbedBuilder()
          .setDescription(userRepo.getTopUsersString(10))
          .setColor(Colors.getPrimary())
          .setFooter("Use ``/rank levels @user`` para ver a posição de um usuário específico.")
          .build();
          event.replyEmbeds(embed).queue();
      }
    } catch(UserNotFoundException e){
      event.replyEmbeds(
        new EmbedTemplate().embedError("❌ Erro ao buscar usuário na lista!", e.getMessage(), "Tente buscar novamente mais tarde. Ou contate um administrador.")
      ).setEphemeral(true).queue();
    } catch(Exception e){
      System.out.println(e.getMessage());
      event.reply("Ocorreu um erro ao buscar o top levels: " + e.getMessage()).setEphemeral(true).queue();
    }
  }

  @Override
  public String getName() {
    return "levels";
  }

  @Override
  public String getDescription() {
    return "Comando para mostra o top rank de levels do servidor.";
  }

  @Override
  public String getUsage() {
    return "/level";
  }

  public String getOptionName(){
    return "user";
  }

  public String getOptionDescription(){
    return "Ver posição do usuário no rank de levels.";
  }
  
}
