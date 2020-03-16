package tests;

import JSONhandlers.JSONAnalyzer;
import JSONhandlers.JSONReader;
import Models.Post;
import Models.User;
import Models.UserAndPost;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Optional;

public class UserAndPostPrintNumberOfPostsTests implements IURLForTests {
    private JSONReader reader;
    private User[] users;
    private Post[] posts;
    private ArrayList<UserAndPost> userAndPost;
    private ByteArrayOutputStream outContent;

    @Before
    public void setUP() {
        outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        reader = new JSONReader();
        Optional<StringBuilder> response = Optional.ofNullable(reader.readFromURL(usersURL));
        if (!response.isPresent()) {
            System.out.println("Nie udalo sie pobrac danych z linku: " + usersURL);
            return;
        }
        StringBuilder[] formattedUsers = JSONAnalyzer.formatUserResponse(response.get());
        users = User.createUsersArray(formattedUsers);
        response = Optional.ofNullable(reader.readFromURL(postsURL));
        if (!response.isPresent()) {
            System.out.println("Nie udalo sie pobrac danych z linku: " + postsURL);
            return;
        }
        StringBuilder[] formattedPosts = JSONAnalyzer.formatPostsReponse(response.get());
        posts = Post.createPostsArray(formattedPosts);
        userAndPost = UserAndPost.combineArrays(posts, users);

    }

    @Test
    public void countNumberOfPostsForEachUserInSmallerArray() {
        ArrayList<UserAndPost> smaller = new ArrayList<>(userAndPost.subList(0, 25));
        UserAndPost.printNumberOfPosts(users, smaller);
        String expected = "";
        expected += users[0].getName() + " napisał(a) " + 10 + " postów";
        expected += users[1].getName() + " napisał(a) " + 10 + " postów";
        expected += users[2].getName() + " napisał(a) " + 5 + " postów";
        for (int i = 3; i < users.length; i++) {
            expected += users[i].getName() + " napisał(a) " + 0 + " postów";
        }

        Assert.assertEquals(expected.replaceAll("\n", "").replaceAll("\r", ""), outContent.toString().replaceAll("\n", "").replaceAll("\r", ""));
    }

    @Test
    public void countNumberOfPostsForEachUserInOtherSmallerArray() {
        ArrayList<UserAndPost> smaller = new ArrayList<>(userAndPost.subList(44, 67));
        UserAndPost.printNumberOfPosts(users, smaller);
        String expected = "";

        for (int i = 0; i < 4; i++) {
            expected += users[i].getName() + " napisał(a) " + 0 + " postów";
        }
        expected += users[4].getName() + " napisał(a) " + 6 + " postów";
        expected += users[5].getName() + " napisał(a) " + 10 + " postów";
        expected += users[6].getName() + " napisał(a) " + 7 + " postów";
        for (int i = 7; i < users.length; i++) {
            expected += users[i].getName() + " napisał(a) " + 0 + " postów";
        }
        Assert.assertEquals(expected.replaceAll("\n", "").replaceAll("\r", ""), outContent.toString().replaceAll("\n", "").replaceAll("\r", ""));
    }
}
