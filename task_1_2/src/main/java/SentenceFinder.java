import java.io.*;
import java.nio.charset.StandardCharsets;


public class SentenceFinder {
    /**
     * @param file from which we get the string
     * @param subSentence what should we find
     * @return a string that is empty if we don't have repetitions or the start index
     * @throws IOException working out exceptions
     */
    public String findSentence(String file, String subSentence) throws IOException {
        if(subSentence == ""){
            return "";
        }
        int maxBuffer = 100000;
        char[] buffer = new char[maxBuffer];
        String answer = "";

        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file), StandardCharsets.UTF_8), maxBuffer);
        int lengthBuffer = reader.read(buffer);

        for (int i = 0; i < lengthBuffer; i++) {
            if (buffer[i] == subSentence.charAt(0)) {
                int numberOfEquals = 0;
                for (int j = 0; j < subSentence.length(); j++) {
                    if (buffer[i + j] == subSentence.charAt(j)) {
                        numberOfEquals++;
                    }
                }
                if(numberOfEquals == subSentence.length()){
                    return i + " ";
                }
            }
        }

        reader.close();
        return answer;
    }
}
