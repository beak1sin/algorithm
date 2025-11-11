import java.io.*;
import java.util.*;

public class BOJ2143 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        long T = Long.parseLong(br.readLine());

        int n = Integer.parseInt(br.readLine());
        int[] A = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }

        int m = Integer.parseInt(br.readLine());
        int[] B = new int[m];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < m; i++) {
            B[i] = Integer.parseInt(st.nextToken());
        }

        Map<Long, Long> mapA = new HashMap<>();
        for (int i = 0; i < n; i++) {
            long sum = 0;
            for (int j = i; j < n; j++) {
                sum += A[j];
                mapA.put(sum, mapA.getOrDefault(sum, 0L) + 1L);
            }
        }

        long answer = 0;
        for (int i = 0; i < m; i++) {
            long sum = 0;
            for (int j = i; j < m; j++) {
                sum += B[j];
                answer += mapA.getOrDefault(T - sum, 0L);
            }
        }

        System.out.println(answer);
    }
}
