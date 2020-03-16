package tests;

import JSONhandlers.JSONAnalyzer;
import JSONhandlers.JSONReader;
import Models.User;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Optional;

public class UserFindNearestUserTests implements IURLForTests {
    private JSONReader reader = null;
    private static User[] users = null;

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
    }

    @Test
    public void getNearestUserForFirstUserInSmallerArray() {
        User[] myUsers = {users[0], users[1], users[2], users[3], users[4]};
        Assert.assertEquals(users[4], User.findNearestUser(users[0], myUsers));
    }

    @Test
    public void getNearestUserForSecondUserInSmallerArray() {
        User[] myUsers = {users[0], users[1], users[2], users[3], users[4]};
        Assert.assertEquals(users[2], User.findNearestUser(users[1], myUsers));

    }

    @Test
    public void getNearestUserForThirdUserInSmallerArray() {
        User[] myUsers = {users[0], users[1], users[2], users[3], users[4]};
        Assert.assertEquals(users[1], User.findNearestUser(users[2], myUsers));
    }

    @Test
    public void getNearestUserForFourthUserInSmallerArray() {
        User[] myUsers = {users[0], users[1], users[2], users[3], users[4]};
        Assert.assertEquals(users[0], User.findNearestUser(users[3], myUsers));
    }

    @Test
    public void getNearestUserForFifthUserInSmallerArray() {
        User[] myUsers = {users[0], users[1], users[2], users[3], users[4]};
        Assert.assertEquals(users[0], User.findNearestUser(users[4], myUsers));
    }

    @Test
    public void getNearestUserForSixthUserInSmallerArray() {
        User[] myUsers = {users[5], users[6], users[7], users[8], users[9]};
        Assert.assertEquals(users[9], User.findNearestUser(users[5], myUsers));
    }

    @Test
    public void getNearestUserForSeventhUserInSmallerArray() {
        User[] myUsers = {users[5], users[6], users[7], users[8], users[9]};
        Assert.assertEquals(users[9], User.findNearestUser(users[6], myUsers));
    }

    @Test
    public void getNearestUserForEighthUserInSmallerArray() {
        User[] myUsers = {users[5], users[6], users[7], users[8], users[9]};
        Assert.assertEquals(users[8], User.findNearestUser(users[7], myUsers));
    }

    @Test
    public void getNearestUserForNinthUserInSmallerArray() {
        User[] myUsers = {users[5], users[6], users[7], users[8], users[9]};
        Assert.assertEquals(users[7], User.findNearestUser(users[8], myUsers));
    }

    @Test
    public void getNearestUserForTenthUserInSmallerArray() {
        User[] myUsers = {users[5], users[6], users[7], users[8], users[9]};
        Assert.assertEquals(users[5], User.findNearestUser(users[9], myUsers));
    }
}
