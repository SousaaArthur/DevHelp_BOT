package devhelp.bot.commands.studyCommands.Exercise;

import devhelp.bot.Database.ExerciseDB.Exercise;
import devhelp.bot.Database.ExerciseDB.ExerciseRepository;
import devhelp.bot.commands.ICommand;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;

import java.text.Normalizer;

public class ExerciseCommand implements ICommand {
    @Override
    public void execute(SlashCommandInteractionEvent event, String[] args) {
        event.deferReply().queue();

        String language = event.getOption(formattedText("linguagem")).getAsString().toLowerCase();
        String difficulty = event.getOption(formattedText("dificuldade")).getAsString().toLowerCase();

        String textDifficulty = "";

        if(difficulty.equals("fácil")){
            difficulty = "facil";
        } else if(difficulty.equals("médio")){
            difficulty = "medio";
        } else if(difficulty.equals("difícil")){
            difficulty = "dificil";
        }

        if (difficulty.equalsIgnoreCase("facil")){
            textDifficulty = ":green_square:";
        } else if (difficulty.equalsIgnoreCase("medio")){
            textDifficulty = ":yellow_square:";
        } else {
            textDifficulty = ":red_square:";
        }

        if(language.equals("py")){
            language = "python";
        } else if(language.equals("js")){
            language = "javascript";
        }

        ExerciseRepository exerciseRepository = new ExerciseRepository();
        Exercise exercise = exerciseRepository.getRandomExercise(language, difficulty);

        if (exercise == null) {
            MessageEmbed embed = new EmbedBuilder()
                    .setTitle("Ops! 😢")
                    .setDescription("Nenhum exercício encontrado para essa linguagem e dificuldade!\n" + "> Para saber quais exercícios estão disponíveis, use o comando `/help`.")
                    .setColor(0xFF0000)
                    .build();
            event.getHook().sendMessageEmbeds(embed).queue();
            return;
        }

        MessageEmbed embed = new EmbedBuilder()
                .setTitle("Exercício de " + exercise.getLanguage() + " - " + exercise.getDifficulty())
                .setDescription("Aqui está um exercício para você praticar! 🚀")
                .addField("Linguagem:", "``" + exercise.getLanguage() + "``", true)
                .addField("Dificuldade: ", "``" + exercise.getDifficulty() + "``" +  " " + textDifficulty, true)
                .addField("Exercício:\n", "```" + exercise.getExercise() + "```", false)
                .setColor(getColorByDifficulty(difficulty))
                .build();
        event.getHook().sendMessageEmbeds(embed).queue();

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
