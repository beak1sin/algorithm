import java.util.*;

public class Solution {
    
    static class State {
        int y;
        int x;
        int cur_dist;
        String result;
        
        State(int y, int x, int cur_dist, String result) {
            this.y = y;
            this.x = x;
            this.cur_dist = cur_dist;
            this.result = result;
        }
    }
    
    public String solution(int n, int m, int y, int x, int r, int c, int k) {
        
        int min_dist = Math.abs(y - r) + Math.abs(x - c);
        
        if (min_dist > k) return "impossible";
        if ((k - min_dist) % 2 != 0) return "impossible";
        
        // 사전순
        int[] dy = {1, 0, 0, -1};
        int[] dx = {0, -1, 1, 0};
        char[] dir = {'d', 'l', 'r', 'u'};
        
        Queue<State> queue = new ArrayDeque<>();
        queue.offer(new State(y, x, 0, ""));
        
        boolean[][][] visited = new boolean[n + 1][m + 1][k + 1];
        visited[y][x][0] = true;
        
        while (!queue.isEmpty()) {
            State poll = queue.poll();
            
            if (poll.y == r && poll.x == c && poll.cur_dist == k) {
                return poll.result;
            }
            
            if (poll.cur_dist >= k) continue;
            
            int remaining_dist = k - poll.cur_dist;
            int distToTarget = Math.abs(poll.y - r) + Math.abs(poll.x - c);
            
            if (distToTarget > remaining_dist) continue;
            
            if ((remaining_dist - distToTarget) % 2 == 1) continue;
            
            for (int i = 0; i < 4; i++) {
                int ny = poll.y + dy[i];
                int nx = poll.x + dx[i];
                int next_dist = poll.cur_dist + 1;
                
                if (ny < 1 || ny > n || nx < 1 || nx > m) continue;
                
                if (visited[ny][nx][next_dist]) continue;
                
                visited[ny][nx][next_dist] = true;
                queue.offer(new State(ny, nx, next_dist, poll.result + dir[i]));
            }
        }
        
        return "impossible";
    }
}
