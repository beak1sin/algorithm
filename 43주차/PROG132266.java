import java.util.*;

class Solution {
    public int[] solution(int n, int[][] roads, int[] sources, int destination) {
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }
        
        for (int[] road : roads) {
            int a = road[0];
            int b = road[1];
            graph.get(a).add(b); 
            graph.get(b).add(a); 
        }
        
        int[] distances = new int[n + 1];
        Arrays.fill(distances, -1);
        
        Queue<Integer> queue = new ArrayDeque<>();
        queue.offer(destination); 
        distances[destination] = 0; 
        
        while (!queue.isEmpty()) {
            int current = queue.poll();
            
            for (int next : graph.get(current)) {
                if (distances[next] == -1) {
                    distances[next] = distances[current] + 1;
                    queue.offer(next);
                }
            }
        }
        
        int[] result = new int[sources.length];
        for (int i = 0; i < sources.length; i++) {
            result[i] = distances[sources[i]];
        }
        
        return result;
    }
}
