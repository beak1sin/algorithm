import java.io.*;
import java.util.*;

public class BOJ2473 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        long[] arr = new long[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Long.parseLong(st.nextToken());
        }
        Arrays.sort(arr);

        long rSum = Long.MAX_VALUE;
        long r1 = 0;
        long r2 = 0;
        long r3 = 0;

        for (int i = 0; i < N - 2; i++) {
            int left = i + 1;
            int right = N - 1;
            while (left < right) {
                long sum = arr[i] + arr[left] + arr[right];
                if (Math.abs(sum) < Math.abs(rSum)) {
                    rSum = sum;
                    r1 = arr[i];
                    r2 = arr[left];
                    r3 = arr[right];
                }
                if (sum == 0) {
                    System.out.println(arr[i] + " " + arr[left] + " " + arr[right]);
                    return;
                } else if (sum > 0) {
                    right--;
                } else {
                    left++;
                }
            }
        }

        long[] result = {r1, r2, r3};
        Arrays.sort(result);
        System.out.println(result[0] + " " + result[1] + " " + result[2]);
    }
}
