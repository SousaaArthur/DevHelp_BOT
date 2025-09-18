package devhelp.bot.database.memeDB;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Aggregates;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;

import devhelp.bot.database.DatabaseManager;

import org.bson.Document;
import org.bson.types.ObjectId;

import java.util.Arrays;

public class MemeRepository {

  public Meme getRandomMeme() {
    MongoDatabase database = DatabaseManager.connect();
    MongoCollection<Document> memesCollection = database.getCollection("memes");

    try {
      Document randomMeme = memesCollection.aggregate(Arrays.asList(Aggregates.sample(1))).first();

      if (randomMeme != null) {
        ObjectId memeID = randomMeme.getObjectId("_id");

        memesCollection.updateOne(
            Filters.eq("_id", memeID),
            Updates.inc("views", 1));

        return new Meme(
            memeID,
            randomMeme.getString("url"),
            randomMeme.getString("category"),
            randomMeme.getInteger("views", 0),
            randomMeme.getInteger("likes", 0));
      } else {
        System.err.println("Nenhum meme encontrado no banco! 🧐");
      }
    } catch (Exception e) {
      System.out.println(e);
    }
    return null;
  }

  public void incrementLikes(ObjectId memeId) {
    MongoDatabase database = DatabaseManager.connect();
    MongoCollection<Document> memesCollection = database.getCollection("memes");

    memesCollection.updateOne(
        Filters.eq("_id", memeId),
        Updates.inc("likes", 1));
  }

  public Meme getMemeById(ObjectId id) {
    MongoDatabase database = DatabaseManager.connect();
    MongoCollection<Document> memesCollection = database.getCollection("memes");

    Document doc = memesCollection.find(Filters.eq("_id", id)).first();
    if (doc != null) {
      return new Meme(
          doc.getObjectId("_id"),
          doc.getString("url"),
          doc.getString("category"),
          doc.getInteger("views", 0),
          doc.getInteger("likes", 0));
    }
    return null;
  }

}
