import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;


public class TestSentenceFind {

    @Test
    public void testNoMatches() throws IOException {
        SentenceFinder sf = new SentenceFinder();
        String fileName = "input.txt";
        String sentence = "no";
        Integer[] ans = sf.findSentence(fileName, sentence);
        assert (ans == null);
    }

    @Test
    public void testOnWrongFile() throws IOException {
        SentenceFinder sf = new SentenceFinder();
        String fileName = "input3.txt";
        String sentence = "aux";
        Throwable thrown = assertThrows(IOException.class, () -> {
            Integer[] ans = sf.findSentence(fileName, sentence);
        });
        String exc = "file not found";
        assertEquals( exc, thrown.getMessage());
    }

    @Test
    public void testOnWrongFile2() throws IOException {
        SentenceFinder sf = new SentenceFinder();
        String fileName = null;
        String sentence = "aux";
        Throwable thrown = assertThrows(IOException.class, () -> {
            Integer[] ans = sf.findSentence(fileName, sentence);
        });
        String exc = "path to file is null";
        assertEquals( exc, thrown.getMessage());
    }

    @Test
    public void testOnEmpty() throws IOException {
        SentenceFinder sf = new SentenceFinder();
        String fileName = "input.txt";
        String sentence = "";
        Throwable thrown = assertThrows(IOException.class, () -> {
            Integer[] ans = sf.findSentence(fileName, sentence);
        });
        String exc = "substring is null";
        assertEquals( exc, thrown.getMessage());
    }

    @Test
    public void testOnEmpty2() throws IOException {
        SentenceFinder sf = new SentenceFinder();
        String fileName = "input.txt";
        String sentence = null;
        Throwable thrown = assertThrows(IOException.class, () -> {
            Integer[] ans = sf.findSentence(fileName, sentence);
        });
        String exc = "substring is null";
        assertEquals( exc, thrown.getMessage());
    }


    @Test
    public void testOnBigFile() throws IOException {
        SentenceFinder sf = new SentenceFinder();
        String fileName = "input2.txt";
        String sentence = "aux";
        Integer[] ans = sf.findSentence(fileName, sentence);
        Integer[] exp = new Integer[] {3795, 6415, 35594, 217581, 226446, 230083, 230310, 385491, 400864, 432865, 546268, 728513};
        assertArrayEquals(ans, exp);
    }
}

