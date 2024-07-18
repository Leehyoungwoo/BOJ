import java.io.*;
import java.util.*;
class TreeNode {
    int val;
    TreeNode left, right;

    TreeNode(int val) {
        this.val = val;
    }
}

public class Main {
    static List<Integer> preorderList = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line;
        while ((line = br.readLine()) != null && !line.isEmpty()) {
            preorderList.add(Integer.parseInt(line));
        }
        constructAndPrintPostorder(0, preorderList.size() - 1);
    }

    private static void constructAndPrintPostorder(int start, int end) {
        if (start > end) {
            return;
        }
        int nodeValue = preorderList.get(start);
        int splitIndex = start + 1;
        while (splitIndex <= end && preorderList.get(splitIndex) < nodeValue) {
            splitIndex++;
        }
        constructAndPrintPostorder(start + 1, splitIndex - 1);
        constructAndPrintPostorder(splitIndex, end); 
        System.out.println(nodeValue);
    }
}