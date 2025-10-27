import java.io.*;

public class BOJ10775 {
    
    public static int[] parent;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int G = Integer.parseInt(br.readLine());
        int P = Integer.parseInt(br.readLine());
        
        parent = new int[G + 1];
        for (int i = 1; i <= G; i++) {
            parent[i] = i; 
        }
        
        int count = 0;
        
        for (int i = 0; i < P; i++) {
            int g = Integer.parseInt(br.readLine());
            
            int gate = find(g);
            
            if (gate == 0) {
                break;
            }
            
            count++;
            
            union(gate, gate - 1);
        }
        
        System.out.println(count);
    }
    
    public static int find(int i) {
        if (parent[i] == i) {
            return i;
        }
        return parent[i] = find(parent[i]);
    }
    
    public static void union(int a, int b) {
        int rootA = find(a);
        int rootB = find(b);
        
        if (rootA != rootB) {
            parent[rootA] = rootB;
        }
    }
}
