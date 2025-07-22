class Solution {
    
    public static int[] dy = {0, 1, 0, -1};
    public static int[] dx = {1, 0, -1, 0};
    
    public int[] solution(int rows, int columns, int[][] queries) {
        int[] answer = new int[queries.length];
        
        int[][] maze = new int[rows][columns];
        int count = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                maze[i][j] = ++count;
            }
        }
        
        for (int i = 0; i < queries.length; i++) {
            int y1 = queries[i][0] - 1;
            int x1 = queries[i][1] - 1;
            int y2 = queries[i][2] - 1;
            int x2 = queries[i][3] - 1;
            
            // 테두리 원소들을 배열에 저장
            int[] border = new int[2 * (y2 - y1 + x2 - x1)];
            int idx = 0;
            
            // 시계방향으로 테두리 수집
            // 상단 행 (왼쪽→오른쪽)
            for (int x = x1; x < x2; x++) {
                border[idx++] = maze[y1][x];
            }
            // 오른쪽 열 (위→아래)
            for (int y = y1; y < y2; y++) {
                border[idx++] = maze[y][x2];
            }
            // 하단 행 (오른쪽→왼쪽)
            for (int x = x2; x > x1; x--) {
                border[idx++] = maze[y2][x];
            }
            // 왼쪽 열 (아래→위)
            for (int y = y2; y > y1; y--) {
                border[idx++] = maze[y][x1];
            }
            
            // 최솟값 찾기
            int min = border[0];
            for (int val : border) {
                min = Math.min(min, val);
            }
            answer[i] = min;
            
            // 한 칸씩 시계방향으로 회전하여 재배치
            idx = 0;
            // 상단 행 (왼쪽→오른쪽)
            for (int x = x1; x < x2; x++) {
                maze[y1][x] = border[(idx + border.length - 1) % border.length];
                idx++;
            }
            // 오른쪽 열 (위→아래)
            for (int y = y1; y < y2; y++) {
                maze[y][x2] = border[(idx + border.length - 1) % border.length];
                idx++;
            }
            // 하단 행 (오른쪽→왼쪽)
            for (int x = x2; x > x1; x--) {
                maze[y2][x] = border[(idx + border.length - 1) % border.length];
                idx++;
            }
            // 왼쪽 열 (아래→위)
            for (int y = y2; y > y1; y--) {
                maze[y][x1] = border[(idx + border.length - 1) % border.length];
                idx++;
            }
        }
        
        return answer;
    }
}
