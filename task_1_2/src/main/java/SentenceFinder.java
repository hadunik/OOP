import java.io.*;
import java.util.Objects;

public class SentenceFinder {

    /**
     * @param str subSentence(pattern)
     * @return pi-function return the array with max prefix of each symbol
     */
    public int[] piFunc(String str) {

        int n = str.length();
        int[] pi = new int[n];
        pi[0] = 0;

        for (int i = 1; i < n; ++i) {
            int maxSufPref = pi[i - 1];
            while (maxSufPref > 0 && str.charAt(i) != str.charAt(maxSufPref))
                maxSufPref = pi[maxSufPref - 1];
            if (str.charAt(maxSufPref) == str.charAt(i))
                ++maxSufPref;
            pi[i] = maxSufPref;
        }
        return pi;
    }

    /**
     * @param file name of file were we search the string
     * @param str  string which we find in file (pattern)
     * @return number of each pattern in file
     */
    public String findSentence(String file, String str) throws IOException {
        if (Objects.equals(str, "")) return "";

        String ans = "";
        int[] p = piFunc(str);
        int m = str.length();

        BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
        int cntOfEq = 0;
        int sym = bufferedReader.read();
        for (int i = 0; sym != -1; i++) {
            char c = (char) sym;
            sym = bufferedReader.read();
            while (cntOfEq > 0 && c != str.charAt(cntOfEq))
                cntOfEq = p[cntOfEq - 1];
            if (c == str.charAt(cntOfEq))
                cntOfEq++;
            if (cntOfEq == m) {
                ans += (i - cntOfEq + 1) + (" ");
                cntOfEq = p[cntOfEq - 1];

            }
        }
        return ans;
    }
}








