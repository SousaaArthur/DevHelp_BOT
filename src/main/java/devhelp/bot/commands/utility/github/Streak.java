package devhelp.bot.commands.utility.github;

import org.json.JSONArray;

import devhelp.bot.commands.ICommand;
import devhelp.bot.config.util.Colors;
import devhelp.bot.config.util.EmbedTemplate;
import devhelp.bot.database.usersDB.User;
import devhelp.bot.database.usersDB.UserRepository;
import devhelp.bot.exception.UserGithubNotFoundException;
import devhelp.bot.exception.UserNotFoundException;
import devhelp.bot.services.GithubService;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;

public class Streak implements ICommand {

  @Override
  public void execute(SlashCommandInteractionEvent event, String[] args) {
    try{
      var optionUser = event.getOption(getOptionName()) != null ? event.getOption(getOptionName()).getAsMember() : null;
      String userId;
      User user;
      UserRepository userRepo = new UserRepository();

      if (optionUser != null) {
        userId = optionUser.getId();
      } else {
        userId = event.getUser().getId();
      }

      user = userRepo.getUser(userId);
      if(user == null){
        throw new UserNotFoundException(optionUser != null ? "O usuário <@" + optionUser.getId() + "> ainda não possui um perfil. Peça para o usuário enviar uma mensagem para criar seu perfil ou use o comando `/profile view`." : "Você ainda não possui um perfil. Use o comando ``/profile view`` para criar seu perfil.");
      }
      String username = user.getGithubUser();
      if(username == null){
        throw new UserGithubNotFoundException(optionUser != null ? "O usuário <@" + optionUser.getId() + "> não possui Github cadastrado! Peça para o usuário vincular seu github com o comando `/profile addgithub`" : "Você não possui Github cadastrado! Vincule seu github com o comando `/profile addgithub`");
      }

      GithubService gService = new GithubService();
      int currentStreak = gService.getCurrentCommitStreak(username);
      int maxStreak = gService.getCommitStreak(username);
      String avatarUrl = gService.getAvatarGithub(username);

    JSONArray weeks = gService.getContributionWeeks(username);
    String weeklyView = gService.formatWeeklyStreak(weeks);

      MessageEmbed embed = new EmbedBuilder()
        .setDescription(String.format(
            """
            ## ⚡ Streak de commits
            **Streak atual:** %d dias 🔄
            **Maior streak:** %d dias 🔥
            ## Última semana
            **%s**
            """, currentStreak, maxStreak, weeklyView))
        .setFooter("Mantenha seus commits diários! ")
        .setColor(Colors.getPrimary())
        .setThumbnail(avatarUrl)
        .build();
      event.replyEmbeds(embed)
        .setEphemeral(false)
        .queue();
    } catch (UserGithubNotFoundException e) {
      event.replyEmbeds(
        new EmbedTemplate().embedWarning("⚠️ Usuário não vinculado", e.getMessage(), "Em caso de dúvidas, contate um administrador.")
      ).setEphemeral(true)
      .queue();
    } catch (UserNotFoundException e) {
      event.replyEmbeds(
        new EmbedTemplate().embedWarning("⚠️ Usuário não encontrado", e.getMessage(), "Em caso de dúvidas, contate um administrador.")
      ).setEphemeral(true)
      .queue();
    } catch (Exception e) {
      event.replyEmbeds(
        new EmbedTemplate().embedError("❌ Erro ao buscar streak do usuário!", e.getMessage(), "Tente novamente mais tarde. Ou contate um administrador.")
      ).setEphemeral(true).queue();
      e.printStackTrace();
    }
  }

  @Override
  public String getName() {
    return "streak";
  }

  @Override
  public String getDescription() {
    return "Mostra o streak de commits do usuário no GitHub.";
  }

  @Override
  public String getUsage() {
    return "/streak <usuário>";
  }

  public String getOptionName() {
    return "user";
  }

  public String getOptionDescription() {
    return "Mencione o usuário que deseja ver o streak de commits.";
  }
}
