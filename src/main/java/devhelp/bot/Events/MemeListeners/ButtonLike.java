package devhelp.bot.Events.MemeListeners;

import devhelp.bot.Database.MemeDB.Meme;
import devhelp.bot.Database.MemeDB.MemeRepository;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.events.interaction.component.ButtonInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.components.ActionRow;
import net.dv8tion.jda.api.interactions.components.buttons.Button;
import org.bson.types.ObjectId;
import org.jetbrains.annotations.NotNull;

public class ButtonLike extends ListenerAdapter {

    private final MemeRepository memeRepository = new MemeRepository();

    @Override
    public void onButtonInteraction(@NotNull ButtonInteractionEvent event) {
        if (event.getComponentId().startsWith("likeMeme:")) {

            String memeIdStr = event.getComponentId().split(":")[1];

            try {
                ObjectId memeId = new ObjectId(memeIdStr);

                memeRepository.incrementLikes(memeId); // CORRETO

                Meme updatedMeme = memeRepository.getMemeById(memeId);

                Button updatedButtonLike = Button.primary("likeMeme:" + memeIdStr, "🤣 " + updatedMeme.getLikes() + " Curtidas");
                Button buttonViews = Button.secondary("viewsMeme", "👀 " + updatedMeme.getViews() + " Views");

                MessageEmbed embed = new EmbedBuilder()
                        .setTitle("🕹️ +1 de XP em humor")
                        .setDescription("Você curtiu um meme. Achievement desbloqueado: ``Bom gosto digital``.")
                        .setColor(0xd38f50)
                        .build();


                event.editComponents(ActionRow.of(updatedButtonLike, buttonViews)).queue();
                event.getHook()
                        .sendMessageEmbeds(embed)
                        .setEphemeral(true)
                        .queue();

            } catch (Exception e) {
                event.getHook().sendMessage("Erro ao processar o like: " + e.getMessage()).setEphemeral(true).queue();
            }

        }
    }
}