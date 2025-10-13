import java.util.*;

class Solution {
    public static int[][] map = new int[102][102];
    public static boolean[][] visited = new boolean[102][102];
    
    public static int[] dx = {0, 0, 1, -1};
    public static int[] dy = {1, -1, 0, 0};

    public int solution(int[][] rectangle, int characterX, int characterY, int itemX, int itemY) {
        
        // 테두리 1로 채우기
        for (int[] rect : rectangle) {
            fill(rect[0] * 2, rect[1] * 2, rect[2] * 2, rect[3] * 2, 1);
        }
        
        // 안쪽 2로 채우기
        for (int[] rect : rectangle) {
            fill(rect[0] * 2 + 1, rect[1] * 2 + 1, rect[2] * 2 - 1, rect[3] * 2 - 1, 2);
        }
        
        return bfs(characterX * 2, characterY * 2, itemX * 2, itemY * 2);
    }

    public static void fill(int x1, int y1, int x2, int y2, int value) {
        for (int i = y1; i <= y2; i++) {
            for (int j = x1; j <= x2; j++) {
                map[i][j] = value;
            }
        }
    }
    
    public static boolean isNotBound(int nx, int ny) {
        return nx < 0 || ny < 0 || nx > 101 || ny > 101;
    }

    public static int bfs(int startX, int startY, int itemX, int itemY) {
        Queue<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[]{startX, startY, 0});
        visited[startY][startX] = true;
        
        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int cx = current[0];
            int cy = current[1];
            int dist = current[2];
            
            if (cx == itemX && cy == itemY) {
                // 좌표 2배라 다시 나눔
                return dist / 2;
            }
            
            for (int i = 0; i < 4; i++) {
                int nx = cx + dx[i];
                int ny = cy + dy[i];
                
                if (isNotBound(nx, ny)) continue;
                if (visited[ny][nx]) continue;
                if (map[ny][nx] != 1) continue;
                
                visited[ny][nx] = true;
                queue.offer(new int[]{nx, ny, dist + 1});
            }
        }
        
        return -1;
    }
}
