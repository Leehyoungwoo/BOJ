import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int total = 0;
        Map<String, Integer> map = new HashMap<>();

        while (input.hasNextLine()) {
            String tree = input.nextLine().trim();
            if (tree.isEmpty()) break;
            total++;
            map.put(tree, map.getOrDefault(tree, 0) + 1);
        }

        List<String> name = new ArrayList<>(map.keySet());
        Collections.sort(name);

        StringBuilder answer = new StringBuilder();
        for (String tree : name) {
            int count = map.get(tree);
            double percent = (double) count * 100 / total;
            percent = Math.round(percent * 10000.0) / 10000.0; 
            answer.append(tree).append(" ").append(String.format("%.4f", percent)).append("\n");
        }

        System.out.print(answer);
    }
}