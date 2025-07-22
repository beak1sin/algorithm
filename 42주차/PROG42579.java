import java.util.*;

class Solution {
    
    class Song {
        int id;
        int plays;

        Song(int id, int plays) {
            this.id = id;
            this.plays = plays;
        }
    }
    
    public int[] solution(String[] genres, int[] plays) {
        Map<String, Integer> genreTotalPlays = new HashMap<>();
        Map<String, List<Song>> genreSongs = new HashMap<>();
        
        for (int i = 0; i < genres.length; i++) {
            String genre = genres[i];
            int play = plays[i];
            
            genreTotalPlays.put(genre, genreTotalPlays.getOrDefault(genre, 0) + play);
            
            genreSongs.computeIfAbsent(genre, k -> new ArrayList<>()).add(new Song(i, play));
        }
        
        // 총 재생 횟수 기준으로 내림차순 정렬
        List<String> genreList = new ArrayList<>(genreTotalPlays.keySet());
        for (int i = 0; i < genreList.size() - 1; i++) {
            for (int j = 0; j < genreList.size() - 1 - i; j++) {
                String genre1 = genreList.get(j);
                String genre2 = genreList.get(j + 1);
                
                // 총 재생 횟수가 더 큰 장르를 앞으로
                if (genreTotalPlays.get(genre1) < genreTotalPlays.get(genre2)) {
                    genreList.set(j, genre2);
                    genreList.set(j + 1, genre1);
                }
            }
        }
        
        // 각 장르 내에서 노래들을 정렬
        for (String genre : genreSongs.keySet()) {
            List<Song> songs = genreSongs.get(genre);
            
            for (int i = 0; i < songs.size() - 1; i++) {
                for (int j = 0; j < songs.size() - 1 - i; j++) {
                    Song song1 = songs.get(j);
                    Song song2 = songs.get(j + 1);
                    
                    // 재생 횟수가 더 많거나, 재생 횟수가 같으면 id가 더 작은 노래를 앞으로
                    if (song1.plays < song2.plays || 
                        (song1.plays == song2.plays && song1.id > song2.id)) {
                        songs.set(j, song2);
                        songs.set(j + 1, song1);
                    }
                }
            }
        }
        
        List<Integer> result = new ArrayList<>();
        
        for (String genre : genreList) {
            List<Song> songs = genreSongs.get(genre);
            
            // 각 장르에서 최대 2곡까지 선택
            for (int i = 0; i < Math.min(2, songs.size()); i++) {
                result.add(songs.get(i).id);
            }
        }
        
        return result.stream().mapToInt(i -> i).toArray();
    }
}
