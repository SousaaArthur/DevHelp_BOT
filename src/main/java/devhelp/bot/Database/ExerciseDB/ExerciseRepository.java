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

            Document exerciseDoc = exercisesCollection.find(query).first();

            if (exerciseDoc != null) {
                String title = exerciseDoc.getString("title");
                String exercise = exerciseDoc.getString("exercise");
                return new Exercise(
                        exerciseDoc.getObjectId("id"),
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
}
