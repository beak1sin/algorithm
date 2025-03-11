import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class BOJ1463 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        
        // dp 배열 선언: dp[i]는 i를 1로 만들기 위한 최소 연산 횟수
        int[] dp = new int[N + 1];
        dp[1] = 0; 
        
        // 2부터 N까지 반복하며 dp 테이블 채우기
        for (int i = 2; i <= N; i++) {
            // 항상 1을 빼는 연산을 고려
            dp[i] = dp[i - 1] + 1;
            
            // 2로 나누어 떨어지는 경우
            if (i % 2 == 0) {
                dp[i] = Math.min(dp[i], dp[i / 2] + 1);
            }
            // 3으로 나누어 떨어지는 경우
            if (i % 3 == 0) {
                dp[i] = Math.min(dp[i], dp[i / 3] + 1);
            }
        }
        
        // 결과 출력: N을 1로 만드는 최소 연산 횟수
        System.out.println(dp[N]);
    }
}
