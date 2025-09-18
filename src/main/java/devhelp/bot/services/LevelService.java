package devhelp.bot.services;

public class LevelService {
  public int calculateLevel(int xp) {
    return (int) Math.sqrt(xp / 5);
  }

  public int xpToNextLevel(int currentLevel) {
    int nextLevel = currentLevel + 1;
    return nextLevel * nextLevel * 5;
  }
}
