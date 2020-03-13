package tests;

import JSONhandlers.JSONAnalyzer;
import JSONhandlers.JSONReader;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class JSONAnalyzerCreateStringAttributeTests implements IURLForTests{

    private JSONReader reader=null;
    private String URL;
    private String attribute;
    private String expected;

    @Before
    public void setUP(){
        reader=new JSONReader();
    }

    public JSONAnalyzerCreateStringAttributeTests(String URL, String attribute, String expectedString){
        this.URL=URL;
        this.attribute=attribute;
        this.expected=expectedString;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> data(){
        return Arrays.asList(new Object[][]{
                {commentsURL+"/10", "name", "eaque et deleniti atque tenetur ut quo ut"},
                {commentsURL+"/10", "email", "Carmen_Keeling@caroline.name"},
                {commentsURL+"/10", "body", "voluptate iusto quis nobis reprehenderit ipsum amet nulla\\nquia quas dolores velit et non\\naut quia necessitatibus\\nnostrum quaerat nulla et accusamus nisi facilis"},
                {commentsURL+"/100", "name", "et sint quia dolor et est ea nulla cum"},
                {commentsURL+"/100", "email", "Leone_Fay@orrin.com"},
                {commentsURL+"/100", "body", "architecto dolorem ab explicabo et provident et\\net eos illo omnis mollitia ex aliquam\\natque ut ipsum nulla nihil\\nquis voluptas aut debitis facilis"},
                {commentsURL+"/200", "name", "aperiam et omnis totam"},
                {commentsURL+"/200", "email", "Amir@kaitlyn.org"},
                {commentsURL+"/200", "body", "facere maxime alias aspernatur ab quibusdam necessitatibus\\nratione similique error enim\\nsed minus et\\net provident minima voluptatibus"},

                {albumsURL+"/1", "title", "quidem molestiae enim"},
                {albumsURL+"/50", "title", "sed qui sed quas sit ducimus dolor"},
                {albumsURL+"/100", "title", "enim repellat iste"},

                {usersURL+"/1", "name", "Leanne Graham"},
                {usersURL+"/1", "username", "Bret"},
                {usersURL+"/1", "email", "Sincere@april.biz"},
                {usersURL+"/1", "street", "Kulas Light"},
                {usersURL+"/1", "suite", "Apt. 556"},
                {usersURL+"/1", "city", "Gwenborough"},
                {usersURL+"/1", "zipcode", "92998-3874"},
                {usersURL+"/1", "phone", "1-770-736-8031 x56442"},
                {usersURL+"/1", "website", "hildegard.org"},
                {usersURL+"/1", "company:name", "Romaguera-Crona"},
                {usersURL+"/1", "company:catchPhrase", "Multi-layered client-server neural-net"},
                {usersURL+"/1", "company:bs", "harness real-time e-markets"},

                {usersURL+"/5", "name", "Chelsey Dietrich"},
                {usersURL+"/5", "street", "Skiles Walks"},
                {usersURL+"/5", "city", "Roscoeview"},
                {usersURL+"/5", "company:name", "Keebler LLC"},
                {usersURL+"/5", "company:bs", "revolutionize end-to-end systems"},
                {usersURL+"/10", "username", "Moriah.Stanton"},
                {usersURL+"/10", "suite", "Suite 198"},
                {usersURL+"/10", "catchPhrase", "Centralized empowering task-force"},
                {usersURL+"/10", "company:bs", "target end-to-end models"},

                {postsURL+"/1", "title", "sunt aut facere repellat provident occaecati excepturi optio reprehenderit"},
                {postsURL+"/1", "body", "quia et suscipit\\nsuscipit recusandae consequuntur expedita et cum\\nreprehenderit molestiae ut ut quas totam\\nnostrum rerum est autem sunt rem eveniet architecto"},
                {postsURL+"/50", "title", "repellendus qui recusandae incidunt voluptates tenetur qui omnis exercitationem"},
                {postsURL+"/50", "body", "error suscipit maxime adipisci consequuntur recusandae\\nvoluptas eligendi et est et voluptates\\nquia distinctio ab amet quaerat molestiae et vitae\\nadipisci impedit sequi nesciunt quis consectetur"},
                {postsURL+"//100", "title", "at nam consequatur ea labore ea harum"},
                {postsURL+"//100", "body", "cupiditate quo est a modi nesciunt soluta\\nipsa voluptas error itaque dicta in\\nautem qui minus magnam et distinctio eum\\naccusamus ratione error aut"},
        });

    }

    @Test
    public void getStringAttributeFromJSONUsingCreateStringAttributeFunction(){
        StringBuilder result=reader.readFromURL(URL);
        Assert.assertEquals(expected, JSONAnalyzer.createStringAttribute(attribute,result));
    }
}
