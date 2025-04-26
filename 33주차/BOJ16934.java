import java.io.*;
import java.util.*;

public class BOJ16934 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		Set<String> prefixSet = new HashSet<>();
		Map<String, Integer> countMap = new HashMap<>();

		for (int i = 0; i < N; i++) {
			String s = br.readLine();
			String alias = null;

			// 접두사 탐색
			for (int len = 1; len <= s.length(); len++) {
				String p = s.substring(0, len);
				if (!prefixSet.contains(p)) {
					alias = p;
					break;
				}
			}

			if (alias != null) {
				// 모든 접두사 추가
				for (int len = 1; len <= s.length(); len++) {
					prefixSet.add(s.substring(0, len));
				}
        
				// 현재 닉네임의 이전 카운트를 가져옴 (현재 사용자는 포함하지 않음)
				int cnt = countMap.getOrDefault(s, 0);
				countMap.put(s, cnt + 1);
				System.out.println(alias);
			} else {
				// x는 현재까지의 카운트 (이전 사용자 수)
				int x = countMap.getOrDefault(s, 0);
				countMap.put(s, x + 1);
        
				if (x == 0) {
					System.out.println(s);
				} else {
					System.out.println(s + (x + 1));
				}
			}
		}
	}
}
