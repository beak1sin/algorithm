import java.util.*;

class Solution {
    
    public static int N = 0, M = 0;
    public static int[] dy = {-1, 1, 0, 0};
    public static int[] dx = {0, 0, -1, 1};
    
    public int solution(String[] maps) {
        N = maps.length;
        M = maps[0].length();
        
        int[] start = new int[2], lever = new int[2], exit = new int[2];
        
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                char c = maps[i].charAt(j);
                if (c == 'S') {
                    start[0] = i;
                    start[1] = j;
                }
                else if (c == 'L') {
                    lever[0] = i;
                    lever[1] = j;
                }
                else if (c == 'E') {
                    exit[0] = i;
                    exit[1] = j;
                }
            }
        }
        
        int startToLever = bfs(maps, start, lever);
        if (startToLever == -1) return -1;
        
        int leverToExit = bfs(maps, lever, exit);
        if (leverToExit == -1) return -1;
        
        return startToLever + leverToExit;
    }
    
    private int bfs(String[] maps, int[] start, int[] target) {
        Queue<int[]> queue = new LinkedList<>();
        boolean[][] visited = new boolean[N][M];
        
        queue.offer(new int[]{start[0], start[1], 0});
        visited[start[0]][start[1]] = true;
        
        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int cy = current[0];
            int cx = current[1];
            int dist = current[2];
            
            if (cy == target[0] && cx == target[1]) {
                return dist;
            }
            
            for (int i = 0; i < 4; i++) {
                int ny = cy + dy[i];
                int nx = cx + dx[i];
                
                if (isOutOfBound(ny, nx)) continue;
                if (maps[ny].charAt(nx) == 'X' || visited[ny][nx]) continue;
                
                visited[ny][nx] = true;
                queue.offer(new int[]{ny, nx, dist + 1});
            }
        }
        
        return -1;
    }
    
    private boolean isOutOfBound(int y, int x) {
        return y < 0 || y >= N || x < 0 || x >= M;
    }
}
