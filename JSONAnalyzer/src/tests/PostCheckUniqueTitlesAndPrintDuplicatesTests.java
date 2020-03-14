package tests;

import JSONhandlers.JSONAnalyzer;
import JSONhandlers.JSONReader;
import Models.Post;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Optional;

public class PostCheckUniqueTitlesAndPrintDuplicatesTests implements IURLForTests{
    private JSONReader reader;
    private static Post[] posts;
    private ByteArrayOutputStream outContent;

    @Before
    public void setUP(){
        outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        reader=new JSONReader();
        Optional<StringBuilder> response = Optional.ofNullable(reader.readFromURL(postsURL));
        if (!response.isPresent()) {
            System.out.println("Nie udalo sie pobrac danych z linku: " + postsURL);
            return;
        }
        StringBuilder[] formattedPosts = JSONAnalyzer.formatPostsReponse(response.get());
        posts = Post.createPostsArray(formattedPosts);
    }


    @Test
    public void returnTrueWhenPostsAreUniqueAndDontPrintAnything(){
        Assert.assertEquals(true, Post.checkUniqueTitlesAndPrintDuplicates(posts));
        Assert.assertEquals("",outContent.toString());
    }

    @Test
    public void returnTrueWhenTitlesAreUniqueAndDontPrintAnything(){
        String title=posts[20].getTitle();
        posts[20]=posts[1].clone();
        posts[20].setTitle(title);
        Assert.assertEquals(true, Post.checkUniqueTitlesAndPrintDuplicates(posts));
        Assert.assertEquals("",outContent.toString());
    }

    @Test
    public void returnFalseAndPrintTitleWhenHasOneDuplication(){
        posts[3].setTitle(posts[60].getTitle());
        Assert.assertEquals(false, Post.checkUniqueTitlesAndPrintDuplicates(posts));
        Assert.assertEquals(posts[60].getTitle().trim(),outContent.toString().trim());
    }

    @Test
    public void returnFalseAndPrintTitlesWhenHasMoreDuplications(){
        posts[3].setTitle(posts[40].getTitle());
        posts[7].setTitle(posts[20].getTitle());
        posts[21].setTitle(posts[1].getTitle());
        posts[80].setTitle(posts[73].getTitle());
        posts[99].setTitle(posts[12].getTitle());
        Assert.assertEquals(false, Post.checkUniqueTitlesAndPrintDuplicates(posts));
        Assert.assertEquals((posts[7].getTitle()+'\n'+posts[1].getTitle()+'\n'+posts[3].getTitle()+'\n'+posts[73].getTitle()+'\n'+posts[12].getTitle()).replaceAll("\n", "").replaceAll("\r", ""),outContent.toString().replaceAll("\n", "").replaceAll("\r", ""));
    }


}
