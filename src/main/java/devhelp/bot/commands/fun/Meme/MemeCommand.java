package devhelp.bot.commands.fun.Meme;

import devhelp.bot.commands.ICommand;
import devhelp.bot.database.memeDB.Meme;
import devhelp.bot.database.memeDB.MemeRepository;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.components.buttons.Button;

public class MemeCommand implements ICommand {
  @Override
  public void execute(SlashCommandInteractionEvent event, String[] args) {
    event.deferReply().queue();

    MemeRepository memeRepository = new MemeRepository();
    Meme meme = memeRepository.getRandomMeme();

    String memeURL = meme.getUrl();

    Button buttonLike = Button.primary("likeMeme:" + meme.getId().toHexString(), "🤣 " + meme.getLikes() + " Curtidas");
    Button buttonViews = Button.secondary("viewsMeme", "👀 " + (meme.getViews() + 1) + " Views");

    String message = (meme.getCategory() != null) ? "\n" + memeURL : memeURL;
    event.getHook().sendMessage("[Anexo](" + message + ")").setActionRow(buttonLike, buttonViews).queue();

  }

  @Override
  public String getName() {
    return "memes";
  }

  @Override
  public String getDescription() {
    return "Mostra um meme aleatorio";
  }

  @Override
  public String getUsage() {
    return "/meme";
  }
}
