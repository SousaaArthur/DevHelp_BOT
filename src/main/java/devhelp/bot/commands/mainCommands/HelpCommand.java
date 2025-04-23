package devhelp.bot.commands.mainCommands;

import devhelp.bot.commands.ICommand;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.components.selections.StringSelectMenu;

public class HelpCommand implements ICommand {

    @Override
    public void execute(SlashCommandInteractionEvent event, String[] args) {
        StringSelectMenu menu = StringSelectMenu.create("command-menu")
                .setPlaceholder("Escolha uma opção!")
                .addOption("Estudos 📚", "Estudos", "Acesse comandos voltados para aprendizado")
                .addOption("Diversão 🕹️", "Diversão", "Descubra comandos para se divertir")
                .addOption("Iniciante 👨‍💻", "Iniciante", "Obtenha orientações para começar na programação")
                .addOption("GitHub 🐙", "GitHub", "Confira o repositório oficial no GitHub")
                .addOption("Menu 💾", "Menu", "Volte ao menu principal")
                .build();

        MessageEmbed embed = new EmbedBuilder()
                .setTitle("Ajuda DevHelper")
                .setDescription("Boot sequence iniciada... ✅\n" +
                        "Identidade confirmada: **Lynx // DevHelper**\n" +
                        "Função: Assistente de Programação • Modo Companion Ativado 🤖\n\n" +
                        "Interface carregada com conhecimento técnico, boas práticas e uns memes pra quebrar o gelo do debug eterno.\n\n" +
                        "**🧠 Consulte abaixo os módulos disponíveis e escolha seu caminho no sistema:**\n")

                .addField("📚 Estudos",
                        "⚙️ Invoque comandos que turbinarão seu cérebro de dev: ``/startcode`` pra baixar o roadmap do sucesso, ``/pomodoro`` pra focar como um monge digital e uma chuva de links que são pura RAM de conhecimento.",
                        false)

                .addField("🎮 Diversão",
                        "🕹️ Porque até um operador precisa de downtime. Desafios de lógica, curiosidades tech que vão fritar sua mente, e memes direto do submundo da programação.",
                        false)

                .addField("🧑‍💻 Iniciante",
                        "🧠 Respire fundo, plugue-se na Matrix e comece do zero. Linguagens básicas, exercícios mastigados e hacks de produtividade pra quem quer subir de nível sem crashar no caminho.",
                        false)

                .addField("🐙 GitHub",
                        "💾 Consulte os logs sagrados do repositório oficial. Lá tem o código-fonte do bot, issues abertas pro caos controlado e chances de você deixar sua marca na resistência digital.",
                        false)

                .setFooter("Desenvolvido por StarCode", "https://cdn.discordapp.com/attachments/1295504651289886731/1364389251340570675/ChatGPT_Image_21_de_abr._de_2025_20_49_26.png?ex=68097e02&is=68082c82&hm=3954ad5f9205165a29a7c3be05f6e41aeaba6dc4646d5dd47a2f9555b0c44d84&")
                .setThumbnail("https://cdn.discordapp.com/attachments/1295504651289886731/1364389251340570675/ChatGPT_Image_21_de_abr._de_2025_20_49_26.png?ex=68097e02&is=68082c82&hm=3954ad5f9205165a29a7c3be05f6e41aeaba6dc4646d5dd47a2f9555b0c44d84&")
                .setImage("https://cdn.discordapp.com/attachments/1308098418975182948/1309343979346591846/standard_2.gif?ex=67413d1b&is=673feb9b&hm=812ae5ce71f8b9eed5460e32d3248c1f290fea705111f11053fc7182b3d413d4&")
                .setColor(0xd38f50)
                .build();
        event.replyEmbeds(embed).addActionRow(menu).setEphemeral(true).queue();
    }

    @Override
    public String getName() {
        return "help";
    }

    @Override
    public String getDescription() {
        return "Exibe o catálogo de instruções do bot operacional. 🤖";
    }

    @Override
    public String getUsage() {
        return "/bot";
    }
}
