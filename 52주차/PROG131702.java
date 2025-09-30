import java.util.*;

class Solution {
    public static int n;
    public static int[][] originalClockHands;
    public static int minClicks = Integer.MAX_VALUE;
    
    public static int[] dy = {0, -1, 1, 0, 0};
    public static int[] dx = {0, 0, 0, -1, 1};

    public int solution(int[][] clockHands) {
        n = clockHands.length;
        originalClockHands = clockHands;

        // 첫 번째 행(y=0)을 조작하는 모든 경우의 수를 탐색
        generateFirstRowClicks(0, new int[n]);

        return minClicks;
    }

    public void generateFirstRowClicks(int x, int[] firstRowClicks) {
        if (x == n) {
            simulate(firstRowClicks); // 첫 행의 조합이 완성되면 시뮬레이션 실행
            return;
        }

        for (int i = 0; i < 4; i++) {
            firstRowClicks[x] = i;
            generateFirstRowClicks(x + 1, firstRowClicks);
        }
    }
    
    // 특정 첫 행 조합에 대해 전체 퍼즐 해결 과정을 시뮬레이션
    public void simulate(int[] firstRowClicks) {
        int[][] tempGrid = new int[n][];
        for (int i = 0; i < n; i++) {
            tempGrid[i] = Arrays.copyOf(originalClockHands[i], n);
        }

        int currentTotalClicks = 0;

        // 첫 번째 행(y=0)에 조작을 적용
        for (int x = 0; x < n; x++) {
            int clicks = firstRowClicks[x];
            if (clicks > 0) {
                currentTotalClicks += clicks;
                applyClicks(tempGrid, 0, x, clicks);
            }
        }

        // 두 번째 행부터 연쇄적으로 조작 (윗 행을 12시로 맞춤)
        for (int y = 1; y < n; y++) {
            for (int x = 0; x < n; x++) {
                int clicksNeeded = (4 - tempGrid[y - 1][x]) % 4;
                if (clicksNeeded > 0) {
                    currentTotalClicks += clicksNeeded;
                    applyClicks(tempGrid, y, x, clicksNeeded);
                }
            }
        }
        
        // 마지막 행이 모두 12시인지 확인하여 성공 여부 판단
        boolean isSolved = true;
        for (int x = 0; x < n; x++) {
            if (tempGrid[n - 1][x] != 0) {
                isSolved = false;
                break;
            }
        }

        if (isSolved) {
            minClicks = Math.min(minClicks, currentTotalClicks);
        }
    }
    
    // (y, x) 위치의 시계를 count만큼 조작
    public void applyClicks(int[][] grid, int y, int x, int count) {
        for (int i = 0; i < 5; i++) {
            int ny = y + dy[i];
            int nx = x + dx[i];
            if (isNotBound(ny, nx)) continue;
            grid[ny][nx] = (grid[ny][nx] + count) % 4;
        }
    }
    
    public static boolean isNotBound(int ny, int nx) {
        return ny < 0 || ny >= n || nx < 0 || nx >= n;
    }
}
