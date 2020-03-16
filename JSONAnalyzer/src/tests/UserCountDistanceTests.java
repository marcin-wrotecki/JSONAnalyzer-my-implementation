package tests;

import JSONhandlers.JSONAnalyzer;
import JSONhandlers.JSONReader;
import Models.User;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;
import java.util.Optional;

@RunWith(Parameterized.class)
public class UserCountDistanceTests implements IURLForTests {

    private double expected;
    private JSONReader reader = null;
    private int firstUserIndex;
    private int secondUserIndex;
    private static User[] users = null;
    private final double delta = 1.0;


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

    public UserCountDistanceTests(int firstUserIndex, int secondUserIndex, double expected) {
        this.expected = expected;
        this.firstUserIndex = firstUserIndex;
        this.secondUserIndex = secondUserIndex;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {0, 1, 8897.85},
                {1, 0, 8897.85},
                {0, 5, 3830.49},
                {5, 0, 3830.49},
                {0, 7, 13823.74},
                {1, 3, 15341.69},
                {4, 1, 8123.66},
                {6, 8, 14384.98},
                {9, 2, 6630.05},
                {8, 4, 14959.78},

                //calculated using https://www.geodatasource.com/distance-calculator
        });
    }

    @Test
    public void countDistanceBetweenTwoUsersUsingCountDistanceFunction() {
        Assert.assertEquals(expected, User.countDistance(users[firstUserIndex], users[secondUserIndex]), delta);
    }
}
