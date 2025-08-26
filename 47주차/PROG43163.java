import java.util.*;

class Solution {

    static class Node {
        String word;
        int step;

        public Node(String word, int step) {
            this.word = word;
            this.step = step;
        }
    }

    public int solution(String begin, String target, String[] words) {
        if (!Arrays.asList(words).contains(target)) {
            return 0;
        }

        Queue<Node> queue = new ArrayDeque<>();
        boolean[] visited = new boolean[words.length];

        queue.add(new Node(begin, 0));

        while (!queue.isEmpty()) {
            Node current = queue.poll();

            // 현재 단어가 target과 일치하면 현재 단계를 반환
            if (current.word.equals(target)) {
                return current.step;
            }

            // words 배열을 순회하며 다음 변환 단어를 탐색
            for (int i = 0; i < words.length; i++) {
                if (visited[i]) continue;
                if (!isOneDiff(current.word, words[i])) continue;
                visited[i] = true; 
                queue.add(new Node(words[i], current.step + 1)); 
            }
        }

        return 0;
    }

    // 두 단어가 한 글자만 다른지 확인하는 메서드
    private boolean isOneDiff(String currentWord, String nextWord) {
        int diffCount = 0;
        for (int i = 0; i < currentWord.length(); i++) {
            if (currentWord.charAt(i) != nextWord.charAt(i)) {
                diffCount++;
            }
            if (diffCount > 1) {
                return false;
            }
        }
        return diffCount == 1;
    }
}
