class Solution {

    static int N, M, K;

    public boolean solution(int[][] key, int[][] lock) {
        N = lock.length;
        M = key.length;
        K = 0;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (lock[i][j] == 0) {
                    K++;
                }
            }
        }

        // 4번 회전
        for (int r = 0; r < 4; r++) {
            for (int i = 1 - M; i < N; i++) {
                for (int j = 1 - M; j < N; j++) {
                    if (canUnlockWithPlacement(key, lock, i, j)) {
                        return true;
                    }
                }
            }
            key = rotateKey(key);
        }

        return false;
    }

    private int[][] rotateKey(int[][] key) {
        int[][] rotatedKey = new int[M][M];
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < M; j++) {
                rotatedKey[j][M - 1 - i] = key[i][j];
            }
        }
        return rotatedKey;
    }

    private boolean canUnlockWithPlacement(int[][] key, int[][] lock, int keyOffsetX, int keyOffsetY) {
        int filledHoleCount = 0;

        // 자물쇠의 모든 칸(i, j)을 순회
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                int keyRow = i - keyOffsetX;
                int keyCol = j - keyOffsetY;

                if (keyRow >= 0 && keyRow < M && keyCol >= 0 && keyCol < M) {
                    if (lock[i][j] + key[keyRow][keyCol] != 1) {
                        return false;
                    }
                    if (lock[i][j] == 0) {
                        filledHoleCount++;
                    }
                }
            }
        }
        
        return filledHoleCount == K;
    }
}
