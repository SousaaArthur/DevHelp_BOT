package devhelp.bot.Events;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.events.interaction.component.StringSelectInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class HelpInteractionListener extends ListenerAdapter {
        private static final int EMBED_COLOR = 0xd38f50;

        @Override
        public void onStringSelectInteraction(StringSelectInteractionEvent event) {
        if(!event.getComponentId().equals("command-menu")) return;

        switch (event.getValues().get(0)) {
            case "Estudos" -> event.editMessageEmbeds(templateEmbed(
                "Comandos de Estudos", """
                        Aqui estão os comandos voltados para aprendizado:
                        `/startcode` - Roadmap de estudos
                        `/pomodoro` - Modo produtividade
                        `/links` - Links úteis para programação
                        """
                        )).queue();
            case "Diversão" -> event.editMessageEmbeds(templateEmbed(
                "Comandos de Diversão", """
                        Aqui estão os comandos para diversão:
                        `/desafio` - Desafios de lógica
                        `/curiosidade` - Curiosidades sobre programação
                        `/meme` - Memes do mundo tech
                        """
                )).queue();
            case "Iniciante" -> event.editMessageEmbeds(templateEmbed(
                "Comandos para Iniciantes", """
                        Aqui estão os comandos para iniciantes:
                        `/linguagens` - Aprenda sobre linguagens básicas
                        `/exercicios` - Exercícios simples
                        `/dicas` - Dicas práticas para começar
                        """
                        )).queue();
            case "GitHub" -> event.editMessageEmbeds(templateEmbed(
                "GitHub", """
                        Acesse o repositório oficial do bot no GitHub:
                        [Clique aqui](https://github.com/filipedhunior/DevHelper)
                        """
                        )).queue();
            case "Menu" -> event.editMessageEmbeds(new EmbedBuilder()
                    .setTitle("Ajuda DevHelper")
                    .setDescription("""
                                    Boot sequence iniciada... ✅
                                    Identidade confirmada: **Lynx // DevHelper**
                                    Função: Assistente de Programação • Modo Companion Ativado 🤖

                                    Interface carregada com conhecimento técnico, boas práticas e uns memes pra quebrar o gelo do debug eterno.

                                    **🧠 Consulte abaixo os módulos disponíveis e escolha seu caminho no sistema:**
                                    """)

                    .addField("**📚 Estudos**",
                            "⚙️ Invoque comandos que turbinarão seu cérebro de dev: ``/startcode`` pra baixar o roadmap do sucesso, ``/pomodoro`` pra focar como um monge digital e uma chuva de links que são pura RAM de conhecimento.",
                            false)

                    .addField("**🎮 Diversão**",
                            "🕹️ Porque até um operador precisa de downtime. Desafios de lógica, curiosidades tech que vão fritar sua mente, e memes direto do submundo da programação.",
                            false)

                    .addField("**🧑‍💻 Iniciante**",
                            "🧠 Respire fundo, plugue-se na Matrix e comece do zero. Linguagens básicas, exercícios mastigados e hacks de produtividade pra quem quer subir de nível sem crashar no caminho.",
                            false)

                    .addField("**🐙 GitHub**",
                            "💾 Consulte os logs sagrados do repositório oficial. Lá tem o código-fonte do bot, issues abertas pro caos controlado e chances de você deixar sua marca na resistência digital.",
                            false)

                    .setFooter("Desenvolvido por StarCode", "https://cdn.discordapp.com/attachments/1295504651289886731/1364389251340570675/ChatGPT_Image_21_de_abr._de_2025_20_49_26.png?ex=68097e02&is=68082c82&hm=3954ad5f9205165a29a7c3be05f6e41aeaba6dc4646d5dd47a2f9555b0c44d84&")
                    .setThumbnail("https://cdn.discordapp.com/attachments/1295504651289886731/1364389251340570675/ChatGPT_Image_21_de_abr._de_2025_20_49_26.png?ex=68097e02&is=68082c82&hm=3954ad5f9205165a29a7c3be05f6e41aeaba6dc4646d5dd47a2f9555b0c44d84&")
                    .setImage("https://cdn.discordapp.com/attachments/1367645252973756437/1367645513792618557/ChatGPT_Image_27_de_abr._de_2025_23_14_16.png?ex=681556a4&is=68140524&hm=00c676ce5a491b1816346747d232c2298f5522014cfc489cb61797c644a3467e&")
                    .setColor(EMBED_COLOR)
                    .build()).queue();
            default -> event.editMessageEmbeds(new EmbedBuilder()
                    .setTitle("Erro")
                    .setDescription("Opção inválida!")
                    .setColor(EMBED_COLOR)
                    .build()).queue();
        }
    }

    public MessageEmbed templateEmbed(String title, String description) {
        return new EmbedBuilder()
                .setTitle(title)
                .setDescription(description)
                .setColor(EMBED_COLOR).build();
    }
}
