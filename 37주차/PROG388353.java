import java.util.*;

class Solution {
    
    public static int[] dy = {-1, 0, 1 , 0};
    public static int[] dx = {0, 1, 0, -1};
    
    public static void takeOutContainerJigae(String[] storage, String container) {
        int n = storage.length, m = storage[0].length();
        char[][] grid = new char[n][m];
        for (int i = 0; i < n; i++) {
            grid[i] = storage[i].toCharArray();
        }
        char target = container.charAt(0);
        List<int[]> toRemove = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] != target) continue;

                boolean reachable = false;
                for (int d = 0; d < 4; d++) {
                    int ni = i + dy[d];
                    int nj = j + dx[d];
                  
                    // 가장자리 인접 시 곧바로 접근 가능
                    if (ni < 0 || ni >= n || nj < 0 || nj >= m) {
                        reachable = true; break;
                    }
                    // 빈 칸 덩어리가 외부에 닿는지 검사
                    if (grid[ni][nj] == '0' && canReachBoundaryDFS(grid, ni, nj)) {
                        reachable = true; break;
                    }
                }
                if (reachable) toRemove.add(new int[]{i, j});
            }
        }

        // 한꺼번에 삭제
        for (int[] p : toRemove) {
            grid[p[0]][p[1]] = '0';
        }

        // 복원
        for (int i = 0; i < n; i++) {
            storage[i] = new String(grid[i]);
        }
    }

    // 인접 빈 칸 덩어리가 경계에 닿는지 재귀 DFS
    private static boolean canReachBoundaryDFS(char[][] grid, int y, int x) {
        int n = grid.length, m = grid[0].length;
        boolean[][] visited = new boolean[n][m];
        return dfs(grid, visited, y, x);
    }

    private static boolean dfs(char[][] g, boolean[][] v, int i, int j) {
        v[i][j] = true;
        // 경계에 닿으면 외부와 연결된 것
        if (i == 0 || i == g.length-1 || j == 0 || j == g[0].length-1) return true;

        for (int d = 0; d < 4; d++) {
            int ni = i + dy[d], nj = j + dx[d];
            if (ni < 0 || ni >= g.length || nj < 0 || nj >= g[0].length) continue;
            if (!v[ni][nj] && g[ni][nj] == '0' && dfs(g, v, ni, nj)) {
                return true;
            }
        }
        return false;
    }
    
    public static void takeOutContainerCraine(String[] storage, String container) {
        
        for (int i = 0; i < storage.length; i++) {
            String[] arr = storage[i].split("");
            for (int j = 0; j < arr.length; j++) {
                if (arr[j].equals(container)) {
                    arr[j] = "0";
                }
            }
            storage[i] = String.join("", arr);
        }
    }
    
    public int solution(String[] storage, String[] requests) {
        int answer = 0;
        
        for (int i = 0; i < requests.length; i++) {
            // System.out.println(requests[i].length());
            int rLen = requests[i].length();
            if (rLen > 1) {
                // 크레인
                String container = requests[i].substring(0, 1);
                takeOutContainerCraine(storage, container);
            } else {
                // 지게차
                takeOutContainerJigae(storage, requests[i]);
            }

        }

        for (int i = 0; i < storage.length; i++) {
            String[] arr = storage[i].split("");
            for (int j = 0; j < arr.length; j++) {
                if (!arr[j].equals("0")) answer++;
            }
        }
        
        return answer;
    }
}
