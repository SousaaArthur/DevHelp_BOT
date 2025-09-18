package devhelp.bot.events;

import java.util.List;
import java.util.Set;

import devhelp.bot.config.util.Colors;
import devhelp.bot.config.util.RolesIDs;
import devhelp.bot.database.usersDB.User;
import devhelp.bot.database.usersDB.UserRepository;
import devhelp.bot.services.LevelService;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;


public class MessageListener extends ListenerAdapter {
  @Override
  public void onMessageReceived(MessageReceivedEvent event) {
    event.getGuild().retrieveMember(event.getAuthor()).queue(member -> {
      if (member == null) return;
      if (member.getUser().isBot()) return;

      var author = event.getAuthor();
      String userId = author.getId();
      String avatarUrl = author.getAvatarUrl();
      String userName = author.getName();
      
      UserRepository userRepo = new UserRepository();
      User user = userRepo.getUser(userId);
      if(user == null) {
        
        user = userRepo.setUser(userId, userName, avatarUrl, new RolesIDs().languagesIds, new RolesIDs().languagesIds, member);
      }



      levelUpEmbed(user, avatarUrl, event);
      user.setMessagesSent(user.getMessagesSent() + 1);

      userRepo.updateUser(user);
    });
  }

  public List<String> functionFilter(Member member, Set<String> roles){
    return member.getRoles().stream()
          .filter(role -> roles.contains(role.getId()))
          .map(role -> role.getId())
          .toList();
  }

  public void levelUpEmbed(User user, String avatarUrl, MessageReceivedEvent event){
      UserRepository userRepo = new UserRepository();
      int rank = userRepo.getUserRank(user.getUserId());

      if(event.getMember().isBoosting()){
        user.setXp(user.getXp() + 5);
      } else { 
        user.setXp(user.getXp() + 3);
      }
      
      LevelService levelService = new LevelService();
      int currentLevel = levelService.calculateLevel(user.getXp());
      if(currentLevel > user.getLevel()){
        MessageEmbed levelUpEmbed = new EmbedBuilder()
        .setTitle("🚀 Level Up!")
        .setDescription(String.format("""
            Parabéns <@%s>, você alcançou o nível %d!  🎉
            
            🏆 Sua posição atual no ranking é **#%d**
            """, user.getUserId(), currentLevel, rank))
        .setColor(Colors.getPrimary())
        .setThumbnail(avatarUrl)
        .setFooter("Use ``/profile`` view para ver seu progresso")
        .build();
        event.getChannel().sendMessageEmbeds(levelUpEmbed).queue();
        user.setLevel(currentLevel);
        user.setXp(0);
      }
  }
}
