import java.io.*;
import java.util.*;

public class BOJ1935 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        
        st = new StringTokenizer(br.readLine());
        String postfix = st.nextToken();
        
        double[] values = new double[26];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            values[i] = Double.parseDouble(st.nextToken());
        }
        
        Stack<Double> stack = new Stack<>();
        for (int i = 0; i < postfix.length(); i++) {
            char ch = postfix.charAt(i);
            if (ch >= 'A' && ch <= 'Z') {
                stack.push(values[ch - 'A']);
            } else {
                double b = stack.pop();
                double a = stack.pop();
                double result = 0;
                switch(ch) {
                    case '+': result = a + b; break;
                    case '-': result = a - b; break;
                    case '*': result = a * b; break;
                    case '/': result = a / b; break;
                }
                stack.push(result);
            }
        }
        System.out.printf("%.2f", stack.pop());
    }
}
