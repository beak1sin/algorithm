import java.io.*;
import java.util.*;

public class BOJ9466 {

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for (int t = 0; t < T; t++) {
			int N = Integer.parseInt(br.readLine());

			List<List<Integer>> graph = new ArrayList<>();

			for (int i = 0; i <= N; i++) {
				graph.add(new ArrayList<>());
			}

			int[] degree = new int[N+1];
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int i = 1; i <= N; i++) {
				int num = Integer.parseInt(st.nextToken());
				graph.get(i).add(num);
				degree[num]++;
			}
			Queue<Integer> queue = new ArrayDeque<>();
			for (int i = 1; i <= N; i++) {
				if (degree[i] == 0) {
					queue.add(i);
				}
			}

			int result = 0;
			while (!queue.isEmpty()) {
				int cur = queue.poll();
				result++;
				for (int next: graph.get(cur)) {
					degree[next]--;
					if (degree[next] != 0) continue;
					queue.add(next);
				}
			}

			sb.append(result).append("\n");

		}
		System.out.print(sb.toString());
	}

}
