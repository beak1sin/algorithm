import java.util.*;
import java.io.*;

public class BOJ1644 {

	public static boolean [] isPrime;
	public static int N;
	public static ArrayList<Integer> primeList;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());

		isPrime = new boolean[N+1];

		Arrays.fill(isPrime, true);

		isPrime[0] = false;
		isPrime[1] = false;

		for (int i = 2; i*i <= N; i++) {
			if (isPrime[i]) {
				for (int j = i*i; j <= N; j += i) {
					isPrime[j] = false;
				}
			}
		}

		primeList = new ArrayList<>();
		for (int i = 2; i <= N; i++) {
			if (isPrime[i]) {
				primeList.add(i);
			}
		}

		int count = 0;
		int size = primeList.size();

		for (int i = 0; i < size; i++) {
			int sum = 0;

			for (int j = i; j < size; j++) {
				sum += primeList.get(j);

				if (sum == N) {
					count++;
					break;
				} else if (sum > N) {
					break;
				}
			}
		}

		System.out.println(count);

	}

}
