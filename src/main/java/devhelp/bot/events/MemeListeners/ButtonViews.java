package devhelp.bot.events.MemeListeners;

import net.dv8tion.jda.api.events.interaction.component.ButtonInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

import devhelp.bot.services.EmbedBuilderService;

public class ButtonViews extends ListenerAdapter {

  @Override
  public void onButtonInteraction(@NotNull ButtonInteractionEvent event) {
    event.replyEmbeds(
      new EmbedBuilderService().embedInfo("🚨 Glitch detectado: Tentativa de fuga da Matrix?", "👀 Esse botão é só um contador, não um portal interativo. Tá tentando hackear a realidade, amigão? 🤫", null)
    ).setEphemeral(true).queue();
  }
}

