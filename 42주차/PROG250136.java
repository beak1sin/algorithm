import java.util.*;

class Solution {

    public static int N = 0, M = 0;
    public static int[] dy = {-1, 0, 1, 0};
    public static int[] dx = {0, 1, 0, -1};

    public static boolean isOutOfBound(int y, int x) {
        return y < 0 || y >= N || x < 0 || x >= M;
    }

    public static int bfs(int y, int x, boolean[][] visited, int[][] land, int groupId, int[][] groupMap) {
        int count = 1;
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{y, x});
        visited[y][x] = true;
        groupMap[y][x] = groupId;

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            int cy = cur[0], cx = cur[1];

            for (int i = 0; i < 4; i++) {
                int ny = cy + dy[i];
                int nx = cx + dx[i];
                if (isOutOfBound(ny, nx)) continue;
                if (visited[ny][nx]) continue;
                if (land[ny][nx] == 0) continue;

                visited[ny][nx] = true;
                groupMap[ny][nx] = groupId;
                queue.offer(new int[]{ny, nx});
                count++;
            }
        }

        return count;
    }

    public int solution(int[][] land) {
        int answer = 0;
        N = land.length;
        M = land[0].length;

        boolean[][] visited = new boolean[N][M];
        int[][] groupMap = new int[N][M];
        Map<Integer, Integer> groupSizes = new HashMap<>();

        int groupId = 1;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (land[i][j] == 1 && !visited[i][j]) {
                    int size = bfs(i, j, visited, land, groupId, groupMap);
                    groupSizes.put(groupId, size);
                    groupId++;
                }
            }
        }

        for (int j = 0; j < M; j++) {
            Set<Integer> groupSet = new HashSet<>();
            int totalSize = 0;

            for (int i = 0; i < N; i++) {
                if (land[i][j] == 1) {
                    int id = groupMap[i][j];
                    groupSet.add(id);
                }
            }

            for (int id : groupSet) {
                totalSize += groupSizes.get(id);
            }

            answer = Math.max(answer, totalSize);
        }

        return answer;
    }
}
