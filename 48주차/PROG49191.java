import java.util.*;

class Solution {
    public int solution(int n, int[][] results) {
        boolean[][] graph = new boolean[n + 1][n + 1];

        for (int[] result : results) {
            int winner = result[0];
            int loser = result[1];
            graph[winner][loser] = true;
        }

        for (int k = 1; k <= n; k++) {
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= n; j++) {
                    if (graph[i][k] && graph[k][j]) {
                        graph[i][j] = true;
                    }
                }
            }
        }

        int answer = 0;
        for (int i = 1; i <= n; i++) {
            int knownResults = 0;
            for (int j = 1; j <= n; j++) {
                if (i == j) continue;

                if (graph[i][j] || graph[j][i]) {
                    knownResults++;
                }
            }

            if (knownResults == n - 1) {
                answer++;
            }
        }

        return answer;
    }
}
