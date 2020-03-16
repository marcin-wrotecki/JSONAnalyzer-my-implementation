package Models;

import JSONhandlers.JSONAnalyzer;

import java.util.ArrayList;
import java.util.HashSet;

public class Post implements Cloneable {

    private int userID;
    private int id;
    private String title;
    private String body;

    public Post(StringBuilder source) {
        userID = JSONAnalyzer.createIntAttribute("userID", source);
        id = JSONAnalyzer.createIntAttribute("id", source);
        title = JSONAnalyzer.createStringAttribute("title", source);
        body = JSONAnalyzer.createStringAttribute("body", source);
    }

    public static Post[] createPostsArray(StringBuilder[] formattedString) {
        Post[] posts = new Post[formattedString.length];

        for (int i = 0; i < posts.length; i++) {
            posts[i] = new Post(formattedString[i]);
        }
        return posts;
    }

    public static boolean checkUniqueTitlesAndPrintDuplicates(Post[] posts) {
        HashSet<String> uniqueTitle = new HashSet<>();
        ArrayList<String> printedTitles = new ArrayList<>();//to avoid duplications
        for (int i = 0; i < posts.length; i++) {
            if (!uniqueTitle.add(posts[i].getTitle())) {
                if (!printedTitles.contains(posts[i].getTitle())) {
                    System.out.println(posts[i].getTitle());
                    printedTitles.add(posts[i].getTitle());
                }
            }
        }
        return uniqueTitle.size() == posts.length;
    }

    public String toString() {
        return "{    \"userId\": " + getUserID() + ",    \"id\": " + getId() + ",    \"title\": \"" + getTitle() + "\",    \"body\": \"" + getBody() + "\" }";
    }

    public int getUserID() {
        return userID;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getBody() {
        return body;
    }


    public void setTitle(String title) {
        this.title = title;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Post clone() {
        try {
            return (Post) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return null;
    }
}
