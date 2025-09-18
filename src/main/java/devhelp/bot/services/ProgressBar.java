package devhelp.bot.services;

public class ProgressBar {
  public String generateProgressBar(int currentXp, int xpForNextLevel) {
    int barLength = 5;
    double progress = (double) currentXp / xpForNextLevel;
    int filledLength = (int) (barLength * progress);
    StringBuilder bar = new StringBuilder();
    for (int i = 0; i < filledLength; i++) {
      if(i < 1){
        bar.append("<:greenleftbararge:1416618698340438087>");
      }
      if(i >= filledLength - 1 && filledLength == barLength){
        bar.append("<:greenrigthbararg:1416618679998746674>");
        break;
      }
      bar.append("<:greenmiddlebarar:1416618689611960440>");
    }
    for (int i = filledLength; i < barLength; i++) {
      if(i < 1){
        bar.append("<:greyleftbarargen:1416618657307557950>");
      }
      if(i >= barLength - 1){
        bar.append("<:bitis_bar_gri55:1416618634264182825> %" + (int)((double)currentXp / xpForNextLevel * 100) + "");
        break;
      }
      bar.append("<:orta_bar_gri57:1416618647115272262>");
    }
    return bar.toString();
  }
}
