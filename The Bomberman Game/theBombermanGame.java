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
     * Complete the 'bomberMan' function below.
     *
     * The function is expected to return a STRING_ARRAY.
     * The function accepts following parameters:
     *  1. INTEGER n
     *  2. STRING_ARRAY grid
     */

    public static List<String> bomberMan(int n, List<String> grid) {
    // Write your code here
        if (n == 0 || n == 1) return grid;
        
        List<List<Character>> chars = convertToDoubleList(grid);
        
        //s = 2 plant
        plantBombs(chars, 'X');
        //printMatrix(chars);
        if (n == 2) return convertDoubleListToSingleList(chars);
        
        //now fill from s = 3 to s = 6
        List<List<String>> answers = new ArrayList<>();
        //s = 3
        explode(chars, 'O');
        answers.add(convertDoubleListToSingleList(chars));
        if (n == 3) return answers.get(0);
        
        //s = 4
        plantBombs(chars, 'O');
        answers.add(convertDoubleListToSingleList(chars));
        if (n == 4) return answers.get(1);
        
        //s = 5
        explode(chars, 'X');
         answers.add(convertDoubleListToSingleList(chars));
        if (n == 5) return answers.get(2);
        
        // s = 6
        plantBombs(chars, 'X');
        answers.add(convertDoubleListToSingleList(chars));
        if (n == 6) return answers.get(3);
        
        int index = (n - 3)%4;
        return answers.get(index);
    }
    
    public static List<List<Character>> convertToDoubleList(List<String> grid) {
        List<List<Character>> newList = new ArrayList<>();
        for (int i = 0; i < grid.size(); i++) {
            String s = grid.get(i);
            ArrayList<Character> chars = new ArrayList<>();
            for (int j = 0; j < s.length(); j++) {
                chars.add(s.charAt(j));
            }
            newList.add(chars);
        }
        return newList;
    }
    
    public static List<String> convertDoubleListToSingleList(List<List<Character>> chars) {
        List<String> convertedList = new ArrayList<>();
        for (int i = 0; i < chars.size(); i++) {
            String s = "";
            for (int j = 0; j < chars.get(0).size(); j++) {
                s += chars.get(i).get(j) == 'X' ? 'O' : chars.get(i).get(j);
            }
            convertedList.add(s);
        }
        return convertedList;
    }
    
    public static void plantBombs(List<List<Character>> chars, char bomb) {
        for (int i = 0; i < chars.size(); i++) {
            for (int j = 0; j < chars.get(0).size(); j++) {
                char c = chars.get(i).get(j);
                if (c == '.') {
                    chars.get(i).set(j, bomb);
                }
            }
        }
    }
    
      public static void explode(List<List<Character>> chars, char bomb) {
        int rows = chars.size();
        int cols = chars.get(0).size();
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                char c = chars.get(i).get(j);
                if ( c == bomb ) {
                    if (j > 0 && chars.get(i).get(j-1) != bomb) { //check left
                        chars.get(i).set(j-1, '.');
                    }
                    if (j < cols - 1 && chars.get(i).get(j+1) != bomb) { //check right
                        chars.get(i).set(j+1, '.');
                    }
                    chars.get(i).set(j, '.'); //me
                    
                    if (i > 0 && chars.get(i-1).get(j) != bomb) { //check up
                        chars.get(i-1).set(j, '.');
                    }
                    if (i < rows - 1 && chars.get(i+1).get(j) != bomb) { //check down
                        chars.get(i+1).set(j, '.');
                    }
                }
            }
        }
    }
    
    public static void printMatrix(List<List<Character>> chars) {
        for (int i = 0; i < chars.size(); i++) {
            for (int j = 0; j < chars.get(0).size(); j++) {
                char c = chars.get(i).get(j);
                System.out.print(String.valueOf(c) + " ");
            }
            System.out.println("");
        }
    }

}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String[] firstMultipleInput = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

        int r = Integer.parseInt(firstMultipleInput[0]);

        int c = Integer.parseInt(firstMultipleInput[1]);

        int n = Integer.parseInt(firstMultipleInput[2]);

        List<String> grid = IntStream.range(0, r).mapToObj(i -> {
            try {
                return bufferedReader.readLine();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        })
            .collect(toList());

        List<String> result = Result.bomberMan(n, grid);

        bufferedWriter.write(
            result.stream()
                .collect(joining("\n"))
            + "\n"
        );

        bufferedReader.close();
        bufferedWriter.close();
    }
}
