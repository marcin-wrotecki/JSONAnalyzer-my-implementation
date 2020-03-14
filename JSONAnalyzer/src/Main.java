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

        Optional<StringBuilder[]> formattedUsers=Optional.ofNullable(getFormattedData(usersURL));
        Optional<StringBuilder[]> formattedPosts=Optional.ofNullable(getFormattedData(postsURL));
        if(!formattedPosts.isPresent() || !formattedUsers.isPresent()){
            return;
        }

        User[] users = User.createUsersArray(formattedUsers.get());
        Post[] posts = Post.createPostsArray(formattedPosts.get());
        ArrayList<UserAndPost> userAndPosts = UserAndPost.combineArrays(posts, users);

        printNumberOfPosts(users, userAndPosts);
        System.out.println();

        System.out.println("Lista postów o powtarzających się tytułach:");
        if (Post.checkUniqueTitlesAndPrintDuplicates(posts)) {
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

    public static StringBuilder[] getFormattedData(String URL){
        JSONReader reader = new JSONReader();

        Optional<StringBuilder> response = Optional.ofNullable(reader.readFromURL(URL));
        if (!response.isPresent()) {
            System.out.println("Nie udalo sie pobrac danych z linku: " + URL);
            return null;
        }
        if(URL.equals(postsURL))
            return JSONAnalyzer.formatPostsReponse(response.get());
        else if(URL.equals(usersURL))
            return JSONAnalyzer.formatUserResponse(response.get());
        else {
            System.out.println("Nieobslugiwany URL");
            return null;
        }
    }

}
