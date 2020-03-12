public class Main {

    final static String postsURL="https://jsonplaceholder.typicode.com/posts";
    final static String usersURL="https://jsonplaceholder.typicode.com/users";

    public static void main(String[] args) {
        JSONReader reader = new JSONReader();
        reader.createJSONFromURL(postsURL);
    }
}
