import java.io.*;
import java.util.*;

class Jewel {
    int weight;
    int value;

    public Jewel(int weight, int value) {
        this.weight = weight;
        this.value = value;
    }
}

public class BOJ1202 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken()); 
        int K = Integer.parseInt(st.nextToken());

        Jewel[] jewelArr = new Jewel[N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int m = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            jewelArr[i] = new Jewel(m, v);
        }

        TreeMap<Integer, Integer> map = new TreeMap<>();
        for (int i = 0; i < K; i++) {
            int weight = Integer.parseInt(br.readLine());
            map.put(weight, map.getOrDefault(weight, 0) + 1);
        }

        Arrays.sort(jewelArr, (a, b) -> b.value - a.value);

        long answer = 0;

        for (Jewel jewel : jewelArr) {
            
            Integer bagWeight = null;
            if (map.containsKey(jewel.weight)) {
                bagWeight = jewel.weight;
            } else {
                bagWeight = map.higherKey(jewel.weight);
            }

            if (bagWeight != null) {
                answer += jewel.value;

                int count = map.get(bagWeight);
                if (count == 1) {
                    map.remove(bagWeight);
                } else {
                    map.put(bagWeight, count - 1);
                }
            }
        }

        System.out.println(answer);
    }
}
