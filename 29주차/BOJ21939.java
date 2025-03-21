import java.io.*;
import java.util.*;

public class BOJ21939 {
    public static void main(String[] args) throws Exception {
        // 입력 준비
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        int N = Integer.parseInt(br.readLine());
        
        ArrayList<TreeSet<Integer>> levels = new ArrayList<>(101);
        for (int i = 0; i <= 100; i++) {
            levels.add(new TreeSet<>());
        }
        
        HashMap<Integer, Integer> problems = new HashMap<>();
        
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int P = Integer.parseInt(st.nextToken());
            int L = Integer.parseInt(st.nextToken());
            problems.put(P, L);
            levels.get(L).add(P);
        }
        
        int M = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            String command = st.nextToken();
            
            if (command.equals("add")) {
                int P = Integer.parseInt(st.nextToken());
                int L = Integer.parseInt(st.nextToken());
                problems.put(P, L);
                levels.get(L).add(P);
            } else if (command.equals("solved")) {
                int P = Integer.parseInt(st.nextToken());
                int L = problems.get(P);
                problems.remove(P);
                levels.get(L).remove(P);
            } else if (command.equals("recommend")) {
                int x = Integer.parseInt(st.nextToken());
                if (x == 1) {
                    for (int L = 100; L >= 1; L--) {
                        if (!levels.get(L).isEmpty()) {
                            sb.append(levels.get(L).last()).append("\n");
                            break;
                        }
                    }
                }
                else if (x == -1) {
                    for (int L = 1; L <= 100; L++) {
                        if (!levels.get(L).isEmpty()) {
                            sb.append(levels.get(L).first()).append("\n");
                            break;
                        }
                    }
                }
            }
        }
        System.out.print(sb.toString());
    }
}
