import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    private static int t;
    private static int n;
    private static List<String> list;
    private static Trie trie;

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        t = Integer.parseInt(input.readLine());
        StringBuilder answer = new StringBuilder();
        for (int i = 0; i < t; i++) {
            init(input);
            answer.append(findAnswer()).append("\n");
        }
        System.out.println(answer);
    }

    private static String findAnswer() {
        for (int i = 0; i < list.size(); i++) {
            trie.insert(list.get(i));
        }

        for (int i = 0; i < list.size(); i++) {
            if (!trie.search(list.get(i))) {
                return "NO";
            }
        }

        return "YES";
    }

    private static void init(BufferedReader input) throws IOException {
        n = Integer.parseInt(input.readLine());
        list = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            list.add(input.readLine());
        }
        trie = new Trie();
    }

    static class Node {
        Map<Character, Node> children = new HashMap<>();
        boolean endOfWord;
        public Node() {

        }
    }

    static class Trie {
        Node root = new Node();

        public Trie() {}

        public void insert(String word) {
            Node node = this.root;

            for (int i = 0; i < word.length(); i++) {
                node = node.children.computeIfAbsent(word.charAt(i), c -> new Node());
            }
            node.endOfWord = true;
        }

        public boolean search(String word) {
            Node node = this.root;
            for (int i = 0; i < word.length(); i++) {
                node = node.children.getOrDefault(word.charAt(i), null);
                if (node == null) {
                    return false;
                }
                if (i != word.length() - 1 && node.endOfWord) {
                    return false;
                }
            }

            return true;
        }
    }
}