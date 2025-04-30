package devhelp.bot.commands.studyCommands;

import devhelp.bot.DatabaseManager;
import devhelp.bot.commands.ICommand;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.Normalizer;

public class ExerciseCommand implements ICommand {
    @Override
    public void execute(SlashCommandInteractionEvent event, String[] args) {
        event.deferReply().queue();

        String language = event.getOption(formattedText("linguagem")).getAsString();
        String difficulty = event.getOption(formattedText("dificuldade")).getAsString();

        String textDifficulty = "";

        if (difficulty.equalsIgnoreCase("facil")){
            textDifficulty = ":green_square:";
        } else if (difficulty.equalsIgnoreCase("medio")){
            textDifficulty = ":yellow_square:";
        } else {
            textDifficulty = ":red_square:";
        }

        if(language.equals("js")){
            language = "javascript";
        }

        String query = "SELECT title, exercise FROM exercises WHERE language = ? AND difficulty = ? ORDER BY RANDOM() LIMIT 1";

        try (Connection conn = DatabaseManager.connect(); PreparedStatement stmt = conn.prepareStatement(query)){
            stmt.setString(1, language);
            stmt.setString(2, difficulty);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                String title = rs.getString("title");
                String exercise = rs.getString("exercise");

                MessageEmbed embed = new EmbedBuilder()
                        .setTitle("Exercício de " + language + " - " + difficulty)
                        .setDescription("Aqui está um exercício para você praticar! 🚀")
                        .addField("Linguagem:", "``" + language + "``", true)
                        .addField("Dificuldade: ", "``" + difficulty + "``" +  " " + textDifficulty, true)
                        .addField("Exercício:\n", "```" + exercise + "```", false)
                        .setColor(getColorByDifficulty(difficulty))
                        .build();
                        event.getHook().sendMessageEmbeds(embed).queue();
            } else {
                event.getHook().sendMessage("Nenhum exercício encontrado para a linguagem e dificuldade especificadas! 🧐").setEphemeral(true).queue();
                return;
            }

        } catch (java.sql.SQLException e) {
            event.reply("Erro ao buscar o exercício! 😢 Detalhes: " + e.getMessage()).setEphemeral(true).queue();
            e.printStackTrace();
        }

    }

    @Override
    public String getName() {
        return "exercise";
    }

    @Override
    public String getDescription() {
        return "Comando para fazer exercícios de programação!";
    }

    @Override
    public String getUsage() {
        return "/exercise";
    }

    public String getOptionLanguage() {
        return "linguagem";
    }

    public String getDescriptionOptionLanguage(){
        return "Escolha a linguagem de programação: Java, Python, JavaScript, GoLang, C#, Ruby e etc.";
    }

    public String getOptionDificulty() {
        return "dificuldade";
    }

    public String getDescriptionOptionDificulty(){
        return "Escolha a dificuldade do exercício: Fácil, Médio ou Difícil";
    }

    public String formattedText(String text){
        String removeCharacters = Normalizer.normalize(text, Normalizer.Form.NFD)
                .replaceAll("[^\\p{ASCII}]", "");
        return removeCharacters.toLowerCase();
    }

    private int getColorByDifficulty(String difficulty) {
        switch (difficulty) {
            case "facil":
                return 0x00FF00;// Verde
            case "medio":
                return 0xFFFF00; // Amarelo
            case "dificil":
                return 0xFF0000; // Vermelho
            default:
                return 0x65D8C5; // Cor padrão
        }
    }
}
