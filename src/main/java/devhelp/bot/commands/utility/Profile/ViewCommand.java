package devhelp.bot.commands.utility.Profile;

import devhelp.bot.commands.ICommand;
import devhelp.bot.config.util.RolesIDs;
import devhelp.bot.database.usersDB.User;
import devhelp.bot.database.usersDB.UserRepository;
import devhelp.bot.services.LevelService;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.components.buttons.Button;

import java.awt.Color;
import java.util.List;

public class ViewCommand implements ICommand{
  UserRepository userRepository = new UserRepository();

  @Override
  public void execute(SlashCommandInteractionEvent event, String[] args) {
    try{
      var userOption = event.getOption("user");
      String userId;
      Member member;
      if(userOption != null){
        userId = userOption.getAsUser().getId();
        member = userOption.getAsMember();
      } else {
        userId = event.getUser().getId();
        member = event.getMember();
      }

      User user = userRepository.getUser(userId);
      if(user == null){
        user = new User(userId);
        new RolesIDs();
        userRepository.setUser(userId, member.getUser().getName(), member.getUser().getAvatarUrl(), RolesIDs.functionIds, RolesIDs.functionIds, member);
        user = userRepository.getUser(userId);

        viewEmbed(user, event);
      }
      if(user == null && !userOption.getAsUser().isBot()){
        user = new User(userId);
        new RolesIDs();
        userRepository.setUser(userId, member.getUser().getName(), member.getUser().getAvatarUrl(), RolesIDs.functionIds, RolesIDs.functionIds, member);
        user = userRepository.getUser(userId);

        viewEmbed(user, event);
      } else {
        viewEmbed(user, event);
      }

    } catch(Exception e){
      event.reply("Ocorreu um erro ao executar o comando.").setEphemeral(true).queue();
    }
  }

  @Override
  public String getName() {
    return "view";
  }

  @Override
  public String getDescription() {
    return "Exibe o perfil do usuário.";
  }

  @Override
  public String getUsage() {
    return "/profile";
  }

  public String getOption(){{
    return "user";
  }}

  public String getOptionDescription(){
    return "O usuário que você deseja ver o perfil.";
  }

  public String getRoleString(List<String> roles){
    String rolesGroup = "";
    for(String role : roles){
      rolesGroup += " <@&" + role + "> ";
    }
    return rolesGroup.isEmpty() ? "Nenhuma função adicionada." : rolesGroup;
  }

  public MessageEmbed viewEmbed(User user, SlashCommandInteractionEvent event){
    int rank = userRepository.getUserRank(user.getUserId());

    LevelService levelService = new LevelService();
    int xpForNextLevel = levelService.xpToNextLevel(user.getLevel());
    MessageEmbed embed = new EmbedBuilder()
        .setDescription(String.format(
          """
            ## ✨ %s - %s
            > %s

            **📊 Nível: %d ┃ ⭐ XP: %d/%d** 
            **🏆 Posição: #%d**
            **💬 Mensagens: %d**
          """, user.getUserName(), user.getTitle(), user.getBio(), user.getLevel(), user.getXp(),xpForNextLevel, rank, user.getMessagesSent()
        ))
        .addField("🛠 Linguagens:", getRoleString(user.getLanguages()), true)
        .addField("🛡 Funções:", getRoleString(user.getRole()), true)
        .setColor(Color.decode(user.getThemeColor()))
        .setThumbnail(user.getAvatarUrl() == null || user.getAvatarUrl().isEmpty() ? "https://media.discordapp.net/attachments/1365822361697386647/1418467503650246749/b5d4ce10a744861ffd3314d20d116976.png?ex=68ce3a4e&is=68cce8ce&hm=4e92ed82d5b236fc50ce29c970f7acab5e037171b374a473a91f72e9d1517cac&=&format=webp&quality=lossless&width=765&height=765" : user.getAvatarUrl())
        .setFooter(user.getGithubUser() == null || user.getGithubUser().isEmpty() ? "Adicione seu GitHub com /profile addgithub" : null)
        .build();
    if (user.getGithubUser() != null && !user.getGithubUser().isEmpty()) {
      Button buttonGithub = Button.link("https://github.com/" + user.getGithubUser(), "🐙 GitHub");
      event.replyEmbeds(embed).addActionRow(buttonGithub).setEphemeral(false).queue();
    } else {
      event.replyEmbeds(embed).setEphemeral(false).queue();
    }
    return embed;
  }

}

