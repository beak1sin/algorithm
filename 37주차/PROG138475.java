import java.util.*;

class Solution {
    public int[] solution(int e, int[] starts) {
        
        int [] yaksooCount = new int[e+1];
        
        for (int i = 1; i <= e; i++) {
            for (int j = i; j <= e; j += i) {
                yaksooCount[j]++;
            }
        }
        
        int[] maxNumber = new int[e+1];
        maxNumber[e] = e;
        for (int i = e-1; i >= 1; i--) {
            if (yaksooCount[i] >= yaksooCount[maxNumber[i+1]]) {
                maxNumber[i] = i;
            } else {
                maxNumber[i] = maxNumber[i+1];
            }
            
        }
                                     
        int[] answer = new int[starts.length];
        for (int i = 0; i < starts.length; i++) {
            answer[i] = maxNumber[starts[i]];
        }                          
        return answer;
    }
}
