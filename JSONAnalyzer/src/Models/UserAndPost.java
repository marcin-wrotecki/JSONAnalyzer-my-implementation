package Models;

import java.util.ArrayList;

public class UserAndPost {

    private int userID;
    private String name;
    private String userName;
    private String email;
    private String[] address;
    private double[] geo;
    private String phone;
    private String website;
    private String[] company;
    private int postID;
    private String title;
    private String body;

    private UserAndPost(Post post, User user) {
        userID = user.getId();
        name = user.getName();
        userName = user.getUserName();
        email = user.getEmail();
        address = new String[4];
        address[0] = user.getAddressStreet();
        address[1] = user.getAddressSuite();
        address[2] = user.getAddressCity();
        address[3] = user.getAddressZipCode();
        geo = new double[2];
        geo[0] = user.getGeoLat();
        geo[1] = user.getGeoLng();
        phone = user.getPhone();
        website = user.getWebsite();
        company = new String[3];
        company[0] = user.getCompanyName();
        company[1] = user.getCompanyCatchPhrase();
        company[2] = user.getCompanyBs();
        userID = post.getUserID();
        postID = post.getId();
        title = post.getTitle();
        body = post.getBody();

    }

    public static ArrayList<UserAndPost> combineArrays(Post[] posts, User[] users) {
        ArrayList<UserAndPost> combinedArr = new ArrayList<>();
        boolean founded = false;
        for (int i = 0; i < posts.length; i++) {
            for (int j = 0; j < users.length; j++) {
                if (posts[i].getUserID() == users[j].getId()) {
                    combinedArr.add(combine(posts[i], users[j]));
                    founded = true;
                } else if (j == (users.length - 1) && !founded) {
                    System.out.println("Nie udalo sie znalezc uzytkownika dla postu o id " + posts[i].getId());
                }
            }
            founded = false;

        }
        return combinedArr;
    }

    public static UserAndPost combine(Post post, User user) {
        if (post.getUserID() == user.getId()) {
            return new UserAndPost(post, user);
        } else {
            System.out.println("Can't combine post and user -> different userID");
            return null;
        }
    }

    public String toString() {
        return "{    \"userid\": " + getUserID() + ",    \"name\": \"" + getName() + "\",    \"username\": \"" + getUserName() + "\",    \"email\": \"" + getEmail() + "\",    \"address\": {      \"street\": \"" + getAddressStreet() + "\",      \"suite\": \"" + getAddressSuite() + "\",      \"city\": \"" + getAddressCity() + "\",      \"zipcode\": \"" + getAddressZipCode() + "\",      \"geo\": {        \"lat\": \"" + getGeoLat() + "\",        \"lng\": \"" + getGeoLng() + "\"      }    },    \"phone\": \"" + getPhone() + "\",    \"website\": \"" + getWebsite() + "\",    \"company\": {      \"name\": \"" + getCompanyName() + "\",      \"catchPhrase\": \"" + getCompanyCatchPhrase() + "\",      \"bs\": \"" + getCompanyBs() + "\"    }" + "   \"postid\": " + getPostID() + ",    \"title\": \"" + getTitle() + "\",    \"body\": \"" + getBody() + "\" }";
    }

    public int getUserID() {
        return userID;
    }

    public int getPostID() {
        return postID;
    }

    public String getName() {
        return name;
    }

    public String getUserName() {
        return userName;
    }

    public String getEmail() {
        return email;
    }

    public String getAddressStreet() {
        return address[0];
    }

    public String getAddressSuite() {
        return address[1];
    }

    public String getAddressCity() {
        return address[2];
    }

    public String getAddressZipCode() {
        return address[3];
    }

    public double getGeoLat() {
        return geo[0];
    }

    public double getGeoLng() {
        return geo[1];
    }

    public String getPhone() {
        return phone;
    }

    public String getWebsite() {
        return website;
    }

    public String getCompanyName() {
        return company[0];
    }

    public String getCompanyCatchPhrase() {
        return company[1];
    }

    public String getCompanyBs() {
        return company[2];
    }

    public String getTitle() {
        return title;
    }

    public String getBody() {
        return body;
    }

}
