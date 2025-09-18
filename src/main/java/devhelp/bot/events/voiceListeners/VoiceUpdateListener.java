package devhelp.bot.events.voiceListeners;

import net.dv8tion.jda.api.events.guild.voice.GuildVoiceUpdateEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class VoiceUpdateListener extends ListenerAdapter {

  @Override
  public void onGuildVoiceUpdate(GuildVoiceUpdateEvent event) {
      var member = event.getEntity();
      var joined = event.getChannelJoined();
      var left = event.getChannelLeft();

      if (joined != null) {
          System.out.println("➡️ " + member.getEffectiveName() + " entrou em " + joined.getName());
      }
      if (left != null) {
          System.out.println("⬅️ " + member.getEffectiveName() + " saiu de " + left.getName());
      }
  }
}
