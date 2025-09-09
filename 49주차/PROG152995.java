import java.util.*;

class Solution {
    public int solution(int[][] scores) {
        int wanhoAttitude = scores[0][0];
        int wanhoPeer = scores[0][1];
        int wanhoSum = wanhoAttitude + wanhoPeer;

        // 근무 태도 점수(내림차순), 동료 평가 점수(오름차순)
        Arrays.sort(scores, (a, b) -> {
            if (a[0] == b[0]) {
                return a[1] - b[1]; // 동료 평가 점수는 오름차순
            }
            return b[0] - a[0]; // 근무 태도 점수는 내림차순
        });

        List<Integer> eligibleSums = new ArrayList<>();
        int maxPeerScore = 0;

        for (int[] score : scores) {
            // 현재 사원의 동료 평가 점수가 이전까지의 최대 동료 평가 점수보다 낮은 경우
            // 이 사원은 인센티브 대상에서 제외됨
            if (score[1] < maxPeerScore) {
                // 제외된 사원이 완호인 경우
                if (score[0] == wanhoAttitude && score[1] == wanhoPeer) {
                    return -1;
                }
            } else {
                // 인센티브 대상자인 경우
                maxPeerScore = Math.max(maxPeerScore, score[1]);
                eligibleSums.add(score[0] + score[1]);
            }
        }

        int rank = 1;
        for (int sum : eligibleSums) {
            if (sum > wanhoSum) {
                rank++;
            }
        }

        return rank;
    }
}
