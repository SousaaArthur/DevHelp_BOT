package devhelp.bot.Database.MemeDB;

import org.bson.types.ObjectId;

public class Meme {
    private ObjectId id;
    private String url;
    private String category;
    private int views;
    private int likes;

    Meme(ObjectId id, String url, String category, int views, int likes) {
        this.id = id;
        this.url = url;
        this.category = category;
        this.views = views;
        this.likes = likes;
    }

    public ObjectId getId() {
        return id;
    }

    public String getUrl() {
        return url;
    }

    public String getCategory() {
        return category;
    }

    public int getViews() {
        return views;
    }

    public int getLikes() {
        return likes;
    }

}
