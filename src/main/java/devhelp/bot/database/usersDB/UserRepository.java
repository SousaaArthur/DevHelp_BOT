package devhelp.bot.database.usersDB;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.bson.Document;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import devhelp.bot.database.DatabaseManager;
import net.dv8tion.jda.api.entities.Member;

public class UserRepository {
  MongoDatabase database = DatabaseManager.connect();
  MongoCollection<Document> usersCollection = database.getCollection("users");
  
  // public void update(){
  //   Document query = new Document();
  //   Document update = new Document("$set", new Document("title", "Explorador(a)"));
  //   UpdateResult result = usersCollection.updateMany(query, update);
  //   System.out.println("Documentos atualizados: " + result.getModifiedCount());
  // }

  public int getUserRank(String userId) {
    List<User> users = getAllUsersSorted();
    for (int i = 0; i < users.size(); i++) {
        if (users.get(i).getUserId().equals(userId)) {
            return i + 1;
        }
    }
    return -1;
}

  public List<User> getAllUsersSorted() {
      return usersCollection.find()
              .sort(new Document("level", -1).append("xp", -1))
              .map(User::fromDocument)
              .into(new ArrayList<>());
  }
  

  public List<User> getTopUsers(int limit) {
    return usersCollection.find()
            .sort(new Document("level", -1).append("xp", -1))
            .limit(limit)
            .map(User::fromDocument)
            .into(new ArrayList<>());
  }

  public User setUser(String userId, String userName, String avatarUrl, Set<String> languages, Set<String> role, Member member) {
    User user = new User(userId, userName, avatarUrl, functionFilter(member, languages), functionFilter(member, role));

    Document newUserDoc = new Document("userId", user.getUserId())
        .append("userName", user.getUserName())
        .append("xp", user.getXp())
        .append("level", user.getLevel())
        .append("bio", user.getBio())
        .append("themeColor", user.getThemeColor())
        .append("title", user.getTitle())
        .append("avatarUrl", user.getAvatarUrl())
        .append("languages", user.getLanguages())
        .append("roles", user.getRole())
        .append("messagesSent", user.getMessagesSent())
        .append("githubUser", user.getGithubUser());
    usersCollection.insertOne(newUserDoc);
    return user;
  }

  public String getTopUsersString(int limit) {
    List<User> topUsers = getTopUsers(limit);
    StringBuilder sb = new StringBuilder("# 🏆 **Top " + limit + " Usuários** 🏆\n\n");

    for (int i = 0; i < topUsers.size(); i++) {
        User user = topUsers.get(i);
        int position = i + 1;
        if(position == 1) {
            sb.append("🥇 ");
        } else if(position == 2) {
            sb.append("🥈 ");
        } else if(position == 3) {
            sb.append("🥉 ");
        } else {
            sb.append("**#" + position + "** ");
        }
        sb.append(String.format("** %s •** __Nível__ %d \n",
                user.getUserName(),
                user.getLevel()));
    }

    return sb.toString();
}

  public String getRankAroundUser(String userId, int windowSize) {
      List<User> users = getAllUsersSorted();
      int rank = getUserRank(userId);

      if (rank == -1) return "Usuário não encontrado no ranking.";

      // Define limites da janela
      int start = Math.max(0, rank - windowSize); 
      int end = Math.min(users.size(), rank + windowSize);

      StringBuilder sb = new StringBuilder("# 📊 **Ranking próximo de você** 📊\n\n");

      for (int i = start; i < end; i++) {
          User user = users.get(i);
          int pos = i + 1;

        if(pos == 1) {
            sb.append("🥇 ");
        } else if(pos == 2) {
            sb.append("🥈 ");
        } else if(pos == 3) {
            sb.append("🥉 ");
        } else {
            sb.append("**#" + pos + "** ");
        }

          if (user.getUserId().equals(userId)) {
              sb.append(String.format("** %s**  •  __Nível__ %d ⬅️\n",
                      user.getUserName(), user.getLevel()));
          } else {
              sb.append(String.format("** %s**  •  __Nível__ %d\n",
                      user.getUserName(), user.getLevel()));
          }
      }

      return sb.toString();
  }

  public User getUser(String userId) {
    Document query = new Document("userId", userId);
    Document userDoc = usersCollection.find(query).first();
    if (userDoc != null) { 
      User user = new User(userDoc.getString("userId"));
      user.setUserName(userDoc.getString("userName"));
      user.setXp(userDoc.getInteger("xp", 0));
      user.setLevel(userDoc.getInteger("level", 0));
      user.setBio(userDoc.getString("bio"));
      user.setThemeColor(userDoc.getString("themeColor"));
      user.setTitle(userDoc.getString("title"));
      user.setAvatarUrl(userDoc.getString("avatarUrl"));
      user.setLanguages(userDoc.getList("languages", String.class));
      user.setRole(userDoc.getList("roles", String.class));
      user.setGithubUser(userDoc.getString("githubUser"));
      user.setMessagesSent(userDoc.getInteger("messagesSent", 0));
      return user;
    } else {
      return null;
    }
  }

  public void updateUser(User user) {
    Document query = new Document("userId", user.getUserId());
    Document update = new Document("$set", new Document("xp", user.getXp())
        .append("userName", user.getUserName())
        .append("level", user.getLevel())
        .append("bio", user.getBio())
        .append("themeColor", user.getThemeColor())
        .append("title", user.getTitle())
        .append("messagesSent", user.getMessagesSent())
        .append("avatarUrl", user.getAvatarUrl())
        .append("languages", user.getLanguages())
        .append("roles", user.getRole())
        .append("messagesSent", user.getMessagesSent())
        .append("githubUser", user.getGithubUser()));
    usersCollection.updateOne(query, update);
  }

  public List<String> functionFilter(Member member, Set<String> roles){
    return member.getRoles().stream()
          .filter(role -> roles.contains(role.getId()))
          .map(role -> role.getId())
          .toList();
  }

}
