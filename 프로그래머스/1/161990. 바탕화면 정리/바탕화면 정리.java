class Solution {
    public int[] solution(String[] wallpaper) {
        // 파일을 #이고
        // 최소값과 최대값을 구해야함
        // 각각 제일 작은 r, c / 제일 큰 r, c를 구해야해
        int lr = Integer.MAX_VALUE;
        int lc = Integer.MAX_VALUE;
        int hr = 0;
        int hc = 0;
        for (int i = 0; i < wallpaper.length; i++) {
            for (int j = 0; j < wallpaper[i].length(); j++) {
                
                if (wallpaper[i].charAt(j) == '#') {
                    // System.out.println("현재 행 " + i);
                    // System.out.println("현재 열 " + j);
                    lr = Math.min(lr, i);
                    lc = Math.min(lc, j);
                    hr = Math.max(hr, i + 1);
                    hc = Math.max(hc, j + 1);
                }
            }
        }
        
        return new int[] {lr, lc, hr, hc};
    }
}