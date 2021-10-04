import java.io.*;
import java.util.ArrayList;

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
     * @param filename name of file were we search the string
     * @param pattern  string which we find in file
     * @return number of each pattern in file
     */
    public Integer[] findSentence(String filename, String pattern) throws IOException {
        if (pattern == null || pattern.equals("")) {
            throw new IOException("substring is null");
        }

        if (filename == null) {
            throw new IOException("path to file is null");
        }
        if (pattern.isEmpty()) {
            return null;
        }

        BufferedReader bufferedReader;
        try {
            bufferedReader = new BufferedReader(new FileReader(filename));
        } catch (IOException e) {
            throw new IOException("file not found");
        }


        int[] p = piFunc(pattern);
        int m = pattern.length();
        ArrayList<Integer> ans = new ArrayList<Integer>();

        int indicator = 0;
        int sym = bufferedReader.read();
        for (int i = 0; sym != -1; i++) {
            char c = (char) sym;
            sym = bufferedReader.read();
            while (indicator > 0 && c != pattern.charAt(indicator))
                indicator = p[indicator - 1];
            if (c == pattern.charAt(indicator))
                indicator++;
            if (indicator == m) {
                ans.add(i - indicator + 1);
                indicator = p[indicator - 1];

            }
        }
        Integer[] arr = {-1};
        arr = (ans.toArray(arr));
        return arr[0] == null ? null : arr;
    }
}








