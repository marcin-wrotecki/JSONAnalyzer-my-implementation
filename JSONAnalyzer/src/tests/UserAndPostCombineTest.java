package tests;

import JSONhandlers.JSONAnalyzer;
import JSONhandlers.JSONReader;
import Models.Post;
import Models.User;
import Models.UserAndPost;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Optional;

public class UserAndPostCombineTest implements IURLForTests {

    private JSONReader reader = null;
    private static User[] users = null;
    private static Post[] posts = null;
    private static double delta = 0.01;

    @Before
    public void setUP() {
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
    }

    @Test
    public void combinePostAndUserAndCheckAttributes() {
        UserAndPost userAndPost = UserAndPost.combine(posts[0], users[0]);
        Assert.assertEquals(posts[0].getId(), userAndPost.getPostID());
        Assert.assertEquals(posts[0].getUserID(), userAndPost.getUserID());
        Assert.assertEquals(users[0].getId(), userAndPost.getUserID());
        Assert.assertEquals(posts[0].getTitle(), userAndPost.getTitle());
        Assert.assertEquals(posts[0].getBody(), userAndPost.getBody());
        Assert.assertEquals(users[0].getName(), userAndPost.getName());
        Assert.assertEquals(users[0].getUserName(), userAndPost.getUserName());
        Assert.assertEquals(users[0].getEmail(), userAndPost.getUserEmail());
        Assert.assertEquals(users[0].getAddressStreet(), userAndPost.getAddressStreet());
        Assert.assertEquals(users[0].getAddressSuite(), userAndPost.getAddressSuite());
        Assert.assertEquals(users[0].getAddressCity(), userAndPost.getAddressCity());
        Assert.assertEquals(users[0].getAddressZipCode(), userAndPost.getAddressZipCode());
        Assert.assertEquals(users[0].getGeoLat(), userAndPost.getGeoLat(), delta);
        Assert.assertEquals(users[0].getGeoLng(), userAndPost.getGeoLng(), delta);
        Assert.assertEquals(users[0].getPhone(), userAndPost.getUserPhone());
        Assert.assertEquals(users[0].getWebsite(), userAndPost.getUserWebsite());
        Assert.assertEquals(users[0].getCompanyName(), userAndPost.getCompanyName());
        Assert.assertEquals(users[0].getCompanyCatchPhrase(), userAndPost.getCompanyCatchPhrase());
        Assert.assertEquals(users[0].getCompanyBs(), userAndPost.getCompanyBs());
    }

    @Test
    public void combineUserWithWrongPostAndGetNull() {
        Assert.assertNull(UserAndPost.combine(posts[50], users[2]));
    }


}
