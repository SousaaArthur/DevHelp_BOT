package devhelp.bot.config.util;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageEmbed;

public class EmbedTemplate {
  private String embedId;

  public MessageEmbed embedSucess(String title, String description, String avatarUrl, String footer){
    return new EmbedBuilder()
    .setTitle(title)
    .setDescription(description)
    .setThumbnail(avatarUrl)
    .setFooter(footer)
    .setColor(Colors.getSuccess())
    .build();
  }

  public MessageEmbed embedError(String title, String description, String footer){
    return new EmbedBuilder()
    .setTitle(title)
    .setDescription(description)
    .setColor(Colors.getError())
    .setFooter(footer)
    .build();
  }

    public MessageEmbed embedInfo(String title, String description, String footer){
    return new EmbedBuilder()
    .setTitle(title)
    .setDescription(description)
    .setColor(Colors.getPrimary())
    .setFooter(footer)
    .build();
  }

  public MessageEmbed embedWarning(String title, String description, String footer){
    return new EmbedBuilder()
    .setTitle(title)
    .setDescription(description)
    .setColor(Colors.getWarning())
    .setFooter(footer)
    .build();
  } 

  public String setEmbedId(String embedId) {
    return embedId;
  }

  public String getEmbedId(){
    return this.embedId;
  }
}