package devhelp.bot.commands.utility.Embeds;

import devhelp.bot.commands.ICommand;
import devhelp.bot.config.util.Colors;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.OptionMapping;
import java.awt.Color;

public class CreateEmbeds implements ICommand {

    @Override
    public void execute(SlashCommandInteractionEvent event, String[] args) {
        OptionMapping title = event.getOption("title");
        OptionMapping description = event.getOption("description");
        OptionMapping color = event.getOption("color");
        OptionMapping image = event.getOption("image");

        EmbedBuilder embed = new EmbedBuilder();

        if (title != null) {
            embed.setTitle(title.getAsString());
        }

        if (description != null) {
            embed.setDescription(description.getAsString().replace("\\n", "\n"));
        }

        if (image != null) {
            embed.setImage(image.getAsString());
        }
        if (color != null) {
            String hexColor = color.getAsString();
            if (!hexColor.startsWith("#")) {
                hexColor = "#" + hexColor;
            }

            try {
                embed.setColor(Color.decode(hexColor));
            } catch (NumberFormatException e) {
                event.reply("❌ O formato da cor é inválido. Use um código hexadecimal, como `#5865F2`.").setEphemeral(true).queue();
                return;
            }
        } else {
            embed.setColor(Colors.getPrimary());
        }

        if (embed.isEmpty()) {
            event.reply("❌ Você precisa fornecer pelo menos uma opção para criar o embed.").setEphemeral(true).queue();
            return;
        }

        event.replyEmbeds(embed.build()).queue();
    }


    @Override
    public String getName() {
        return "create-embed";
    }

    @Override
    public String getDescription() {
        return "Cria embeds personalizados";
    }

    @Override
    public String getUsage() {
        return "/embed create";
    }

    public String getOptionTitleEmbed(){
        return "title";
    }

    public String getDescriptionTitleEmbed(){
        return "Título do embed";
    }

    public String getOptionDescriptionEmbed(){
        return "description";
    }

    public String getDescriptionDescriptionEmbed(){
        return "Descrição do embed";
    }

    public String getOptionColorEmbed(){
        return "color";
    }

    public String getDescriptionColorEmbed(){
        return "Cor do embed em hexadecimal (Exemplo: 5865F2)";
    }

    public String getOptionImageEmbed(){
        return "image";
    }

    public String getDescriptionImageEmbed(){
        return "Imagem do embed (URL)";
    }

    public String getOptionThumbnail(){
        return "thumbnail";
    }

    public String getDescriptionThumbnail(){
        return "Thumbnail do embed (URL)";
    }

}
