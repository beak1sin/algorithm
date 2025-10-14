import java.io.*;
import java.util.*;

public class Main {

    static int N, Q, size;
    static int[][] A;
    static int[] L;
    static boolean[][] visited;
    static int[] dx = {0, 0, -1, 1}; 
    static int[] dy = {-1, 1, 0, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        Q = Integer.parseInt(st.nextToken());

        size = (int) Math.pow(2, N);
        A = new int[size][size];

        for (int y = 0; y < size; y++) {
            st = new StringTokenizer(br.readLine());
            for (int x = 0; x < size; x++) {
                A[y][x] = Integer.parseInt(st.nextToken());
            }
        }

        L = new int[Q];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < Q; i++) {
            L[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 0; i < Q; i++) {
            firestorm(L[i]);
        }
        
        int totalIce = 0;
        int maxChunkSize = 0;
        visited = new boolean[size][size];

        for (int y = 0; y < size; y++) {
            for (int x = 0; x < size; x++) {
                totalIce += A[y][x];
                if (A[y][x] > 0 && !visited[y][x]) {
                    maxChunkSize = Math.max(maxChunkSize, bfs(y, x));
                }
            }
        }

        System.out.println(totalIce);
        System.out.println(maxChunkSize);
    }

    private static void firestorm(int level) {
        int subSize = (int) Math.pow(2, level);
        int[][] temp = new int[size][size];
        
        for (int y = 0; y < size; y += subSize) {
            for (int x = 0; x < size; x += subSize) {
                rotate(y, x, subSize, temp);
            }
        }
        A = temp;

        boolean[][] toMelt = new boolean[size][size];
        for (int y = 0; y < size; y++) {
            for (int x = 0; x < size; x++) {
                if (A[y][x] == 0) continue;

                int iceNeighborCount = 0;
                for (int d = 0; d < 4; d++) {
                    int ny = y + dy[d];
                    int nx = x + dx[d];

                    if (isNotBound(ny, nx)) continue;

                    if (A[ny][nx] > 0) {
                        iceNeighborCount++;
                    }
                }
                if (iceNeighborCount < 3) {
                    toMelt[y][x] = true;
                }
            }
        }

        for (int y = 0; y < size; y++) {
            for (int x = 0; x < size; x++) {
                if (toMelt[y][x]) {
                    A[y][x]--;
                }
            }
        }
    }

    private static void rotate(int startY, int startX, int subSize, int[][] temp) {
        for (int i = 0; i < subSize; i++) {
            for (int j = 0; j < subSize; j++) {
                temp[startY + j][startX + (subSize - 1 - i)] = A[startY + i][startX + j];
            }
        }
    }

    private static int bfs(int startY, int startX) {
        Queue<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[]{startY, startX});
        visited[startY][startX] = true;
        int chunkSize = 1;

        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int y = current[0];
            int x = current[1];

            for (int d = 0; d < 4; d++) {
                int ny = y + dy[d];
                int nx = x + dx[d];

                if (isNotBound(ny, nx)) continue;
                if (visited[ny][nx]) continue;
                if (A[ny][nx] == 0) continue;
                
                visited[ny][nx] = true;
                queue.offer(new int[]{ny, nx});
                chunkSize++;
            }
        }
        return chunkSize;
    }

    private static boolean isNotBound(int y, int x) {
        return y < 0 || y >= size || x < 0 || x >= size;
    }
}
