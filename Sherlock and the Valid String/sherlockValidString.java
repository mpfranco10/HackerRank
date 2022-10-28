import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.function.*;
import java.util.regex.*;
import java.util.stream.*;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

class Result {

    /*
     * Complete the 'isValid' function below.
     *
     * The function is expected to return a STRING.
     * The function accepts STRING s as parameter.
     */

    public static String isValid(String s) {
    // Write your code here
        HashMap<Character, Integer> charCount = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            char  c = s.charAt(i);
            charCount.put(c, charCount.getOrDefault(c, 0) + 1);
        }
        
        HashMap<Integer, Integer> frecuencyCount = new HashMap<>();
        for (Integer nInteger: charCount.values()) {
            frecuencyCount.put(nInteger, frecuencyCount.getOrDefault(nInteger, 0) + 1);
        }
        
        int n = frecuencyCount.keySet().size();
        System.out.println(n);
        if (n == 1) return "YES";
        else if (n == 2) {
            int minKey = Integer.MAX_VALUE;
            int maxKey = 0;
            for (int i: frecuencyCount.keySet()) {
                minKey = Math.min(minKey, i);
                maxKey = Math.max(maxKey, i);
            }
            if (minKey == 1 && frecuencyCount.get(minKey) == 1) return "YES";
            else if (maxKey == minKey + 1 && frecuencyCount.get(maxKey) == 1) {
                return "YES";
            } else {
                return "NO";
            }
        } else {
            return "NO";
        }
    }

}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String s = bufferedReader.readLine();

        String result = Result.isValid(s);

        bufferedWriter.write(result);
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}
