import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Optional;

public class JSONReader {

    public  void createJSONFromURL(String webSiteURL){

        Optional<StringBuilder> response=Optional.ofNullable(readFromURL(webSiteURL));

        if(!response.isPresent()){
            return;
        }
        System.out.println(response.get());



    }

    private StringBuilder readFromURL(String webSiteURL){
        Optional<BufferedReader> reader= Optional.ofNullable(connectWithURL(webSiteURL));
        if (!reader.isPresent()) {
            return null;
        }

        String inputLine;
        StringBuilder response = new StringBuilder();

        try {
            while ((inputLine = reader.get().readLine()) != null) {
                response.append(inputLine);
            }

            return response;
        }
        catch (IOException e) {
            System.out.println("Nie udalo sie odczytac danych z linku");
        }finally{
            try {
                reader.get().close();
            } catch (IOException e) {
                System.out.println("Nie udalo sie zamknac polaczenia");
            }
        }
        return null;
    }

    private BufferedReader connectWithURL(String webSiteURL){
        try {
            URL url = new URL(webSiteURL);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            return new BufferedReader(new InputStreamReader(con.getInputStream()));
        } catch (MalformedURLException e) {
           // e.printStackTrace();
            System.out.println("Nie udalo sie stworzyc obiektu URL");
        } catch (IOException e) {
            //e.printStackTrace();
            System.out.println("Nie udalo sie nawiazac polaczenia");
        }
        return null;


    }
}
