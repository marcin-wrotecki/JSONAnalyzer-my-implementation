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

        Optional<StringBuilder[]> formattedUsers = Optional.ofNullable(getFormattedData(usersURL));
        Optional<StringBuilder[]> formattedPosts = Optional.ofNullable(getFormattedData(postsURL));
        if (!formattedPosts.isPresent() || !formattedUsers.isPresent()) {
            return;
        }

        User[] users = User.createUsersArray(formattedUsers.get());
        Post[] posts = Post.createPostsArray(formattedPosts.get());
        ArrayList<UserAndPost> userAndPosts = UserAndPost.combineArrays(posts, users);

        UserAndPost.printNumberOfPosts(users, userAndPosts);

        System.out.println("\nLista postów o powtarzających się tytułach:");
        if (Post.checkUniqueTitlesAndPrintDuplicates(posts)) {
            System.out.println("Wszystkie posty są unikatowe\n");
        }
        for (int i = 0; i < users.length; i++) {
            System.out.println("Najblizej uzytkownika " + users[i].getUserName() + " mieszka uzytkownik " + User.findNearestUser(users[i], users).getUserName());
        }

        System.out.println("\nPolaczone:");
        for (UserAndPost u : userAndPosts) {
            System.out.println(u);
        }
    }



    public static StringBuilder[] getFormattedData(String URL) {
        JSONReader reader = new JSONReader();

        Optional<StringBuilder> response = Optional.ofNullable(reader.readFromURL(URL));
        if (!response.isPresent()) {
            System.out.println("Nie udalo sie pobrac danych z linku: " + URL);
            return null;
        }
        if (URL.equals(postsURL))
            return JSONAnalyzer.formatPostsReponse(response.get());
        else if (URL.equals(usersURL))
            return JSONAnalyzer.formatUserResponse(response.get());
        else {
            System.out.println("Nieobslugiwany URL");
            return null;
        }
    }

}
