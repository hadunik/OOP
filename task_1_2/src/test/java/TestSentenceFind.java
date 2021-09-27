import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;


public class TestSentenceFind {
    @Test
    public void simpleTestOnWord() throws IOException {
        SentenceFinder sf = new SentenceFinder();
        String fileName = "input.txt";
        String sentence = "aabaab";
        String ans = sf.findSentence(fileName, sentence);
        Assertions.assertEquals("0 10 13 16 ", ans);
    }

    @Test
    public void simpleTestOnLetter() throws IOException {
        SentenceFinder sf = new SentenceFinder();
        String fileName = "input.txt";
        String sentence = "a";
        String ans = sf.findSentence(fileName, sentence);
        Assertions.assertEquals("0 1 3 4 6 7 8 9 10 11 13 14 16 17 19 20 23 24 25 ", ans);
    }

    @Test
    public void testNoMatches() throws IOException {
        SentenceFinder sf = new SentenceFinder();
        String fileName = "input.txt";
        String sentence = "no";
        String ans = sf.findSentence(fileName, sentence);
        Assertions.assertEquals("", ans);
    }

    @Test
    public void testOnEmpty() throws IOException {
        SentenceFinder sf = new SentenceFinder();
        String fileName = "input.txt";
        String sentence = "";
        String ans = sf.findSentence(fileName, sentence);
        Assertions.assertEquals("", ans);
    }
}
