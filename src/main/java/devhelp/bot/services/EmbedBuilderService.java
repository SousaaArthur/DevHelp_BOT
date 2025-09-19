package devhelp.bot.services;

import devhelp.bot.config.util.Colors;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageEmbed;

public class EmbedBuilderService {
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

}