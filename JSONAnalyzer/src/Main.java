import Data.Post;
import Data.User;
import Data.UserAndPost;
import JSONhandlers.JSONAnalyzer;
import JSONhandlers.JSONReader;

import java.util.*;

public class Main {

    final static String postsURL="https://jsonplaceholder.typicode.com/posts";
    final static String usersURL="https://jsonplaceholder.typicode.com/users";

    public static void main(String[] args) {
        JSONReader reader = new JSONReader();
        Optional<StringBuilder> response = Optional.ofNullable(reader.readFromURL(usersURL));
        //System.out.println(response.get());
        StringBuilder[] formattedUsers = JSONAnalyzer.formatUserResponse(response.get());

        response = Optional.ofNullable(reader.readFromURL(postsURL));
        StringBuilder[] formattedPosts = JSONAnalyzer.formatPostsReponse(response.get());

        User[] users = new User[formattedUsers.length];
        Post[] posts = new Post[formattedPosts.length];
        for (int i = 0; i < users.length; i++) {
            users[i] = new User(formattedUsers[i]);
        }
        for (int i = 0; i < posts.length; i++) {
            posts[i] = new Post(formattedPosts[i]);
        }

        //response.get().
        ArrayList<UserAndPost> userAndPosts = UserAndPost.combineArrays(posts, users);
        /*for(Data.UserAndPost u:userAndPosts){
            System.out.println(u);
        }*/

        for (int i = 0; i < users.length; i++) {
            int userID = users[i].getId();
            System.out.println(users[i].getName() + " napisał(a) " + userAndPosts.stream().filter(e -> e.getUserID() == userID).count() + " postów");
        }

        System.out.println();
        if(Post.checkUniqueTitles(posts)){
            System.out.println("Wszystkie posty są unikatowe");
        }

        System.out.println();
        for (int i = 0; i < users.length; i++) {
            System.out.println("Najblizej uzytkownika "+users[i].getName()+" mieszka uzytkownik "+User.findNearestUser(users[i],users).getName());
        }
    }

}
