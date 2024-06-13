import java.util.*;

class Solution {
    public int[] solution(String[] genres, int[] plays) {
        Map<String, List<Song>> map = new HashMap<>();
        Map<String, Integer> playCount = new HashMap<>();

        // Map에 넣기
        for (int i = 0; i < genres.length; i++) {
            if (!map.containsKey(genres[i])) {
                map.put(genres[i], new ArrayList<>());
            }
            map.get(genres[i]).add(new Song(plays[i], i));
            playCount.put(genres[i], playCount.getOrDefault(genres[i], 0) + plays[i]);
        }

        // 장르별 재생 횟수 기준으로 정렬
        List<String> sortedGenres = new ArrayList<>(playCount.keySet());
        sortedGenres.sort((g1, g2) -> playCount.get(g2) - playCount.get(g1));

        // 결과를 담을 리스트
        List<Integer> result = new ArrayList<>();

        // 장르별로 노래 정렬하여 결과에 추가
        for (String genre : sortedGenres) {
            List<Song> songs = map.get(genre);
            songs.sort((s1, s2) -> s2.plays - s1.plays); // 재생 횟수 내림차순 정렬
            for (int i = 0; i < Math.min(2, songs.size()); i++) {
                result.add(songs.get(i).no);
            }
        }

        // List<Integer>를 int[] 배열로 변환하여 반환
        int[] answer = new int[result.size()];
        for (int i = 0; i < result.size(); i++) {
            answer[i] = result.get(i);
        }
        return answer;
    }
}

class Song {
    int plays;
    int no;

    public Song(int plays, int no) {
        this.plays = plays;
        this.no = no;
    }
}
