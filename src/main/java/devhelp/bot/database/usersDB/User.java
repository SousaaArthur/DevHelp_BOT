package devhelp.bot.database.usersDB;
import java.util.List;

import org.bson.Document;

public class User {
  private String userId;
  private String userName;
  private int xp;
  private int level;
  private String bio;
  private String themeColor;
  private String title;
  private int messagesSent;
  private String avatarUrl;
  private List<String> languages;
  private List<String> role;
  private String githubUser;
  private int githubStreak;
  
  public User(String userId, String userName, String avatarUrl, List<String> languages, List<String> role) {
    this.userId = userId;
    this.userName = userName;
    this.xp = 0;
    this.level = 0;
    this.bio = "Sem biografia adicione uma com ``/profile addbio``";
    this.themeColor = "#5865F2";
    this.title = "Explorador(a)";
    this.messagesSent = 0;
    this.avatarUrl = avatarUrl;
    this.languages = languages;
    this.role = role;
  }

  public static User fromDocument(Document doc) {
    User user = new User(doc.getString("userId")); // cria o objeto com o ID
    user.setUserName(doc.getString("userName"));
    user.setXp(doc.getInteger("xp", 0));
    user.setLevel(doc.getInteger("level", 0));
    user.setBio(doc.getString("bio"));
    user.setThemeColor(doc.getString("themeColor"));
    user.setTitle(doc.getString("title"));
    user.setAvatarUrl(doc.getString("avatarUrl"));
    user.setLanguages(doc.getList("languages", String.class));
    user.setRole(doc.getList("roles", String.class));
    user.setGithubUser(doc.getString("githubUser"));
    user.setMessagesSent(doc.getInteger("messagesSent", 0));
    return user;
  } 
  public User(String userId) {
    this.userId = userId;
  }

  public int getGithubStreak() {
    return githubStreak;
  }

  public void setGithubStreak(int githubStreak) {
    this.githubStreak = githubStreak;
  }

  public String getGithubUser() {
    return githubUser;
  }
  public void setGithubUser(String githubUser) {
    this.githubUser = githubUser;
  }
  public List<String> getLanguages() {
    return languages;
  }
  public void setLanguages(List<String> languages) {
    this.languages = languages;
  }
  public List<String> getRole() {
    return role;
  }
  public void setRole(List<String> role) {
    this.role = role;
  }
  public String getUserName() {
    return userName;
  }
  public void setUserName(String userName) {
    this.userName = userName;
  }
  public String getAvatarUrl() {
    return avatarUrl;
  }
  public void setAvatarUrl(String avatarUrl) {
    this.avatarUrl = avatarUrl;
  }
  public String getUserId() {
    return userId;
  }
  public int getXp() {
    return xp;
  }
  public int getLevel() {
    return level;
  }
  public String getBio() {
    return bio;
  }
  public String getThemeColor() {
    return themeColor;
  }
  public String getTitle() {
    return title;
  }
  public void setUserId(String userId) {
    this.userId = userId;
  }
  public void setXp(int xp) {
    this.xp = xp;
  }
  public void setLevel(int level) {
    this.level = level;
  }
  public void setBio(String bio) {
    this.bio = bio;
  }
  public void setThemeColor(String themeColor) {
    this.themeColor = themeColor;
  }
  public void setTitle(String title) {
    this.title = title;
  }
  public int getMessagesSent() {
    return messagesSent;
  }
  public void setMessagesSent(int messagesSent) {
    this.messagesSent = messagesSent;
  }
}
