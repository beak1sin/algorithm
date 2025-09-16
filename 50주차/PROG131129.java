import java.util.*;

class Solution {
    public int[] solution(int target) {
        // dp[i][0]: i점을 만드는 최소 다트 수
        // dp[i][1]: 그 때의 최대 싱글/불 횟수
        int[][] dp = new int[target + 1][2];

        // dp 배열 초기화 (다트 수는 매우 큰 값으로)
        for (int i = 1; i <= target; i++) {
            dp[i][0] = Integer.MAX_VALUE;
            dp[i][1] = 0;
        }

        // 0점은 0개의 다트, 0개의 싱글/불로 만들 수 있음 (기저 상태)
        dp[0][0] = 0;
        dp[0][1] = 0;

        // 1점부터 target 점까지 순서대로 최적 해를 구함
        for (int i = 0; i < target; i++) {
            // 현재 i점에서 다트를 한 번 더 던져 도달할 수 있는 다음 점수들을 갱신
            
            // 싱글 또는 불을 던지는 경우 (1~20점, 50점)
            // 이 경우들은 [다트 1개, 싱글/불 1개]를 추가하는 것과 같음
            for (int s = 1; s <= 20; s++) {
                if (i + s <= target) {
                    updateDp(dp, i, i + s, 1, 1);
                }
            }
            if (i + 50 <= target) {
                updateDp(dp, i, i + 50, 1, 1);
            }

            // 더블을 던지는 경우 (2, 4, ..., 40점)
            // 이 경우들은 [다트 1개, 싱글/불 0개]를 추가하는 것과 같음
            for (int d = 1; d <= 20; d++) {
                if (i + d * 2 <= target) {
                    updateDp(dp, i, i + d * 2, 1, 0);
                }
            }
            
            // 트리플을 던지는 경우 (3, 6, ..., 60점)
            // 이 경우들은 [다트 1개, 싱글/불 0개]를 추가하는 것과 같음
            for (int t = 1; t <= 20; t++) {
                if (i + t * 3 <= target) {
                    updateDp(dp, i, i + t * 3, 1, 0);
                }
            }
        }

        return dp[target];
    }

    // dp 테이블을 갱신하는 헬퍼 메서드
    private void updateDp(int[][] dp, int prevScore, int nextScore, int dartsToAdd, int singlesToAdd) {
        int newDarts = dp[prevScore][0] + dartsToAdd;
        int newSingles = dp[prevScore][1] + singlesToAdd;

        // 더 적은 다트로 도달 가능하다면 무조건 갱신
        if (newDarts < dp[nextScore][0]) {
            dp[nextScore][0] = newDarts;
            dp[nextScore][1] = newSingles;
        } 
        // 다트 수가 같다면 싱글/불 횟수가 더 많은 경우에만 갱신
        else if (newDarts == dp[nextScore][0]) {
            if (newSingles > dp[nextScore][1]) {
                dp[nextScore][1] = newSingles;
            }
        }
    }
}
