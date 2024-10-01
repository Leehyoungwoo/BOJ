import java.util.*;

class Solution {
    static Map<String, List<Integer>> map = new HashMap<>();

    public static int[] solution(String[] info, String[] query) {
        int[] answer = new int[query.length];

        // Step 1: Process info to generate combinations
        for (String in : info) {
            String[] s = in.split(" ");
            // Generate all possible combinations
            generateCombinations(s, "", 0);
        }

        // Sort scores in each list
        for (List<Integer> scores : map.values()) {
            Collections.sort(scores);
        }

        // Step 3: Process query
        for (int q = 0; q < query.length; q++) {
            query[q] = query[q].replaceAll(" and ", "");
            String[] qParts = query[q].split(" ");
            String key = qParts[0]; // Combined key (language, position, career, soul food)
            int scoreThreshold = Integer.parseInt(qParts[1]);

            answer[q] = map.containsKey(key) ? binarySearch(map.get(key), scoreThreshold) : 0;
        }

        return answer;
    }

    // Generate combinations
    private static void generateCombinations(String[] arr, String str, int idx) {
        if (idx == 4) {
            map.putIfAbsent(str, new ArrayList<>());
            map.get(str).add(Integer.parseInt(arr[4]));
            return;
        }

        // Include the current attribute
        generateCombinations(arr, str + arr[idx], idx + 1);
        // Exclude the current attribute
        generateCombinations(arr, str + "-", idx + 1);
    }

    // Binary search for counting scores >= scoreThreshold
    private static int binarySearch(List<Integer> scores, int threshold) {
        int start = 0, end = scores.size() - 1;

        while (start <= end) {
            int mid = (start + end) / 2;
            if (scores.get(mid) < threshold) {
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }

        return scores.size() - start; // Count of elements >= threshold
    }
}
