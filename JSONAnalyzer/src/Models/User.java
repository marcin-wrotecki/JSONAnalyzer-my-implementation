package Models;

import JSONhandlers.JSONAnalyzer;

public class User {
    private int id;
    private String name;
    private String userName;
    private String email;
    private String[] address;
    private double[] geo;
    private String phone;
    private String website;
    private String[] company;
    private final static int earthRadius = 6371;

    public User(StringBuilder source) {
        id = JSONAnalyzer.createIntAttribute("id", source);
        name = JSONAnalyzer.createStringAttribute("name", source);
        userName = JSONAnalyzer.createStringAttribute("username", source);
        email = JSONAnalyzer.createStringAttribute("email", source);
        address = new String[4];
        address[0] = JSONAnalyzer.createStringAttribute("address:street", source);//taka konwencje przyjalem dla parametrow zagniezdzonych
        address[1] = JSONAnalyzer.createStringAttribute("address:suite", source);
        address[2] = JSONAnalyzer.createStringAttribute("address:city", source);
        address[3] = JSONAnalyzer.createStringAttribute("address:zipcode", source);
        geo = new double[2];
        geo[0] = JSONAnalyzer.createDoubleAttribute("address:geo:lat", source);
        geo[1] = JSONAnalyzer.createDoubleAttribute("address:geo:lng", source);
        phone = JSONAnalyzer.createStringAttribute("phone", source);
        website = JSONAnalyzer.createStringAttribute("website", source);
        company = new String[3];
        company[0] = JSONAnalyzer.createStringAttribute("company:name", source);
        company[1] = JSONAnalyzer.createStringAttribute("company:catchPhrase", source);
        company[2] = JSONAnalyzer.createStringAttribute("company:bs", source);

    }

    public static User[] createUsersArray(StringBuilder[] formattedString) {
        User[] users = new User[formattedString.length];
        for (int i = 0; i < users.length; i++) {
            users[i] = new User(formattedString[i]);
        }
        return users;
    }

    public String toString() {
        return "{    \"id\": " + getId() + ",    \"name\": \"" + getName() + "\",    \"username\": \"" + getUserName() + "\",    \"email\": \"" + getEmail() + "\",    \"address\": {      \"street\": \"" + getAddressStreet() + "\",      \"suite\": \"" + getAddressSuite() + "\",      \"city\": \"" + getAddressCity() + "\",      \"zipcode\": \"" + getAddressZipCode() + "\",      \"geo\": {        \"lat\": \"" + getGeoLat() + "\",        \"lng\": \"" + getGeoLng() + "\"      }    },    \"phone\": \"" + getPhone() + "\",    \"website\": \"" + getWebsite() + "\",    \"company\": {      \"name\": \"" + getCompanyName() + "\",      \"catchPhrase\": \"" + getCompanyCatchPhrase() + "\",      \"bs\": \"" + getCompanyBs() + "\"    }  }";
    }

    public static User findNearestUser(User u, User[] usersArray) {
        int index = 0;
        double distance = Double.MAX_VALUE;
        double temp = 0;
        for (int i = 0; i < usersArray.length; i++) {
            if (u.getId() != usersArray[i].getId()) {
                temp = countDistance(u, usersArray[i]);
                if (distance > temp) {
                    distance = temp;
                    index = i;
                }
            }
        }
        //System.out.println(distance);
        return usersArray[index];
    }

    public static double countDistance(User user1, User user2) {
        double dlong = Math.toRadians(user2.getGeoLng() - user1.getGeoLng());
        double dlat = Math.toRadians(user2.getGeoLat() - user1.getGeoLat());

        double distance = Math.pow(Math.sin(dlat / 2), 2) +
                Math.cos(Math.toRadians(user1.getGeoLat())) * Math.cos(Math.toRadians(user2.getGeoLat())) *
                        Math.pow(Math.sin(dlong / 2), 2);

        distance = 2 * Math.asin(Math.sqrt(distance));
        distance = distance *earthRadius;
        return distance;
    }

    public int getId() {
        return id;
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


}
