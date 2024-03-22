import java.util.*;

class Solution {
    public int solution(String skill, String[] skill_trees) {
        int answer = 0;
        List<Character> skillList = new ArrayList<>();
        for(int i = 0; i < skill.length(); i++) {
            skillList.add(skill.charAt(i));
        }
        for (int i = 0; i < skill_trees.length; i++) {
            List<Character> list = new ArrayList<>();
            String oneSkill = skill_trees[i];
            boolean flag = true;
            for(int j = 0; j < oneSkill.length(); j++) {
                char c = oneSkill.charAt(j);
                list.add(c);
                if (skillList.contains(c)) {
                    if(skillList.indexOf(c) == 0) {
                        continue;
                    }
                    int beforeIdx = skillList.indexOf(c) - 1;
                    char before = skillList.get(beforeIdx);
                    if(!list.contains(before)) {
                        flag = false;
                    }
                }
            }
            if(flag) {
                answer++;
            }
        }
        return answer;
    }
}