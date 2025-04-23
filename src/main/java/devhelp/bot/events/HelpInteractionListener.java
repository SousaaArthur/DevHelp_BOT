package devhelp.bot.events;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.interaction.component.StringSelectInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class HelpInteractionListener extends ListenerAdapter {

    public void onStringSelectInteraction(StringSelectInteractionEvent event) {
        if(!event.getComponentId().equals("command-menu")) return;

        switch (event.getValues().get(0)) {
            case "Estudos" -> event.editMessageEmbeds(new EmbedBuilder()
                    .setTitle("Comandos de Estudos")
                    .setDescription("Aqui estão os comandos voltados para aprendizado:\n" +
                            "`/startcode` - Roadmap de estudos\n" +
                            "`/pomodoro` - Modo produtividade\n" +
                            "`/links` - Links úteis para programação")
                    .setColor(0xd38f50)
                    .build()).queue();
            case "Diversão" -> event.editMessageEmbeds(new EmbedBuilder()
                    .setTitle("Comandos de Diversão")
                    .setDescription("Aqui estão os comandos para diversão:\n" +
                            "`/desafio` - Desafios de lógica\n" +
                            "`/curiosidade` - Curiosidades sobre programação\n" +
                            "`/meme` - Memes do mundo tech")
                    .setColor(0xd38f50)
                    .build()).queue();
            case "Iniciante" -> event.editMessageEmbeds(new EmbedBuilder()
                    .setTitle("Comandos para Iniciantes")
                    .setDescription("Aqui estão os comandos para iniciantes:\n" +
                            "`/linguagens` - Aprenda sobre linguagens básicas\n" +
                            "`/exercicios` - Exercícios simples\n" +
                            "`/dicas` - Dicas práticas para começar")
                    .setColor(0xd38f50)
                    .build()).queue();
            case "GitHub" -> event.editMessageEmbeds(new EmbedBuilder()
                    .setTitle("GitHub")
                    .setDescription("Acesse o repositório oficial do bot no GitHub:\n" +
                            "[Clique aqui](https://github.com/filipedhunior/DevHelper)")
                    .setColor(0xd38f50)
                    .build()).queue();
            case "Menu" -> event.editMessageEmbeds(new EmbedBuilder()
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
                    .build()).queue();
            default -> event.editMessageEmbeds(new EmbedBuilder()
                    .setTitle("Erro")
                    .setDescription("Opção inválida!")
                    .setColor(0xd38f50)
                    .build()).queue();
        }
    }
}
