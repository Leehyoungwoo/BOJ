import java.util.*;

class Solution {
    
    int[] result ;
    String[] globalIdList;
    String[] globalReport;
    int globalK;
    
    public int[] solution(String[] id_list, String[] report, int k) {
        int[] answer = {};
        init(id_list, report, k);
        answer = findAnswer();
        return answer;
    }
    
    public void init(String[] id_list, String[] report, int k) {
        result = new int[id_list.length];
        globalIdList = id_list;
        globalReport = report;
        globalK = k;
    }
    
    public int[] findAnswer() {
        List<Integer> reportedUser = new ArrayList<>();
        int[] temp = new int[globalIdList.length];
        int[][] reportFromTo = new int[globalIdList.length][globalIdList.length];
        
        for (int i = 0; i < globalReport.length; i++) {
            int fromIdx = 0;
            int toIdx = 0;
            String[] str = globalReport[i].split(" ");
            String from = str[0];
            String to = str[1];
            for(int j = 0; j < globalIdList.length; j++) {
                if (globalIdList[j].equals(from)) {
                    fromIdx = j;
                    continue;
                }
                
                if (globalIdList[j].equals(to)) {
                    toIdx = j;
                }
            }
            reportFromTo[fromIdx][toIdx]++;
        }
        
        for (int i = 0; i < globalIdList.length; i++) {
            int count = 0;
            for(int j = 0; j < globalIdList.length; j++) {
                if (reportFromTo[j][i] != 0) {
                    count++;
                }
            }
            if (count >= globalK) {
                reportedUser.add(i);
            }
        }
        
        for(Integer user : reportedUser) {
            for(int i = 0; i < globalIdList.length; i++) {
            if(reportFromTo[i][user] != 0) {
                temp[i]++;
            }
        }
        }
        return temp;
    }
}