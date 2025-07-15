import java.util.*;

class Solution {
    public int solution(int[] queue1, int[] queue2) {
        long sum1 = 0, sum2 = 0;
        for (int i = 0; i < queue1.length; i++) {
            sum1 += queue1[i];
            sum2 += queue2[i];
        }
        
        long total_sum = sum1 + sum2;
        if (total_sum % 2 == 1) return -1;
        long target = total_sum / 2;
        
        Queue<Integer> q1 = new ArrayDeque<>();
        Queue<Integer> q2 = new ArrayDeque<>();
        
        for (int i = 0; i < queue1.length; i++) {
            q1.offer(queue1[i]);
            q2.offer(queue2[i]);
        }
        
        int result = 0;
        int max_oper = 600000;
        
        while (sum1 != target && result <= max_oper) {
           if (sum1 > target) {
                if (q1.isEmpty()) return -1;
                int val = q1.poll();
                q2.offer(val);
                sum1 -= val;
            } else {
                if (q2.isEmpty()) return -1;
                int val = q2.poll();
                q1.offer(val);
                sum1 += val;
            }
            result++;
        }
        
        return sum1 == target ? result : -1;
    }
}
