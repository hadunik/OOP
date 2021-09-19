import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;

public class TestSentenceFind {
    @Test
    public void simpleTestOnWord() throws IOException{
        SentenceFinder sf = new SentenceFinder();
        String fileName = "input.txt";
        String sentence = "want";
        String ans = sf.findSentence(fileName, sentence);
        Assertions.assertEquals("8 ", ans);
    }

    @Test
    public void simpleTestOnLetter() throws IOException{
        SentenceFinder sf = new SentenceFinder();
        String fileName = "input.txt";
        String sentence = "w";
        String ans = sf.findSentence(fileName, sentence);
        Assertions.assertEquals("8 ", ans);
    }

    @Test
    public void testNoMatches() throws IOException{
        SentenceFinder sf = new SentenceFinder();
        String fileName = "input.txt";
        String sentence = "no";
        String ans = sf.findSentence(fileName, sentence);
        Assertions.assertEquals("", ans);
    }

    @Test
    public void testOnEmpty() throws IOException{
        SentenceFinder sf = new SentenceFinder();
        String fileName = "input.txt";
        String sentence = "";
        String ans = sf.findSentence(fileName, sentence);
        Assertions.assertEquals("", ans);
    }
}
