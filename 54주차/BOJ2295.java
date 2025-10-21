import java.io.*;
import java.util.*;

public class BOJ2295 {

	public static int N;
	public static int[] arr;
	public static int answer = 0;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		arr = new int[N];
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(br.readLine());
		}

		Arrays.sort(arr);

		HashSet<Integer> twoSum = new HashSet<>();

		for (int i = 0; i < N; i++) {
			for (int j = i; j < N; j++) {
				twoSum.add(arr[i] + arr[j]);
			}
		}

		for (int i = N - 1; i >= 0; i--) {
			for (int j = 0; j < N; j++) {
				int DMinusC = arr[i] - arr[j];
				if (twoSum.contains(DMinusC)) {
					answer = Math.max(answer, arr[i]);
				}
			}
		}

		System.out.println(answer);

	}

}
