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
public class JSONAnalyzerCreateDoubleAttributeTests implements IURLForTests{
    private JSONReader reader=null;

    private String URL;
    private String attribute;
    private double expected;
    private final double delta=0.01;

    @Before
    public void setUP(){
        reader=new JSONReader();
    }

    public JSONAnalyzerCreateDoubleAttributeTests(String URL, String attribute, double expected){
        this.URL=URL;
        this.attribute=attribute;
        this.expected=expected;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {usersURL+"/1","address:geo:lat",-37.3159},
                {usersURL+"/3","address:geo:lat",-68.6102},
                {usersURL+"/5","address:geo:lat",-31.8129},
                {usersURL+"/7","address:geo:lat",24.8918},
                {usersURL+"/9","address:geo:lat",24.6463},

                {usersURL+"/2","address:geo:lng",-34.4618},
                {usersURL+"/4","address:geo:lng",-164.2990},
                {usersURL+"/6","address:geo:lng",71.7478},
                {usersURL+"/8","address:geo:lng",-120.7677},
                {usersURL+"/10","address:geo:lng",57.2232},


        });
    }

    @Test
    public void getDoubleAttributeFromJSONUsingCreateDoubleAttributeFunction(){
        StringBuilder result=reader.readFromURL(URL);
        Assert.assertEquals(expected, JSONAnalyzer.createDoubleAttribute(attribute,result),delta);
    }
}
