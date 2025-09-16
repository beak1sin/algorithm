class Solution {
    public int solution(int[] stones, int k) {
        int answer = 0;
        int min = 1;
        int max = 200_000_000;

        while (min <= max) {
            // 이번에 확인할 친구의 수
            int mid = min + (max - min) / 2;

            // 'mid'명의 친구가 건널 수 있는지 확인
            if (canCross(stones, k, mid)) {
                // 건널 수 있다면, 이 'mid'는 정답 후보가 됨
                // 더 많은 친구가 건널 수 있는지 확인하기 위해 범위를 위로 올림
                answer = mid;
                min = mid + 1;
            } else {
                // 건널 수 없다면, 친구 수를 줄여야 함
                // 범위를 아래로 내림
                max = mid - 1;
            }
        }

        return answer;
    }

    // 'friends'명의 친구가 징검다리를 건널 수 있는지 확인하는 함수
    private boolean canCross(int[] stones, int k, int friends) {
        // 0이 된 돌이 연속으로 몇 개인지 세는 변수
        int consecutiveZeros = 0;

        for (int stone : stones) {
            // 'friends'명이 건너고 나면 돌의 내구도는 'stone - friends'가 됨
            // 이 값이 0보다 작거나 같으면 밟을 수 없는 돌이 됨
            if (stone - friends < 0) {
                consecutiveZeros++;
            } else {
                // 밟을 수 있는 돌을 만나면 연속 카운트 초기화
                consecutiveZeros = 0;
            }

            // 밟을 수 없는 돌이 k개 이상 연속되면 건널 수 없음
            if (consecutiveZeros >= k) {
                return false;
            }
        }

        // 모든 돌을 확인했는데도 k개 이상 연속된 구간이 없으면 건널 수 있음
        return true;
    }
}
