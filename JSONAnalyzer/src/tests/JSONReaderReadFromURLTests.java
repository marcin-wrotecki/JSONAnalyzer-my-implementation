package tests;

import JSONhandlers.JSONReader;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;


@RunWith(Parameterized.class)
public class JSONReaderReadFromURLTests implements IURLForTests {

    private JSONReader reader=null;
    @Parameterized.Parameter()
    public String URL;
    @Parameterized.Parameter(1)
    public boolean expected;

    @Before
    public void setUP(){ reader=new JSONReader();}



    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {usersURL,true},
                {postsURL,true},
                {albumsURL,true},
                {photosURL,true},
                {todosURL,true},
                {commentsURL,true},
                {"www.nonexistingwebsite.com",false},

        });
    }

    @Test
    public void checkIfReadFromURLGetResponse(){
        StringBuilder response=reader.readFromURL(URL);
        Assert.assertEquals(expected, response!=null);

    }

}
