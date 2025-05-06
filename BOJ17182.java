import java.io.*;
import java.util.*;

public class BOJ17182 {
    static int N, K, ans = Integer.MAX_VALUE;
    static int[][] dist;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        dist = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                dist[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int k = 0; k < N; k++) {
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (dist[i][j] > dist[i][k] + dist[k][j]) {
                        dist[i][j] = dist[i][k] + dist[k][j];
                    }
                }
            }
        }

        visited = new boolean[N];
        visited[K] = true;
        dfs(K, 1, 0);

        System.out.println(ans);
    }

    static void dfs(int curr, int count, int cost) {
        if (cost >= ans) return;

        if (count == N) {
            ans = cost;
            return;
        }

        for (int next = 0; next < N; next++) {
            if (!visited[next]) {
                visited[next] = true;
                dfs(next, count + 1, cost + dist[curr][next]);
                visited[next] = false;
            }
        }
    }
}
