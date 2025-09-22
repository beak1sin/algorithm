import java.util.*;

class Solution {
    public int solution(int n, int[][] edge) {
        ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }

        for (int[] node : edge) {
            graph.get(node[0]).add(node[1]);
            graph.get(node[1]).add(node[0]);
        }

        int[] distance = new int[n + 1];
        boolean[] visited = new boolean[n + 1];

        Queue<Integer> queue = new ArrayDeque<>();
        queue.add(1);
        visited[1] = true;

        while (!queue.isEmpty()) {
            int currentNode = queue.poll();

            for (int nextNode : graph.get(currentNode)) {
                if (visited[nextNode]) continue;
                visited[nextNode] = true;
                distance[nextNode] = distance[currentNode] + 1;
                queue.add(nextNode); 
            }
        }

        int maxDistance = 0;
        for (int d : distance) {
            if (d > maxDistance) {
                maxDistance = d;
            }
        }
        
        int answer = 0;
        for (int d : distance) {
            if (d == maxDistance) {
                answer++;
            }
        }

        return answer;
    }
}
