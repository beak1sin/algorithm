import java.util.*;

class Solution {

    static class Node implements Comparable<Node> {
        int id;
        int intensity;

        public Node(int id, int intensity) {
            this.id = id;
            this.intensity = intensity;
        }

        @Override
        public int compareTo(Node o) {
            return this.intensity - o.intensity;
        }
    }

    static List<List<Node>> graph;

    public int[] solution(int n, int[][] paths, int[] gates, int[] summits) {
        
        graph = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }

        for (int[] path : paths) {
            int from = path[0];
            int to = path[1];
            int time = path[2];
            graph.get(from).add(new Node(to, time));
            graph.get(to).add(new Node(from, time));
        }

        boolean[] isSummit = new boolean[n + 1];
        for (int summit : summits) {
            isSummit[summit] = true;
        }
        
        int[] intensity = new int[n + 1];
        Arrays.fill(intensity, Integer.MAX_VALUE);

        PriorityQueue<Node> pq = new PriorityQueue<>();

        for (int gate : gates) {
            intensity[gate] = 0;
            pq.offer(new Node(gate, 0));
        }

        while (!pq.isEmpty()) {
            Node current = pq.poll();
            
            // 산봉우리인지 체크
            if (current.intensity > intensity[current.id] || isSummit[current.id]) {
                continue;
            }

            for (Node next : graph.get(current.id)) {
                int newIntensity = Math.max(intensity[current.id], next.intensity);
                
                if (newIntensity < intensity[next.id]) {
                    intensity[next.id] = newIntensity;
                    pq.offer(new Node(next.id, newIntensity));
                }
            }
        }

        int minSummitId = Integer.MAX_VALUE;
        int minIntensityValue = Integer.MAX_VALUE;

        Arrays.sort(summits);

        for (int summit : summits) {
            if (intensity[summit] < minIntensityValue) {
                minIntensityValue = intensity[summit];
                minSummitId = summit;
            }
        }

        return new int[]{minSummitId, minIntensityValue};
    }
}
