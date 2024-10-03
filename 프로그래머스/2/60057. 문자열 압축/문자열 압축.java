class Solution {
    public int solution(String s) {
        int answer = s.length(); 

        for (int unit = 1; unit <= s.length() / 2; unit++) {
            String compressed = compress(s, unit); 
            answer = Math.min(answer, compressed.length()); 
        }

        return answer;
    }

    private String compress(String s, int unit) {
        StringBuilder compressed = new StringBuilder(); 
        String prev = s.substring(0, unit);
        int count = 1; 

        for (int i = unit; i < s.length(); i += unit) {
            String next = i + unit > s.length() ? s.substring(i) : s.substring(i, i + unit);

            if (prev.equals(next)) {
                count++;
            } else {
                if (count > 1) {
                    compressed.append(count); 
                }
                compressed.append(prev);
                prev = next; 
                count = 1;
            }
        }

        if (count > 1) {
            compressed.append(count);
        }
        compressed.append(prev);

        return compressed.toString(); 
    }
}
