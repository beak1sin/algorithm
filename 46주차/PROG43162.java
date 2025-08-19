import java.util.*;

class Solution {
    public int solution(int n, int[][] computers) {
        int answer = 0;
        
        boolean[] visited = new boolean[n];

        for (int i = 0; i < n; i++) {
            if (visited[i]) continue;
            answer++;
            bfs(i, n, computers, visited);
        }
        return answer;
    }

    void bfs(int start, int n, int[][] computers, boolean[] visited) {
        Queue<Integer> queue = new ArrayDeque<>();
        
        queue.add(start);
        visited[start] = true;

        while (!queue.isEmpty()) {
            int current = queue.poll();

            for (int i = 0; i < n; i++) {
                if (computers[current][i] != 1) continue;
                if (visited[i]) continue;
                queue.add(i);
                visited[i] = true;
            }
        }
    }
}
