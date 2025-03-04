import java.io.*;
import java.util.*;

public class BOJ2011 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();

        int[] dp = new int[s.length()+1];
        dp[0] = 1;

        for(int i=1; i<=s.length(); i++) {
            int now = s.charAt(i-1) - '0';
            if(now >= 1 && now <= 9) {
                dp[i] += dp[i-1];
                dp[i] %= 1000000;
            }

            if(i == 1) continue;

            int prev = s.charAt(i-2) - '0';

            if(prev == 0) continue;

            int value = prev*10+now;

            if(value >= 10 && value <= 26) {
                dp[i] += dp[i-2];
                dp[i] %= 1000000;
            }
        }
        System.out.println(dp[s.length()]);
    }
}
