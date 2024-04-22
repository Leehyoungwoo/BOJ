import java.util.*;

class Solution {
    public int solution(int[] nums) {
        int answer = 0;
        Map<Integer, Integer> map = new HashMap<>();
        for(int i = 0; i < nums.length; i++) {
            map.put(nums[i], map.getOrDefault(nums[i], 0));
        }
        if (nums.length / 2 <= map.size()) {
            answer = nums.length / 2;
            System.out.println("answer : " + answer);
            return answer;
        }
        System.out.println("answer : " + map.size());
        return map.size();
    }
}