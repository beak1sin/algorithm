public class Solution {
    public int solution(String name) {
        int n = name.length();
        int answer = 0;
        
        for (int i = 0; i < n; i++) {
            char c = name.charAt(i);
            int up = c - 'A';
            int down = 'Z' - c + 1;
            answer += Math.min(up, down);
        }
        
        // 좌우 이동 횟수 최적화
        int minHorizontalMove = n - 1;  // 오른쪽으로만 이동하는 경우
        
        for (int i = 0; i < n; i++) {
            // 현재 위치에서 연속된 A의 끝 위치 찾기
            int nextPos = i + 1;
            while (nextPos < n && name.charAt(nextPos) == 'A') {
                nextPos++;
            }
            
            // 세 가지 이동 경로 중 최소값 계산
            // 1) 오른쪽으로만 이동: n - 1
            // 2) 왼쪽으로만 이동 후 오른쪽: i + i + (n - nextPos)
            // 3) 오른쪽 이동 후 왼쪽: (n - nextPos) + (n - nextPos) + i
            
            int rightThenLeft = i + i + (n - nextPos);           // 오른쪽 갔다가 왼쪽으로 되돌아가기
            int leftThenRight = (n - nextPos) + (n - nextPos) + i; // 왼쪽으로 갔다가 오른쪽으로 되돌아가기
            
            minHorizontalMove = Math.min(minHorizontalMove, 
                                       Math.min(rightThenLeft, leftThenRight));
        }
        
        return answer + minHorizontalMove;
    }
    
}
