import java.util.*;

class Solution {
    public int[] solution(String[] gems) {
        Set<String> gemTypes = new HashSet<>();
        for (String gem : gems) {
            gemTypes.add(gem);
        }
        int totalTypes = gemTypes.size();
        
        // 슬라이딩 윈도우를 위한 변수들
        Map<String, Integer> window = new HashMap<>();
        int left = 0;
        int minLength = Integer.MAX_VALUE;
        int[] result = new int[2];
        
        // 오른쪽 포인터로 윈도우 확장
        for (int right = 0; right < gems.length; right++) {
            // 현재 보석을 윈도우에 추가
            window.put(gems[right], window.getOrDefault(gems[right], 0) + 1);
            
            // 모든 종류의 보석이 포함될 때까지 윈도우 축소
            while (window.size() == totalTypes) {
                // 현재 구간이 더 짧으면 결과 업데이트
                if (right - left + 1 < minLength) {
                    minLength = right - left + 1;
                    result[0] = left + 1;  // 1부터 시작하는 인덱스
                    result[1] = right + 1; // 1부터 시작하는 인덱스
                }
                
                // 왼쪽 보석 제거
                String leftGem = gems[left];
                window.put(leftGem, window.get(leftGem) - 1);
                if (window.get(leftGem) == 0) {
                    window.remove(leftGem);
                }
                left++;
            }
        }
        
        return result;
    }
}
