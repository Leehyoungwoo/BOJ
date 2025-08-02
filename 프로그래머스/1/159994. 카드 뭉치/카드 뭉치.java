class Solution {
    public String solution(String[] cards1, String[] cards2, String[] goal) {
        String answer = "";
        // 카드 뭉치를 번갈아가면서 쓰는건 가능하지만 idx를 맘대로 할 수는 없다
        // cards1, 2 길이가 10이면 굳이 set을 활용할 필요까지는 없다
        // 인덱스 두 둥치를 따로 두고 0으로 시작
        // goal을 순회하면서 1에 있어? 사용하고 인덱스 1 증가, 2에 있어? 사용하고 2증가
        // 없는 순간에 stop하고 No 리턴
        int oneIdx = 0;
        int twoIdx = 0;
        // goal 순회
        for (int i = 0; i < goal.length; i++) {
            String target = goal[i];
            // System.out.println(target);
            // if (oneIdx == cards1.length || twoIdx == cards2.length) {
            //     break;
            // }
            // 둘 다 없으면 브레이크 해야함
            // 인덱스 체크가 문제인데 
            // 해결하기 위해서는 메서드 분리하고 각 조건 앞에 넣어줘야할거 같음
            if (isNotOver(cards1, oneIdx) && !cards1[oneIdx].equals(target) && isNotOver(cards2, twoIdx) && !cards2[twoIdx].equals(target)) {
                break;
            }
            if (isNotOver(cards1, oneIdx) && cards1[oneIdx].equals(target)) {
                // System.out.println(cards1[oneIdx]);
                // System.out.println(target + "은 " + "cards1적중!!!");
                oneIdx++;
                continue;
            }
            if (isNotOver(cards2, twoIdx) && cards2[twoIdx].equals(target)) {
                // System.out.println(cards2[oneIdx]);
                // System.out.println(target + "은 " + "cards2적중!!!");
                twoIdx++;
            }
        }
        
        if (oneIdx + twoIdx == goal.length) {
            return "Yes";
        }
        return "No";
    }
    
    private boolean isNotOver(String[] cards, int idx) {
        return idx < cards.length;
    }
}