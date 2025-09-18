package devhelp.bot.exception;

import devhelp.bot.database.usersDB.User;

public class UserNotFoundException extends RuntimeException {
  public UserNotFoundException(User userId) {
    super("O usuário <@" + userId + "> não foi encontrado no banco de dados.");
  }

  public UserNotFoundException(String message) {
    super(message);
  }

}
