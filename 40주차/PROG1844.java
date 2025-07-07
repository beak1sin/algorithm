import java.util.*;

class Solution {
    
    public boolean isOutOfBoundary(int y, int x, int n, int m) {
        return y < 0 || y >= n || x < 0 || x >= m; 
    }
    
    public int solution(int[][] maps) {
        int n = maps.length;
        int m = maps[0].length;
        
        int[] dy = {-1, 1, 0, 0};
        int[] dx = {0, 0, -1, 1};
        
        Queue<int[]> queue = new ArrayDeque<>();
        
        boolean[][] visited = new boolean[n][m];
        
        queue.offer(new int[]{0, 0, 1});
        visited[0][0] = true;
        
        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int y = current[0];
            int x = current[1];
            int distance = current[2];
            
            if (y == n - 1 && x == m - 1) {
                return distance;
            }
            
            for (int i = 0; i < 4; i++) {
                int ny = y + dy[i];
                int nx = x + dx[i];
                
                if (isOutOfBoundary(ny, nx, n, m)) continue;
                if (maps[ny][nx] == 0) continue;
                if (visited[ny][nx]) continue;
                
                visited[ny][nx] = true;
                queue.offer(new int[]{ny, nx, distance + 1});
            }
        }
        
        return -1;
    }
}
