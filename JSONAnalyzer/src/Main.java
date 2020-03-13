import Models.Post;
import Models.User;
import Models.UserAndPost;
import JSONhandlers.JSONAnalyzer;
import JSONhandlers.JSONReader;

import java.util.*;

public class Main {

    final static String postsURL = "https://jsonplaceholder.typicode.com/posts";
    final static String usersURL = "https://jsonplaceholder.typicode.com/users";

    public static void main(String[] args) {
        JSONReader reader = new JSONReader();

        Optional<StringBuilder> response = Optional.ofNullable(reader.readFromURL(usersURL));
        if (!response.isPresent()) {
            System.out.println("Nie udalo sie pobrac danych z linku: " + usersURL);
            return;
        }
        StringBuilder[] formattedUsers = JSONAnalyzer.formatUserResponse(response.get());

        response = Optional.ofNullable(reader.readFromURL(postsURL));
        if (!response.isPresent()) {
            System.out.println("Nie udalo sie pobrac danych z linku: " + postsURL);
            return;
        }

        StringBuilder[] formattedPosts = JSONAnalyzer.formatPostsReponse(response.get());

        User[] users = User.createUsersArray(formattedUsers);
        Post[] posts = Post.createPostsArray(formattedPosts);
        ArrayList<UserAndPost> userAndPosts = UserAndPost.combineArrays(posts, users);


        printNumberOfPosts(users, userAndPosts);
        System.out.println();

        if (Post.checkUniqueTitles(posts)) {
            System.out.println("Wszystkie posty są unikatowe\n");
        }

        for (int i = 0; i < users.length; i++) {
            System.out.println("Najblizej uzytkownika " + users[i].getName() + " mieszka uzytkownik " + User.findNearestUser(users[i], users).getName());
        }
        System.out.println("Polaczone:");
        for (UserAndPost u : userAndPosts) {
            System.out.println(u);
        }
    }

    public static void printNumberOfPosts(User[] users, ArrayList<UserAndPost> userAndPosts) {
        for (int i = 0; i < users.length; i++) {
            int userID = users[i].getId();
            System.out.println(users[i].getName() + " napisał(a) " + userAndPosts.stream().filter(e -> e.getUserID() == userID).count() + " postów");
        }
    }

}
