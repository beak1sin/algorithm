import java.util.*;

class Solution {
    public long solution(int n, int[] works) {
        // 우선순위 큐를 Max Heap으로 사용하기 위해 Collections.reverseOrder()를 적용
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());

        // 모든 작업량을 우선순위 큐에 추가
        for (int work : works) {
            maxHeap.offer(work);
        }

        // n시간 동안 가장 큰 작업량을 1씩 줄이는 작업을 반복
        for (int i = 0; i < n; i++) {
            // 가장 큰 작업량을 꺼냄
            int maxWork = maxHeap.poll();

            // 만약 가장 큰 작업량이 0이라면, 모든 일이 끝난 것이므로 중단
            if (maxWork == 0) {
                return 0;
            }
            
            // 작업량을 1 줄여서 다시 큐에 추가
            maxHeap.offer(maxWork - 1);
        }

        // 남은 작업량들의 제곱의 합(야근 피로도) 계산
        long result = 0;
        while (!maxHeap.isEmpty()) {
            int work = maxHeap.poll();
            result += (long) work * work;
        }

        return result;
    }
}
