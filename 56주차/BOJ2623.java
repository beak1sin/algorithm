import java.util.*;
import java.io.*;

public class BOJ2623 {

	public static int N, M;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		List<List<Integer>> graph = new ArrayList<>();
		int[] inDegree = new int[N+1];

		for (int i = 0; i <= N; i++) {
			graph.add(new ArrayList<>());
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());

			int size = Integer.parseInt(st.nextToken());

			int first = Integer.parseInt(st.nextToken());
			for (int j = 0; j < size - 1; j++) {
				int second = Integer.parseInt(st.nextToken());
				graph.get(first).add(second);
				inDegree[second]++;
				first = second;
			}
		}

		Queue<Integer> queue = new ArrayDeque<>();
		ArrayList<Integer> result = new ArrayList<>();


		for (int i = 1; i <= N; i++) {
			if (inDegree[i] == 0) {
				queue.add(i);
			}
 		}

		while (!queue.isEmpty()) {
			int poll = queue.poll();
			result.add(poll);

			int size = graph.get(poll).size();
			for (int i = 0; i < size; i++) {
				int next = graph.get(poll).get(i);
				inDegree[next]--;

				if (inDegree[next] == 0) {
					queue.add(next);
				}
			}
		}

		StringBuilder sb = new StringBuilder();

		if (result.size() == N) {
			for (Integer res: result) {
				sb.append(res).append("\n");
			}
			System.out.print(sb.toString());
		} else {
			System.out.println(0);
		}

	}

}
