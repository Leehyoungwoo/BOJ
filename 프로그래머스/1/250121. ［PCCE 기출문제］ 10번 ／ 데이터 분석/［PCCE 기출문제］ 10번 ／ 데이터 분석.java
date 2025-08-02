import java.util.*;

class Solution {
    public int[][] solution(int[][] data, String ext, int val_ext, String sort_by) {
        //정렬한 데이터들이 담긴 이차원 정수 리스트 data
        // 어떤 정보를 기준으로 데이터를 뽑아낼지를 의미하는 문자열 ext
        // 뽑아낼 정보의 기준값을 나타내는 정수 val_ext
        // 정보를 정렬할 기준이 되는 문자열 sort_by
        // 데이터 길이 500 25000
        // data code, date, maximum, remain 
        List<int[]> answerList = new ArrayList<>();
        // 기준점을 뽑아야하는데 
        int ex = find(ext);
        int sb = find(sort_by);
        
        for (int i = 0; i < data.length; i++) {
            // int code = data[i][0];   
            // int ext = data[i][1];
            // int maximum = data[i][2];
            // int remain = data[i][3];
            // ---------------------
            if (data[i][ex] < val_ext) {
                answerList.add(data[i]);
            }
        }
        
        Collections.sort(answerList, Comparator.comparingInt(a -> a[sb]));
        int[][] answer = new int[answerList.size()][4];
        
        for (int i = 0; i < answer.length; i++) {
            int[] an = answerList.get(i);
            answer[i] = an;
        }
        
        return answer;
    }
    
    private int find(String ext) {
        if (ext.equals("code")) return 0;
        if (ext.equals("date")) return 1;
        if (ext.equals("maximum")) return 2;
        return 3;
    }
}