import java.util.*;

class Solution {
    public int solution(int cacheSize, String[] cities) {
        Deque<String> cache = new LinkedList<>();
        int answer = 0;
        for(String city : cities) {
            String lowCity = city.toLowerCase();
            if(cache.size() == cacheSize) {
                if (cache.contains(lowCity)) {
                    answer+=1; 
                    cache.remove(lowCity);
                    cache.addLast(lowCity);
                } else {
                    answer+=5;
                    cache.addLast(lowCity);
                    cache.removeFirst();
                }
                
            } else {
                if (cache.contains(lowCity)) {
                   answer+=1; 
                } else {
                    answer+=5;
                }

                cache.addLast(lowCity);
            }
        }
        return answer;
    }
}