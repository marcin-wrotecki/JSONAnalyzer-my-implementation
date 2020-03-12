package Data;

import JSONhandlers.JSONAnalyzer;

import java.util.HashSet;

public class Post {

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

    public static boolean checkUniqueTitles(Post[] posts){
        HashSet<String> uniqueTitle = new HashSet<>();
        System.out.println("Lista postów o powtarzających się tytułach:");
        for(int i=0;i<posts.length;i++){
            if(!uniqueTitle.add(posts[i].getTitle()))
                System.out.println(posts[i].getTitle());
        }
        return uniqueTitle.size()==posts.length;
    }
    public String toString() {
        return "{    \"userId\": " + getUserID() + ",    \"id\": " + getId() + ",    \"title\": " + getTitle() + ",    \"body\": " + getBody() + " }";
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
}