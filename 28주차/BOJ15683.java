import java.io.*;
import java.util.*;

public class BOJ15683 {
    static int N, M = 0;
    static int result = Integer.MAX_VALUE;
    static int[][] map;
    static ArrayList<CCTV> cctvs = new ArrayList<>();
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};
    
    static class CCTV {
        int type, x, y;
        public CCTV(int type, int x, int y) {
            this.type = type;
            this.x = x;
            this.y = y;
        }
    }
    
    static int[][][] directions = {
        {},
        { {0}, {1}, {2}, {3} },                  // 1번 CCTV
        { {0,2}, {1,3} },                        // 2번 CCTV: 위아래, 좌우
        { {0,1}, {1,2}, {2,3}, {3,0} },          // 3번 CCTV: 상+우, 우+하, 하+좌, 좌+상
        { {0,1,2}, {1,2,3}, {2,3,0}, {3,0,1} },  // 4번 CCTV: 세 방향
        { {0,1,2,3} }                            // 5번 CCTV: 네 방향
    };
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j] >= 1 && map[i][j] <= 5) {
                    cctvs.add(new CCTV(map[i][j], i, j));
                }
            }
        }
        
        dfs(0, map);
        System.out.println(result);
    }
    
    static void dfs(int index, int [][] map) {
        if(index == cctvs.size()) {
            int count = 0;
            for(int i = 0; i < N; i++){
                for(int j = 0; j < M; j++){
                    if(map[i][j] == 0) count++;
                }
            }
            result = Math.min(result, count);
            return;
        }
        
        CCTV cctv = cctvs.get(index);
        int type = cctv.type;
        for(int[] dirs : directions[type]) {
            int[][] temp = new int[N][M];
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    temp[i][j] = map[i][j];
                }
            }
            for(int dir : dirs) {
                monitor(temp, cctv.x, cctv.y, dir);
            }
            dfs(index + 1, temp);
        }
    }
    
    static void monitor(int[][] map, int x, int y, int dir) {
        int nx = x + dx[dir];
        int ny = y + dy[dir];
        while(nx >= 0 && ny >= 0 && nx < N && ny < M && map[nx][ny] != 6) {
            if(map[nx][ny] == 0) {
                map[nx][ny] = -1;
            }
            nx += dx[dir];
            ny += dy[dir];
        }
    }
}
