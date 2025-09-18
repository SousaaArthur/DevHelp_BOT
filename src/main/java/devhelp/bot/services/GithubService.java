package devhelp.bot.services;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;

import org.json.JSONArray;
import org.json.JSONObject;

public class GithubService {
  private String BASE_URL = "https://api.github.com/users/";
  HttpClient client = HttpClient.newHttpClient();

  public String getAvatarGithub(String userName){
    String url = BASE_URL + userName;
    HttpRequest request = HttpRequest.newBuilder()
      .uri(URI.create(url))
      .header("Accept", "application/vnd.github.v3+json")
      .build();
    try{
      HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
      if (response.statusCode() == 200) {
        JSONObject json = new JSONObject(response.body());
        return json.getString("avatar_url");
      } else {
        System.err.println("Erro na API GitHub: " + response.statusCode());
      }
    } catch(IOException | InterruptedException e) {
      e.printStackTrace();
    }
    return null;
  }

  public String getUserInfo(String userName){
    String url = BASE_URL + userName;
    HttpRequest request = HttpRequest.newBuilder()
      .uri(URI.create(url))
      .build();
    try {
      HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
      if(response.statusCode() == 200){
          JSONObject json = new JSONObject(response.body());
          int followingCount = json.getInt("following");
          int followersCount = json.getInt("followers");
          return followingCount + " seguindo" + " | " + followersCount + " seguidores";
      }
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
    return null;
  }

  public String getUserFollowers(String userName){
    String url = BASE_URL + userName;
    HttpRequest request = HttpRequest.newBuilder()
      .uri(URI.create(url))
      .build();
    try {
      HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
      if(response.statusCode() == 200){
          JSONObject json = new JSONObject(response.body());
          return json.getInt("followers") + " seguidores";
      }
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
    return null;
  }

  public String getCommitStreak(String userName, String token) {
      String graphqlUrl = "https://api.github.com/graphql";

      // Query GraphQL
      String query = """
          {
            user(login: "%s") {
              contributionsCollection {
                contributionCalendar {
                  weeks {
                    contributionDays {
                      date
                      contributionCount
                    }
                  }
                }
              }
            }
          }
      """.formatted(userName);

      JSONObject json = new JSONObject();
      json.put("query", query);

      HttpRequest request = HttpRequest.newBuilder()
              .uri(URI.create(graphqlUrl))
              .header("Authorization", "Bearer " + token) // token GitHub
              .header("Content-Type", "application/json")
              .POST(HttpRequest.BodyPublishers.ofString(json.toString()))
              .build();

      try {
          HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
          if (response.statusCode() != 200) {
              System.err.println("Erro GraphQL GitHub: " + response.statusCode());
              return null;
          }

          JSONObject result = new JSONObject(response.body());
          JSONArray weeks = result
                  .getJSONObject("data")
                  .getJSONObject("user")
                  .getJSONObject("contributionsCollection")
                  .getJSONObject("contributionCalendar")
                  .getJSONArray("weeks");

          int currentStreak = 0;
          int maxStreak = 0;

          for (int i = 0; i < weeks.length(); i++) {
              JSONArray days = weeks.getJSONObject(i).getJSONArray("contributionDays");
              for (int j = 0; j < days.length(); j++) {
                  int count = days.getJSONObject(j).getInt("contributionCount");
                  if (count > 0) {
                      currentStreak++;
                      if (currentStreak > maxStreak) maxStreak = currentStreak;
                  } else {
                      currentStreak = 0;
                  }
              }
          }

          return "Maior sequência de dias consecutivos com commits: " + maxStreak;

      } catch (IOException | InterruptedException e) {
          e.printStackTrace();
      }

      return null;
  }

  public String getCurrentCommitStreak(String userName, String token) {
    String graphqlUrl = "https://api.github.com/graphql";

    String query = """
        {
          user(login: "%s") {
            contributionsCollection {
              contributionCalendar {
                weeks {
                  contributionDays {
                    date
                    contributionCount
                  }
                }
              }
            }
          }
        }
    """.formatted(userName);

    JSONObject json = new JSONObject();
    json.put("query", query);

    HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create(graphqlUrl))
            .header("Authorization", "Bearer " + token)
            .header("Content-Type", "application/json")
            .POST(HttpRequest.BodyPublishers.ofString(json.toString()))
            .build();

    try {
        HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
        if (response.statusCode() != 200) {
            System.err.println("Erro GraphQL GitHub: " + response.statusCode());
            return null;
        }

        JSONObject result = new JSONObject(response.body());
        JSONArray weeks = result
                .getJSONObject("data")
                .getJSONObject("user")
                .getJSONObject("contributionsCollection")
                .getJSONObject("contributionCalendar")
                .getJSONArray("weeks");

        int currentStreak = 0;
        boolean counting = true;

        for (int i = weeks.length() - 1; i >= 0 && counting; i--) {
            JSONArray days = weeks.getJSONObject(i).getJSONArray("contributionDays");

            for (int j = days.length() - 1; j >= 0; j--) {
                int count = days.getJSONObject(j).getInt("contributionCount");
                if (count > 0) {
                    currentStreak++;
                } else {
                    counting = false;
                    break;
                }
            }
        }

        return "Sequência atual de commits: " + currentStreak + " dias";

    } catch (IOException | InterruptedException e) {
        e.printStackTrace();
    }

    return null;
  }
}
