package devhelp.bot.database;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import devhelp.bot.config.BotConfig;

public class DatabaseManager {

  private static MongoClient mongoClient;
  private static MongoDatabase database;

  public static MongoDatabase connect() {
    if (mongoClient == null) {
      mongoClient = MongoClients.create(BotConfig.getUrlDatabase());
      database = mongoClient.getDatabase("devhelper_bot");
    }
    return database;
  }

}
