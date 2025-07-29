import java.util.*;

class Solution {
    public String solution(String number, int k) {
        Stack<Character> stack = new Stack<>();
        int removeCount = 0;
        
        for (int i = 0; i < number.length(); i++) {
            char currentDigit = number.charAt(i);
            
            while (!stack.isEmpty() && 
                   removeCount < k && 
                   stack.peek() < currentDigit) {
                stack.pop();
                removeCount++;
            }
            
            stack.push(currentDigit);
        }
        
        // 아직 제거할 개수가 남았다면 뒤에서부터 제거
        while (removeCount < k && !stack.isEmpty()) {
            stack.pop();
            removeCount++;
        }
        
        StringBuilder result = new StringBuilder();
        for (Character digit : stack) {
            result.append(digit);
        }
        
        return result.toString();
    }
}
