import java.io.*;
import java.nio.charset.StandardCharsets;

public class SentenceFinder {
    /**
     * @param file        from which we get the string
     * @param subSentence what should we find
     * @return a string that is empty if we don't have repetitions or the start index
     * @throws IOException working out exceptions
     */
    public String findSentence(String file, String subSentence) throws IOException {
        if (subSentence == "") {
            return "";
        }
        int i = -1;
        int maxBuffer = 100000;
        int cntBuffer = 0;
        char[] buffer = new char[maxBuffer];
        String answer = "";

        FileReader reader = new FileReader(file);
        int c = 0;
        char temp;
        while (c != -1) {
            i++;
            if (cntBuffer == 0) {
                c = reader.read();
                temp = (char) c;
            } else {
                temp = buffer[cntBuffer--];
            }
            if (temp == subSentence.charAt(0)) {
                int numEq = 1;
                int flag = 0;
                while (((c = reader.read()) != -1) && (flag == 0)) {
                    if (numEq == subSentence.length()) {
                        return i + " ";
                    }
                    temp = (char) c;
                    buffer[cntBuffer++] = temp;
                    if (temp == subSentence.charAt(numEq)) {
                        numEq++;
                    } else {
                        flag = 1;
                        numEq = 0;
                    }
                }
            }
        }
/*
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
*/
        reader.close();
        return answer;
    }
}
