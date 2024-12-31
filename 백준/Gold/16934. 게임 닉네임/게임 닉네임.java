import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    private static Trie trie;
    private static int n;
    private static Map<String, Integer> map;

    public static void main(String[] args) throws IOException {
        init();
    }

    private static void init() throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder answer = new StringBuilder();
        n = Integer.parseInt(input.readLine());
        trie = new Trie();
        map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            String word = input.readLine();
            map.put(word, map.getOrDefault(word, 0) + 1);
            answer.append(trie.insert(word)).append("\n");
        }

        System.out.println(answer);
    }

    static class Node {
        Map<Character, Node> children = new HashMap<>();
        boolean endOfWord;
        boolean isDuplicated;

        public Node() {}
    }

    static class Trie {
        Node root = new Node();

        public String insert(String word) {
            Node node = this.root;
            for (int i = 0; i < word.length(); i++) {
                if (node.children.containsKey(word.charAt(i))) {
                    node.children.get(word.charAt(i)).isDuplicated = true;
                }
                node = node.children.computeIfAbsent(word.charAt(i), c -> new Node());

            }
            node.endOfWord = true;
            String nickname = "";
            int idx = 0;
            node = this.root;
            while (idx < word.length()) {
                char c = word.charAt(idx++);
                node = node.children.get(c);
                nickname = nickname + c;
                if (!node.isDuplicated) {
                    break;
                }
            }
            if (map.containsKey(word) && map.get(word) > 1) {
                nickname += map.get(word);
            }
            return nickname;
        }
    }
}