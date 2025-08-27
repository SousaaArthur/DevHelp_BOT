package devhelp.bot.Database.ExerciseDB;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import devhelp.bot.Database.DatabaseManager;
import org.bson.Document;

public class ExerciseRepository {

    MongoDatabase database = DatabaseManager.connect();
    MongoCollection<Document> exercisesCollection = database.getCollection("exercises");

    public Exercise getRandomExercise(String language, String difficulty){

        try {
            Document query = new Document("language", language)
                    .append("difficulty", difficulty);

            java.util.List<Document> randomDocs = exercisesCollection.aggregate(
                    java.util.Arrays.asList(
                            new Document("$match", query),
                            new Document("$sample", new Document("size", 1))
                    )
            ).into(new java.util.ArrayList<>());

            if (!randomDocs.isEmpty()) {
                Document exerciseDoc = randomDocs.get(0);
                return new Exercise(
                        exerciseDoc.getObjectId("_id"),
                        exerciseDoc.getString("title"),
                        exerciseDoc.getString("exercise"),
                        exerciseDoc.getString("language"),
                        exerciseDoc.getString("difficulty")
                );
            } else {
                System.err.println("Nenhum exercício encontrado para a linguagem e dificuldade especificadas! 🧐");
            }

        } catch (Exception e) {
            System.err.print("Erro ao buscar o exercício! 😢 Detalhes: " + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    public int hasExercises() {
        return exercisesCollection.find().into(new java.util.ArrayList<>()).size();
    }

}
