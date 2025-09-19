package devhelp.bot.exception;

public class UserGithubNotFoundException extends RuntimeException {
  public UserGithubNotFoundException(String message) {
    super(message);
  }
}
