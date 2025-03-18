import java.util.*;

class Solution {
    
    // 문자열 길이별로 관리하는 트리가 필요
    // 정방향 역방향 둘다 필요
    private Map<Integer, Trie> tries = new HashMap<>();
    private Map<Integer, Trie> reversedTries = new HashMap<>();
    
    public int[] solution(String[] words, String[] queries) {
        for (String word : words) {
            int len = word.length();
            // 맵에서 없으면 만들어서 트라이에 insert, 역으로도 insert
            tries.putIfAbsent(len, new Trie());
            reversedTries.putIfAbsent(len, new Trie());
            Trie normal = tries.get(len);
            Trie reverse = reversedTries.get(len);
            normal.insert(word);
            reverse.insert(new StringBuilder(word).reverse().toString());
        }
        // 쿼리를 순회하면서 길이에 따라 trie를 가져오고 와일드카드?의 위치를 보고
        // 역방향 정방향 정해서 해당하는 글자의 개수 가져오기
        int[] answer = new int[queries.length];
        int idx = 0;
        for (String word : queries) {
            int count = 0;
            Trie normal, reverse;
            
            // 모두 와일드 카드인 경우
            if (word.charAt(0) == '?' && word.charAt(word.length() - 1) == '?') {
                normal = tries.get(word.length());
                answer[idx++] = (normal != null) ? normal.head.count : 0;
                continue;
            }

            
            if (word.startsWith("?")) {
                reverse = reversedTries.get(word.length());
                if (reverse != null) {
                    count = reverse.searchCount(new StringBuilder(word).reverse().toString());
                }
            } else {
                normal = tries.get(word.length());
                if (normal != null) {
                    count = normal.searchCount(word);
                }
            }
            answer[idx++] = count;
        }
        
        return answer;
    }
}

class Node {
    Map<Character, Node> children;
    boolean isEndOfWord;
    int count;
    
    public Node() {
        children = new HashMap<>();
        isEndOfWord = false;
        count = 0;
    }
}

class Trie {
    Node head = new Node();
    
    // insert할때, 뒤에 이어지는 단어 카운트 기록
    public void insert(String word) {
        Node node = head;
        head.count++;
        for (char c : word.toCharArray()) {
            node.children.putIfAbsent(c, new Node());
            node = node.children.get(c);
            node.count++;
        }
        node.isEndOfWord = true;
    }
    // search가 아니라 와일드 카드가 오면 뒤로 올 단어의 개수를 만들어줘야 함
    public int searchCount(String word) {
        Node node = head;

        for (char c : word.toCharArray()) {
            if (c == '?') {
                return node.count;
            }
            if (!node.children.containsKey(c)) {
                return 0;
            }
            node = node.children.get(c);
        }
        return node.count;
    }
}