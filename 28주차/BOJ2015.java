import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.HashMap;

public class BOJ2015 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        long K = Long.parseLong(st.nextToken());
        
        int[] A = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }
        
        // 누적합을 기록할 HashMap 초기화 (키: 누적합, 값: 빈도수)
        HashMap<Long, Long> prefixMap = new HashMap<>();
        // 시작 전 누적합 0은 1번 등장한 것으로 간주
        prefixMap.put(0L, 1L);
        
        long prefixSum = 0;
        long count = 0;
        
        // 배열을 순회하며 누적합 갱신 및 K를 만족하는 구간 확인
        for (int i = 0; i < N; i++) {
            prefixSum += A[i];
            // 현재 누적합에서 K를 뺀 값이 이전에 몇 번 등장했는지 확인
            long target = prefixSum - K;
            if (prefixMap.containsKey(target)) {
                count += prefixMap.get(target);
            }
            // 현재 누적합의 빈도수를 업데이트
            prefixMap.put(prefixSum, prefixMap.getOrDefault(prefixSum, 0L) + 1);
        }
        
        System.out.println(count);
    }
}
