package devhelp.bot.events.helpListeners;

import devhelp.bot.config.util.Colors;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageEmbed;

public class HelpCategory {
public static MessageEmbed menu(String userName, String avatarUrl){
    return new EmbedBuilder()
        .setDescription("""
            # 📖 Central de Ajuda – Star Code
            Bem-vindo à central de ajuda do Star Code!
            Aqui você encontra todos os comandos disponíveis para explorar, aprender e se divertir na comunidade.
            ## ✨ Categorias de comandos:
            **📚 Estudos**
            Comandos voltados para aprendizado e prática de programação.

            **🕹️ Diversão**
            Comandos interativos para relaxar e se conectar com a comunidade.

            **🧑‍💻 Iniciante**
            Recursos e atalhos para quem está começando na jornada dev.
            """)
        .setFooter("Desenvolvido por " + userName,
            avatarUrl)
        .setThumbnail(
            "https://media.discordapp.net/attachments/1416518696024670238/1416518818175652061/logo_starcode.png?ex=68c72373&is=68c5d1f3&hm=db3c3b73ba08bf343d64be0528c0e6c6692c5c6a88742f4fd33a88da3857202c&=&format=webp&quality=lossless&width=765&height=765")
        .setColor(Colors.getPrimary())
        .build();
    }
}
