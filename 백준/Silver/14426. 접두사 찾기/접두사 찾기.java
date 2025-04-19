import java.io.*;
import java.util.*;

public class Main {

    private static int n;
    private static int m;
    private static Trie trie;
    private static List<String> inputs;

    public static void main(String[] args) throws IOException {
        init();
        findAnswer();
    }

    private static void init() throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer = new StringTokenizer(input.readLine());
        n = Integer.parseInt(tokenizer.nextToken());
        m = Integer.parseInt(tokenizer.nextToken());
        trie = new Trie();
        for (int i = 0; i < n; i++) {
            trie.insert(input.readLine());
        }
        inputs = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            inputs.add(input.readLine());
        }
    }

    private static void findAnswer() {
        int count = 0;
        for(String word : inputs) {
            if (trie.isPrefix(word)) {
                count++;
            }
        }

        System.out.println(count);
    }
}

class Trie {
    Node head = new Node();

    public void insert(String word) {
        Node root = head;
        for (char c : word.toCharArray()) {
            root = root.children.computeIfAbsent(c, k -> new Node());
        }
        root.isEndOfWord = true;
    }

    public boolean isPrefix(String word) {
        Node root = head;
        for (char c : word.toCharArray()) {
            root = root.children.getOrDefault(c, null);
            if (root == null) {
                return false;
            }
        }

        return true;
    }
}

class Node {
    Map<Character, Node> children = new HashMap<>();
    boolean isEndOfWord = false;
}
