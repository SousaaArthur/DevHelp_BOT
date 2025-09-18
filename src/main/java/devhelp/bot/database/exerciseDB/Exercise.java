package devhelp.bot.database.exerciseDB;

import org.bson.types.ObjectId;

public class Exercise {
  ObjectId id;
  String title;
  String exercise;
  String language;
  String difficulty;

  Exercise(ObjectId id, String title, String exercise, String language, String difficulty) {
    this.id = id;
    this.title = title;
    this.exercise = exercise;
    this.language = language;
    this.difficulty = difficulty;
  }

  public ObjectId getId() {
    return id;
  }

  public String getTitle() {
    return title;
  }

  public String getExercise() {
    return exercise;
  }

  public String getLanguage() {
    return language;
  }

  public String getDifficulty() {
    return difficulty;
  }

}
