import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class BOJ7576 {

    public static int M, N;
    public static int[][] graph;
    public static Deque<int[]> queue;
    public static int[] dx = {1, 0, 0, -1};
    public static int[] dy = {0, 1, -1, 0};

    public static void bfs() {
        while (!queue.isEmpty()) {
            int[] poll = queue.pollFirst();
            int x = poll[0];
            int y = poll[1];
            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];
                if (nx >= 0 && nx < N && ny >= 0 && ny < M && graph[nx][ny] == 0) {
                    queue.addLast(new int[]{nx, ny});
                    graph[nx][ny] = graph[x][y] + 1;
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        graph = new int[N][M];
        queue = new LinkedList<>();
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                graph[i][j] = Integer.parseInt(st.nextToken());
                if (graph[i][j] == 1) {
                    queue.addLast(new int[]{i, j});
                }
            }
        }

        bfs();

        int count = 0;
        boolean flag = false;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (graph[i][j] == 0) {
                    flag = true;
                    break;
                }
                count = Math.max(count, graph[i][j]);
            }
        }
        if (flag) {
            System.out.println(-1);
        } else {
            System.out.println(count == 1 ? 0 : count - 1);
        }
    }
}
