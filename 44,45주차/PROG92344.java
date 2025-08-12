public class Solution {
    public int solution(int[][] board, int[][] skill) {
        int N = board.length;
        int M = board[0].length;
        
        // 차분 배열 (difference array) - 경계 처리를 위해 +1 크기로 생성
        int[][] diff = new int[N + 1][M + 1];
        
        // 각 스킬을 차분 배열에 적용
        for (int[] s : skill) {
            int type = s[0];
            int r1 = s[1], c1 = s[2];
            int r2 = s[3], c2 = s[4];
            int degree = s[5];
            
            // type 1: 공격(감소), type 2: 회복(증가)
            int value = (type == 1) ? -degree : degree;
            
            // 차분 배열에 직사각형 범위 업데이트
            diff[r1][c1] += value;
            diff[r1][c2 + 1] -= value;
            diff[r2 + 1][c1] -= value;
            diff[r2 + 1][c2 + 1] += value;
        }
        
        // 차분 배열을 이용해 실제 변화량 계산 (2D 누적합)
        // 1단계: 가로 방향 누적합
        for (int i = 0; i < N; i++) {
            for (int j = 1; j < M; j++) {
                diff[i][j] += diff[i][j - 1];
            }
        }
        
        // 2단계: 세로 방향 누적합
        for (int j = 0; j < M; j++) {
            for (int i = 1; i < N; i++) {
                diff[i][j] += diff[i - 1][j];
            }
        }
        
        // 최종 내구도 계산하고 파괴되지 않은 건물 개수 세기
        int answer = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                int finalDurability = board[i][j] + diff[i][j];
                if (finalDurability > 0) {
                    answer++;
                }
            }
        }
        
        return answer;
    }
}
