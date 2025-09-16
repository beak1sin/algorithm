import java.util.*;

class Solution {
    public int[] solution(String[] operations) {
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>((o1, o2) -> o2 - o1);

        for (String op : operations) {
            StringTokenizer st = new StringTokenizer(op);
            String command = st.nextToken();
            int number = Integer.parseInt(st.nextToken());

            if (command.equals("I")) {
                minHeap.add(number);
                maxHeap.add(number);
            } else if (command.equals("D")) {
                if (minHeap.isEmpty()) {
                    continue;
                }

                if (number == 1) {
                    int maxVal = maxHeap.poll();
                    minHeap.remove(maxVal);
                } else {
                    int minVal = minHeap.poll();
                    maxHeap.remove(minVal);
                }
            }
        }

        if (minHeap.isEmpty()) {
            return new int[]{0, 0};
        } else {
            return new int[]{maxHeap.peek(), minHeap.peek()};
        }
    }
}
