import java.io.*;
import java.util.*;

public class BOJ16234 {
    static int N, L, R;
    static int[][] map;
    static boolean[][] visited;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        
        map = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        
        int days = 0;
        
        while (true) {
            visited = new boolean[N][N];
            boolean isMoved = false;
            
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (!visited[i][j]) {
                        List<int[]> union = new ArrayList<>();
                        Queue<int[]> queue = new LinkedList<>();
                        queue.offer(new int[]{i, j});
                        visited[i][j] = true;
                        union.add(new int[]{i, j});
                        int sum = map[i][j];
                        
                        while (!queue.isEmpty()) {
                            int[] cur = queue.poll();
                            int x = cur[0];
                            int y = cur[1];
                            
                            for (int d = 0; d < 4; d++) {
                                int nx = x + dx[d];
                                int ny = y + dy[d];
                                
                                if (nx < 0 || nx >= N || ny < 0 || ny >= N || visited[nx][ny])
                                    continue;
                                
                                int diff = Math.abs(map[x][y] - map[nx][ny]);
                                if (diff >= L && diff <= R) {
                                    queue.offer(new int[]{nx, ny});
                                    visited[nx][ny] = true;
                                    union.add(new int[]{nx, ny});
                                    sum += map[nx][ny];
                                }
                            }
                        }
                        
                        // 연합에 2개 이상 국가가 포함되어 있으면 인구 이동을 진행
                        if (union.size() > 1) {
                            int newPopulation = sum / union.size();
                            for (int[] cell : union) {
                                map[cell[0]][cell[1]] = newPopulation;
                            }
                            isMoved = true;
                        }
                    }
                }
            }
            
            // 하루 동안 인구 이동이 발생하지 않으면 종료
            if (!isMoved)
                break;
            
            days++;
        }
        
        System.out.println(days);
    }
}
