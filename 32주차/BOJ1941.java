import java.io.*;
import java.util.*;

public class BOJ1941 {
    static char[][] graph = new char[5][5];
    static boolean[] comb = new boolean[25];
    static int result = 0;
    static int[] dy = {-1, 1, 0, 0};
    static int[] dx = {0, 0, -1, 1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        for (int y = 0; y < 5; y++) graph[y] = br.readLine().toCharArray();
        combination(0, 0, 0);
        System.out.println(result);
    }

    static void combination(int index, int depth, int sCnt) {
        if (sCnt + (7 - depth) < 4) return;
        if (depth == 7) {
            if (isConnect()) result++;
            return;
        }
        if (index >= 25) return;

        comb[index] = true;
        combination(index + 1, depth + 1, sCnt + (graph[index / 5][index % 5] == 'S' ? 1 : 0));
        comb[index] = false;
        combination(index + 1, depth, sCnt);
    }

    static boolean isConnect() {
        boolean[][] visited = new boolean[5][5];
        Deque<int[]> q = new ArrayDeque<>();

        for (int i = 0; i < 25; i++) {
            if (comb[i]) {
                int y = i / 5;
                int x = i % 5;
                q.offer(new int[]{y, x});
                visited[y][x] = true;
                break;
            }
        }
        int count = 1;
        while (!q.isEmpty()) {
            int[] cur = q.poll();
            for (int d = 0; d < 4; d++) {
                int ny = cur[0] + dy[d];
                int nx = cur[1] + dx[d];
                if (ny < 0 || ny >= 5 || nx < 0 || nx >= 5) continue;
                if (visited[ny][nx]) continue;
                if (!comb[ny * 5 + nx]) continue;
                visited[ny][nx] = true;
                count++;
                q.offer(new int[]{ny, nx});
            }
        }
        return count == 7;
    }
}
