package devhelp.bot.Events.MemeListeners;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.events.interaction.component.ButtonInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

public class ButtonViews extends ListenerAdapter {

    @Override
    public void onButtonInteraction(@NotNull ButtonInteractionEvent event) {
        if(event.getComponentId().equals("viewsMeme")){
            MessageEmbed embed = new EmbedBuilder()
                    .setTitle("🚨 Glitch detectado: Tentativa de fuga da Matrix?")
                    .setDescription("👀 Esse botão é só um contador, não um portal interativo. Tá tentando hackear a realidade, amigão? :shushing_face:")
                    .setImage("https://cdn.discordapp.com/attachments/1295504651289886731/1367631679971135598/image.png?ex=681549c1&is=6813f841&hm=8419abb494dee8a87048c6d7edbaad7f7a9aca378b30fbe6985587ca0c7c3901&")
                    .setColor(0xd38f50)
                    .build();

            event.replyEmbeds(embed).setEphemeral(true).queue();
        }
    }
}

