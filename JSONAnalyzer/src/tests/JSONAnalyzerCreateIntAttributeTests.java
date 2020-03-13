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
public class JSONAnalyzerCreateIntAttributeTests implements IURLForTests{


    private JSONReader reader=null;

    private String URL;
    private String attribute;
    private int expected;

    @Before
    public void setUP(){
        reader=new JSONReader();
    }

    public JSONAnalyzerCreateIntAttributeTests(String URL, String attribute, int expected){
        this.URL=URL;
        this.attribute=attribute;
        this.expected=expected;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> data(){
        return Arrays.asList(new Object[][]{
                {commentsURL+"/10","postID",2},
                {commentsURL+"/10","id",10},
                {commentsURL+"/100","postID",20},
                {commentsURL+"/100","id",100},
                {commentsURL+"/200","postID",40},
                {commentsURL+"/200","id",200},

                {albumsURL+"/1","userId",1 },
                {albumsURL+"/1","id",1},
                {albumsURL+"/50","userId",5},
                {albumsURL+"/50","id",50},
                {albumsURL+"/100","userId",10},
                {albumsURL+"/100","id",100},

                {usersURL+"/1","id",1},
                {usersURL+"/5","id",5},
                {usersURL+"/10","id",10},

                {postsURL+"/1","userId",1 },
                {postsURL+"/1","id",1},
                {postsURL+"/50","userId",5},
                {postsURL+"/50","id",50},
                {postsURL+"/100","userId",10},
                {postsURL+"/100","id",100},

        });
    }

   /* @Test
    public void whenURLisWrongThenReturnNull(){
        StringBuilder result = reader.readFromURL(URL);
        Assert.assertEquals(expected,result);
    }*/

    @Test
    public void getIntegerAttributeFromJSONUsingCreateIntAttributeFunction(){
        StringBuilder result=reader.readFromURL(URL);
        Assert.assertEquals(expected, JSONAnalyzer.createIntAttribute(attribute,result));
    }
}
